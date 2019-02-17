import target

import cv2
import math
import numpy as np
import os
import sys
import tensorflow as tf

HATCH_PROB=0.5
ROBOT_BASE_WIDTH = 24
DISTANCE = 6

IMG_SCALE = 4

def getKey(transform) :
    """
    Returns the transforms key components
    """
    theta = math.asin(transform[0][0,2]) * 180 / np.pi
    x = transform[1][0, 0]
    z = transform[1][2, 0]
    phi = math.atan2(x, z)* 180 /np.pi

    return theta, phi, x, z


def robotSpaceToHatchSpace(transform, point) :

    # Point relative to hatch center
    x = transform[1][0, 0] - point[0]
    z = transform[1][2, 0] - point[1]

    # rotate point to hatch space
    s = transform[0][0,2]
    c = transform[0][0,0]

    x_ = c * x - s * z
    z_ = c * z + s * x

    return x_, z_


def hatchSpaceToRobotSpace(transform, point) :

    # Point in hatch space
    x = point[0]
    z = point[1]

    # rotate point to robot orientation
    theta = math.asin(transform[0][0, 2])
    s = np.sin(theta)
    c = np.cos(theta)

    x_ = c * x + s * z
    z_ = c * z - s * x

    # Conver to robot centered
    x_ = transform[1][0, 0] - x_
    z_ = transform[1][2, 0] - z_

    return x_, z_


def getHatchRelativePosition(transform) :
    """
    Get position of robot in the hatch's frame of reference
    """
    return robotSpaceToHatchSpace(transform, [0, 0])


def getCircleParameters(point) :
    """
    Given point on x,z plane, draw circle from origin through the point that is tangent to the
    z axis.  Find the center (on the x axis) and arc length and return them.
    """

    phi = math.atan2(point[1], point[0])
    d = math.sqrt(point[0]**2 + point[1]**2)
    arc_angle = np.pi - 2 * phi
    center = 0.5 * d / math.cos(phi)

    return center, arc_angle


def getPathTarget(transform, distance = DISTANCE) :
    """
    Given current hatch transform, compute a point along the arc path from the hatch to the
    robot the robot should target.  This is similar to the pathfinder system, but using the
    arc to the hatch rather than a line segment.
    Target is computed in hatch space, but returned in robot space
    """
    # Get position of robot in hatch space
    hrp = getHatchRelativePosition(transform)

    # Draw circle on robot from hatch perspective
    rp = hcenter - [ -IMG_SCALE * hrp[0] * transform[0][0, 0] + -IMG_SCALE * hrp[1] * transform[0][0, 2],
                     -IMG_SCALE * hrp[0] * transform[0][0, 2] + IMG_SCALE * hrp[1] * transform[0][0, 0]]
    cv2.circle(overhead, tuple(rp.astype(int)), 5, [0,128,0], IMG_SCALE//2)

    # Get circle parameters (hatch space)
    cp = getCircleParameters(hrp)

    # draw circle for hatch
    arc_center = hcenter -\
                 [-IMG_SCALE * cp[0] * transform[0][0,0], -IMG_SCALE * cp[0] * transform[0][0,2]]
    arc_radius = abs(4*int(cp[0]))
    cv2.circle(overhead, tuple(arc_center.astype(int)), arc_radius, [128,128,128], 1)

    # radius * angle (radians)
    arc_length = cp[0] * cp[1]

    if arc_length < distance :
        # Target *is* hatch
        target = [0,0]
    else :
        # Arc angle from hatch to target
        a = (arc_length - distance) / cp[0]

        target = [cp[0] - math.cos(a) * cp[0], math.sin(a) * cp[0]]

    print("Hatchspace target", target)
    # Draw circle on target from hatch perspective
    rp = hcenter - [ -IMG_SCALE * target[0] * transform[0][0, 0] + -IMG_SCALE * target[1] * transform[0][0, 2],
                     -IMG_SCALE * target[0] * transform[0][0, 2] + IMG_SCALE * target[1] * transform[0][0, 0]]
    cv2.circle(overhead, tuple(rp.astype(int)), 5, [0,128,0], IMG_SCALE//2)

    robot_target = hatchSpaceToRobotSpace(transform, target)

    # Draw circle on target from robot perspective
    rp = ro_position + np.array([-robot_target[0], robot_target[1]]) * IMG_SCALE
    cv2.circle(overhead, tuple(rp.astype(int)), 5, [128,0,128], IMG_SCALE//2)

    return robot_target

def getSpeed(target, speed) :
    """
    Get the l, r speed ratios to take robot along a circle that passes through target point
    """
    center, angle = getCircleParameters(target)

    l_rad = center + ROBOT_BASE_WIDTH/2
    r_rad = center - ROBOT_BASE_WIDTH/2

    l_speed = l_rad / center * speed
    r_speed = r_rad / center

    return l_speed, r_speed

def getNewTransform(transform, target, speed) :
    # Find robot new location, orientation.  For simulation
    center, angle = getCircleParameters(target)

    # Draw guide circle to target
    circle_center = ro_position - [center * IMG_SCALE, 0]
    cv2.circle(overhead, tuple(circle_center.astype(int)), abs(int(center*IMG_SCALE)), [0,255,255], 1)

    # Move speed along the circle centered at center
    angle_covered = speed / center

    # New center, orientation
    new_c = np.matrix(
        [[center - math.cos(angle_covered) * center, 0, math.sin(angle_covered) * center]]).T
    m = np.matrix([[math.cos(angle_covered), 0, -math.sin(angle_covered)],
                   [0, 1, 0],
                   [math.sin(angle_covered), 0, math.cos(angle_covered)]])

    # Adjust transform
    transform[0] = m * transform[0]
    transform[1] = m * (transform[1] - new_c)

    return transform

def run(transform, view_trans, speed, distance=DISTANCE) :
    target = getPathTarget(view_trans, distance)
    print("Robotspace target", target)

    transform = getNewTransform(transform, target, speed)

    return transform, target

# pick an orientation
hatch_pos = None
if np.random.rand() < HATCH_PROB :
    hatch_pos = target.getHatchPosition()

transform = list(target.getRandomTransform())

# session = tf.Session()
#
# #Create a saver object to load the model
# saver = tf.train.import_meta_graph(os.path.join('checkpoints','.meta'))
#
# #restore the model from our checkpoints folder
# saver.restore(session,os.path.join('checkpoints', '.\\'))
#
# #Create graph object for getting the same network architecture
# graph = tf.get_default_graph()
#
# #Get the last layer of the network by it's name which includes all the previous layers too
# network = graph.get_tensor_by_name("Tanh:0")
# im_ph = graph.get_tensor_by_name("Placeholder:0")
# label_ph = graph.get_tensor_by_name("Placeholder_1:0")


SPEED = 1

count = 0

for i in range(20000) :
    print(getKey(transform))
    img = target.drawImage(transform, hatch_pos)
    overhead = np.zeros([100 * IMG_SCALE, 100 * IMG_SCALE, 3])
    # Draw Robot
    ro_position = np.array((50 * IMG_SCALE, 5 * IMG_SCALE)) # Robot overhead position
    cv2.circle(overhead, tuple(ro_position), 2 * IMG_SCALE, [255, 0, 0], 3)
    cv2.line(overhead, tuple(ro_position), tuple(ro_position + [0, 5 * IMG_SCALE]), [255, 255, 255], 2)
    # Draw Hatch
    hcenter = ro_position + [- transform[1][0, 0] * IMG_SCALE, transform[1][2, 0] * IMG_SCALE]
    cv2.circle(overhead, tuple(hcenter.astype(int)), 2*IMG_SCALE, [0, 0, 255], 3)
    hdir = (hcenter + [5 * IMG_SCALE * transform[0][0, 2], -5 * IMG_SCALE * transform[0][0, 0]])
    cv2.line(overhead, tuple(hcenter.astype(int)), tuple(hdir.astype(int)), [255, 255, 255], 2)

    # Investigate!
    # data = img.reshape(1,240,320,1).astype(np.float)/255
    # result = session.run(network, feed_dict={im_ph:data, label_ph:[[0,0,0]]})
    # print(result*360)
    # angle = result[0,0]*2*np.pi
    # view_trans = [np.matrix([[math.cos(angle), 0, math.sin(angle)],
    #                          [0, 1, 0],
    #                          [-math.sin(angle), 0, math.cos(angle)]]),
    #               np.matrix([[result[0,1]*360, 0, result[0,2]*360]]).T]

    # option = determineMove(transform)
    # print(option)
    # transform = move(transform, option)
    transform, pt = run(transform, transform, SPEED)

    output = np.zeros([440, 780, 3], dtype=np.uint8)
    output[:, :] = [32,32,32]
    output[20:420, 20:420, :] = overhead
    output[100:340, 440:760, 0] = img
    output[100:340, 440:760, 1] = img
    output[100:340, 440:760, 2] = img

    cv2.imshow("Img", output)

    cv2.imwrite("demo/demo%03d.jpg" % count, output)
    count += 1

    x = cv2.waitKey(0)
    if x == ord('q') :
        sys.exit(0)



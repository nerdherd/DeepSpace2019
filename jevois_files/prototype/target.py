import noise

import cv2
import numpy as np
import os
import sys

# Image size
WIDTH = 320
HEIGHT = 240

HATCH_PROB = 0.5

THRESHOLD = True

cam_intrinsics = np.matrix([
    [WIDTH/2,   0,          WIDTH/2],
    [0,         WIDTH/2,    HEIGHT/2],
    [0,         0,          1]])

# Camera position is at origin pointing in +z, so we don't need to include it in calculations


def createTargets() :
    # Targets returned with innermost points on y=0 plane, facing -z

    # TBD: Randomize angle, length a bit?
    # Target dimensions
    pts = np.matrix([[0, 0], [0, 5.25], [-2, 5.25], [-2, 0]]).T
    angle = -14.5 * np.pi / 180.0

    rmat = np.matrix([[np.cos(angle), np.sin(angle)], [-np.sin(angle), np.cos(angle)]])

    lf_target = np.concatenate(((rmat * pts) + [[-4], [0]], [[12]*4]), axis=0)
    rt_target = lf_target.copy()
    rt_target[0] *= -1

    return lf_target, rt_target

targets = createTargets()

hatch_pts = np.matrix([[9.5 * np.sin(x*np.pi/12), 9.5 * np.cos(x*np.pi/12) + 9.5 + 2.5, 0] for x in range(24)]).T


def drawTarget(img, pts, color=128) :
    coords = cam_intrinsics * pts
    coords = coords[:2] / coords[-1]    # Homogeneous coordinates

    coords = np.array(coords.T, int)     # Again, make opencv happy

    cv2.fillConvexPoly(img, coords, color)


def drawTargets(img, targets) :
    for target in targets :
        drawTarget(img, target)


def drawHatch(img, pos, transform) :
    pts = transformTargets([hatch_pts + pos], transform)

    drawTarget(img, pts[0], 16)


def addNoise(img, amp = 128, freq = 32, octaves = 2, offset = 30) :
    z = np.random.rand() * 1000

    for y in range(WIDTH):
        for x in range(HEIGHT):
            img[x,y] = max(0, img[x,y] + int(noise.pnoise3(x / freq, y / freq, z, octaves) * amp + offset))

def threshold(img, thresh=110) :
    if not THRESHOLD :
        return img
    return cv2.threshold(img, thresh, 255, cv2.THRESH_BINARY)[1]


def transformTargets(targets, transform) :
    new_targets = []
    for target in targets :
        new_targets.append(transform[0] * target + transform[1])
    return new_targets

def getHatchPosition() :
    x = np.random.rand() * 4 - 2
    z = np.random.rand() * 8 - 4
    return np.matrix([x, 0, z]).T

def getRandomTransform(angle = None) :
    # TBD, height settings?
    if angle is None :
        angle = -10
        while abs(angle) > np.pi/3 :
            angle = np.random.normal(0, 0.4)

    distance = 0
    while distance < 12 or distance > 120 :
        distance = np.clip(np.random.normal(24, 24), 12, 120)

    heading = -10
    while abs(heading) > np.pi/6 :
        heading = np.clip(np.random.normal(0, 0.25), -np.pi/6, np.pi/6)

    translation = np.array([[np.sin(heading) * distance, 0, np.cos(heading) * distance]]).T

    rotation = np.matrix([
        [np.cos(angle), 0, np.sin(angle)],
        [0, 1, 0],
        [-np.sin(angle), 0, np.cos(angle)]
    ])

    return rotation, translation, 180*angle/np.pi

def drawImage(transform, hatch_pos) :
    img = np.zeros([HEIGHT, WIDTH, 1], np.uint8)
    ttargets = transformTargets(targets, transform[:-1])
    drawTargets(img, ttargets)

    if hatch_pos is not None :
        drawHatch(img, hatch_pos, transform[:-1])

    addNoise(img)
    img = threshold(img)

    return img


# tf = getRandomTransform()
# print(tf)
# tf_targets = transformTargets(targets, tf)
# print("TFT ",tf_targets)
# tft = tf_targets[0][:2] / tf_targets[0][-1]
# print ("tft", tft)
#
# coords1 = cam_intrinsics * tf_targets[0]
# coords1 = coords1[:2] / coords1[-1]  # Homogeneous coordinates
# coords2 = cam_intrinsics * tf_targets[1]
# coords2 = coords2[:2] / coords2[-1]  # Homogeneous coordinates
# V_I = np.asmatrix(np.ones((4,4)))
# V_I[0] = [coords1[0][0,0], coords1[0][0,3], coords2[0][0,0], coords2[0][0,3]]
# V_I[1] = [coords1[1][0,0], coords1[1][0,3], coords2[1][0,0], coords2[1][0,3]]
#
# C_I = np.asmatrix(np.identity(4))
# C_I[:3,:3] = cam_intrinsics
#
# V_T = np.asmatrix(np.ones((4,4)))
# V_T[:3,0] = targets[0][:,0]
# V_T[:3,1] = targets[0][:,3]
# V_T[:3,2] = targets[1][:,0]
# V_T[:3,3] = targets[1][:,3]
# V_T[3] = 1.0
# print("VT", V_T)
# VTI = (V_T + np.identity(4)*1e-8).I
#
# print("VI", V_I)
# print ("UNdone ", cam_intrinsics.I * V_I[:3])
# print ("UNdone ", C_I.I * V_I)
# print ("RESULT ", C_I.I * V_I * VTI)

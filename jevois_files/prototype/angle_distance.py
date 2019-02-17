import scipy
import numpy as np
import lmfit
import math

import target

def getBaseTargets() :
    targets = np.asmatrix(np.ones((3,4)))
    targets[:,0] = target.targets[0][:,0]
    targets[:,1] = target.targets[0][:,3]
    targets[:,2] = target.targets[1][:,0]
    targets[:,3] = target.targets[1][:,3]
    targets[1] += 5
    return targets


def get3dCoords(transform) :
    targets = getBaseTargets()
    tf_targets = transform[0] * targets + transform[1]
    return tf_targets

def getViewCoords(transform) :
    V_T = get3dCoords(transform)
    V_I = target.cam_intrinsics * V_T
    V_I = V_I[:2] / V_I[-1]  # Homogeneous coordinates

    return V_I.astype(int).astype(float)

tf = target.getRandomTransform()

# print (tf)
# print ("VT", get3dCoords(tf))
# print ("VI", getViewCoords(tf))
# print()
f_x = target.cam_intrinsics[0,0]
f_y = target.cam_intrinsics[1,1]
g_x = target.cam_intrinsics[0,2]
g_y = target.cam_intrinsics[1,2]
# print(f_x, f_y, g_x, g_y)
# print()

V_I = getViewCoords(tf)
V_T = get3dCoords(tf)

# What we want
T_z = tf[1][2,0]
T_x = tf[1][0,0]
r_xx = tf[0][0,0]
r_xz = tf[0][0,2]

print ("Correct ", math.asin(r_xz)*180/np.pi, T_x, T_z)

for i in range(4) :
    xp = V_T[0, i]      # x+Tx
    yp = V_T[1, i]      # y+Ty
    zp = V_T[2, i]      # z+Tz
    x_I = V_I[0, i]
    y_I = V_I[1, i]

    # print()
    # print(i, x_I, y_I)
    # print((f_y * V_T[1,i]/V_T[2,i] + g_y))
    # print(y_I-g_y, f_y * V_T[1,i]/V_T[2,i])
    # print(zp / yp, f_y / (y_I - g_y))

    a = zp
    b = f_y * yp / (y_I - g_y)
    # print(i, a,b, a-b)
    c = xp/zp
    d = (x_I - g_x) / f_x
    # print(i, c, d, c-d)

p = lmfit.Parameters()

base = getBaseTargets()

# Calculate the mean z and x based upon points
mean_z = 0
for i in range(4) :
    x_I = V_I[0, i]
    y_I = V_I[1, i]
    mean_z += f_y * base[1,i] / (y_I - g_y) - base[2,i]
mean_z /= 4.0

mean_x = 0
for i in range(4) :
    x_I = V_I[0, i]
    y_I = V_I[1, i]
    mean_x += (x_I - g_x) / f_x * (base[2,i] + mean_z) - base[0,i]
mean_x /= 4.0

# We could take these from previous frame/tick, but calculate from image might work
p.add("T_X", mean_x)
p.add("T_Z", mean_z)
p.add("Angle", 0)

def residual(params) :
    a = params['Angle'].value
    tx = params['T_X'].value
    tz = params['T_Z'].value

    print("Trial: ", a*180/np.pi, tx, tz)

    c = math.cos(a)
    s = math.sin(a)

    resids = []
    for i in range(4) :
        x = base[0, i]
        y = base[1, i]
        z = base[2, i]
        x_I = V_I[0, i]
        y_I = V_I[1, i]

        _y = c * z - s * x + tz
        _x = c * x + s * z + tx
        _fy = f_y * y / (y_I - g_y)
        _fx = f_x / (x_I - g_x)

        resids.append(_fy - _y)
        resids.append(_fx - _y / _x)
    return resids

lmfit.minimize(residual, p)

print ("Correct ", math.asin(r_xz)*180/np.pi, T_x, T_z)


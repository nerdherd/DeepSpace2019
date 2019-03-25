/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.constants;

import com.nerdherd.lib.misc.NerdyMath;

/**
 * Add your docs here.
 */
public class ArmConstants {

    public static final boolean kArmInversion = false;
    public static final boolean kArmSensorPhase = true;
    public static final double kArmTalonDeadband = 0.004;

    public static final double kArmGravityFF = 1.83;
    public static final double kArmStaticFrictionFF = .52;
    public static final int kArmMotionMagicMaxAccel = 540;
    public static final int kArmMotionMagicCruiseVelocity = 540;

    public static final double kArmP = 4;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmF = 2.81;

    public static final double kArmAngleRatio = 1./4096. * 360 * 12. / 22.;
    public static final double kBacklashOffset = 2;

    public static final double kArmAngleOffset = -15;
    public static final double kSecondaryAngleOffset = 93 - kBacklashOffset;
    public static final double kEffectiveArmAngleOffset = kArmAngleOffset - kBacklashOffset;

    public static final double kArmLength = 14;
    public static final double kArmMaxAngle = 90;
    public static final double kArmMinAngle = -17;
    public static final double kArmMaxAngleRads = NerdyMath.degreesToRadians(kArmMaxAngle);
    public static final double kArmMinAngleRads = NerdyMath.degreesToRadians(kArmMinAngle);

    public static final double kArmDegPerS2 = kArmMotionMagicMaxAccel * kArmAngleRatio * 10.;
    public static final double kArmMaxDegPerS = kArmMotionMagicCruiseVelocity * kArmAngleRatio * 10.;

}

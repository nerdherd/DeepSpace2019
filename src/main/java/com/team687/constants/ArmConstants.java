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

    public static final boolean kArmInversion = true;
    public static final boolean kArmSensorPhase = true;
    public static final double kArmTalonDeadband = 0.004;

    public static final double kArmGravityFF = 0.765;
    public static final double kArmStaticFrictionFF = 0.5;
    public static final int kArmMotionMagicMaxAccel = 640;
    public static final int kArmMotionMagicCruiseVelocity = 640;

    public static final double kArmP = 4;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmF = 1.598;

    public static final double kArmAngleRatio = 1./4096. * 360 * 12. / 30.;
    public static final double kBacklashOffset = 0;

    public static final double kArmAngleOffset = -17;
    public static final double kEffectiveArmAngleOffset = kArmAngleOffset - kBacklashOffset;

    public static final double kArmLength = 14;
    public static final double kArmMaxAngle = 40;
    public static final double kArmMinAngle = -35;
    public static final double kArmMaxAngleRads = NerdyMath.degreesToRadians(kArmMaxAngle);
    public static final double kArmMinAngleRads = NerdyMath.degreesToRadians(kArmMinAngle);

    public static final double kArmDegPerS2 = kArmMotionMagicMaxAccel * kArmAngleRatio * 10.;
    public static final double kArmMaxDegPerS = kArmMotionMagicCruiseVelocity * kArmAngleRatio * 10.;

    public static final double kManualZeroVoltage = -1;

}

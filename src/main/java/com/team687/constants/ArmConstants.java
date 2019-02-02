/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.constants;

/**
 * Add your docs here.
 */
public class ArmConstants {

    public static final boolean kArmInversion = true;
    public static final boolean kArmSensorPhase = false;
    public static final double kArmTalonDeadband = 0.004;

    public static final double kArmGravityFF = 1.555;
    public static final double kArmStaticFrictionFF = 0.245;
    public static final int kArmMotionMagicMaxAccel = 260;
    public static final int kArmMotionMagicCruiseVelocity = 260;

    public static final double kArmP = 4;
    public static final double kArmI = 0;
    public static final double kArmD = 0;
    public static final double kArmF = 3.9;

    public static final double kArmAngleRatio = 1./4096. * 360 * 12. / 15.;
    public static final double kArmAngleOffset = -19;

}
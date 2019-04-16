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
public class ClimberConstants {
    public static final boolean kClimberInversion = false;

    public static final boolean kClimberSensorPhase = false;
    public static final double kClimberTalonDeadband = 0.004;

    // public static final double kClimberGravityFF = -0;
    public static final double kClimberGravityFF = 0;

    // public static final double kClimberStaticFrictionFF = -0;
    public static final double kClimberStaticFrictionFF = 0;

    public static final int kClimberMotionMagicMaxAccel = 0;
    public static final int kClimberMotionMagicCruiseVelocity = 0;

    public static final double kClimberP = 0;
    public static final double kClimberI = 0;
    public static final double kClimberD = 0;
    public static final double kClimberF = 0;

    // public static final double kClimberDistanceRatio = -1./4096. * 1.432 * Math.PI * 2;
    public static final double kClimberDistanceRatio = 1./4096. * 1.432 * Math.PI;

    public static final double kClimberHeightOffset = 2;

    public static final double kManualZeroVoltage = 0;

    public static final double kPositionTolerance = 2;

    public static final double kHardStopPos = 29.75;
    public static final double kClimbGoodPos = 7.75;

    public static final double kDesiredUpPow = 0.25;
    public static final double kDesiredLiftPow = -0.5;
    public static final double kSuckPower = 0.5;
}

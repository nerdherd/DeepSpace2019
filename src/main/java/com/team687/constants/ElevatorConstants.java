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
public class ElevatorConstants {
    //inverted kElevatorInversion, kElevatorGravityFF, kElevatorStaticFrictionFF, kElevatorDistanceRatio
    
    // public static final boolean kElevatorInversion = false;
    public static final boolean kElevatorInversion = true;

    public static final boolean kElevatorSensorPhase = true;
    public static final double kElevatorTalonDeadband = 0.004;

    // public static final double kElevatorGravityFF = -0;
    public static final double kElevatorGravityFF = 1.55;

    // public static final double kElevatorStaticFrictionFF = -0;
    public static final double kElevatorStaticFrictionFF = 0.5;

    public static final int kElevatorMotionMagicMaxAccel = 5080;
    public static final int kElevatorMotionMagicCruiseVelocity = 5080;

    public static final double kElevatorP = 0.5;
    public static final double kElevatorI = 0;
    public static final double kElevatorD = 0;
    public static final double kElevatorF = 0.201;

    // public static final double kElevatorDistanceRatio = -1./4096. * 1.432 * Math.PI * 2;
    public static final double kElevatorDistanceRatio = 1./4096. * 1.432 * Math.PI * 2;

    public static final double kDroopCompensation = 0;
    public static final double kElevatorHeightOffset = 9 - kDroopCompensation;

    public static final double kMaxElevatorHeight = 76;
    public static final double kMinElevatorHeight = 12;

    public static final double kElevatorInPerS2 = kElevatorMotionMagicMaxAccel * kElevatorDistanceRatio * 10.;
    public static final double kElevatorInPerS = kElevatorMotionMagicCruiseVelocity * kElevatorDistanceRatio * 10.;

    public static final double kManualZeroVoltage = -0.75;

}

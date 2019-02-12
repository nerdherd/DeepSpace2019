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

    // public static final double kElevatorGravityFF = -0.87;
    public static final double kElevatorGravityFF = 0.87;

    // public static final double kElevatorStaticFrictionFF = -0.43;
    public static final double kElevatorStaticFrictionFF = 0.43;

    public static final int kElevatorMotionMagicMaxAccel = 2800;
    public static final int kElevatorMotionMagicCruiseVelocity = 2800;

    public static final double kElevatorP = 0.5;
    public static final double kElevatorI = 0;
    public static final double kElevatorD = 0;
    public static final double kElevatorF = 0.266;

    // public static final double kElevatorDistanceRatio = -1./4096. * 1.432 * Math.PI * 2;
    public static final double kElevatorDistanceRatio = 1./4096. * 1.432 * Math.PI * 2;

    public static final double kDroopCompensation = 2;
    public static final double kElevatorHeightOffset = 13.75 - kDroopCompensation;

    public static final double kMaxElevatorHeight = 75;
    public static final double kMinElevatorHeight = 12;

}

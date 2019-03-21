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

    public static final double kP1 = 4;
    public static final double kI1 = 0;
    public static final double kD1 = 0;
    public static final double kF1 = 1.254;
    
    public static final double kP2 = 4;
    public static final double kI2 = 0;
    public static final double kD2 = 0;
    public static final double kF2 = 1.352;

    public static final double kStinger1BacklashOffset = 0;
    public static final double kStinger2BacklashOffset = 0;
    
    public static final double kStinger1AngleOffset = 63 - kStinger1BacklashOffset;
    public static final double kStinger2AngleOffset = 63 - kStinger2BacklashOffset;

    public static final double kStinger1AngleRatio = 1./4096. * 360;
    public static final double kStinger2AngleRatio = 1./4096. * 360;

    public static final double kStinger1StowAngle = 0;
    public static final double kStinger2StowAngle = 0;

    public static final double kLeftStingerGravityFF = -5.676;
    public static final double kLeftStingerStaticFF = 0;
    public static final double kRightStingerGravityFF = -6.167;
    public static final double kRightStingerStaticFF = 0;
    
    public static final int kLeftStingerMaxVel = 820;
    public static final int kRightStingerMaxVel = 750;
    public static final int kLeftStingerAccel = 820;
    public static final int kRightStingerAccel = 750;

    public static final double kStartClimbingAngle = 25;


    public static final double kClimbAngle = 18;
    
    // Percent output needed to lift robot from arms
    // public static final double kArmLiftFF = -0.3;

}

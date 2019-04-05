/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// Power up robots
	
    public static final int kRightMasterTalonID = 13;
    public static final int kLeftMasterTalonID = 20;

    public static final int kRightSlaveTalon1ID = 14;
    public static final int kLeftSlaveTalon1ID = 21;

    public static final int kRightSlaveTalon2ID = 15;
    public static final int kLeftSlaveTalon2ID = 22;

    public static final int kDrivetrainShifter1ID = 2;
    public static final int kDrivetrainShifter2ID = 5;

    // public static final int kChevalRampTalonID = 0;

    public static final int kArmTalonID = 11;
    public static final int kElevatorTalonID = 12;

    public static final int kLeftIntakeVictorID = 3;
    public static final int kRightIntakeVictorID = 2;

    public static final int kClawPiston1ID = 6;
    public static final int kClawPiston2ID = 1;

    public static final int kClimberTalonID = 50;
    public static final int kNerdyTalonID = 10;
    public static final int kVaccumID = 30;


}

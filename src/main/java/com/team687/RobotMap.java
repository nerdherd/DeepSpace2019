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
	
    public static final int kRightMasterTalonID = 2;
    public static final int kLeftMasterTalonID = 1;

    public static final int kRightSlaveVictor1ID = 3;
    public static final int kLeftSlaveVictor1ID = 1;

    public static final int kRightSlaveVictor2ID = 4;
    public static final int kLeftSlaveVictor2ID = 2;

    public static final int kDrivetrainShifter1ID = 2;
    public static final int kDrivetrainShifter2ID = 1;

    // public static final int kChevalRampTalonID = 0;

    public static final int kArmTalonID = 7;
    public static final int kElevatorTalonID = 6;

    public static final int kLeftIntakeVictorID = 7;
    public static final int kRightIntakeVictorID = 8;

    public static final int kClawPiston1ID = 3;
    public static final int kClawPiston2ID = 0;

}

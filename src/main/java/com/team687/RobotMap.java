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

    public static final int kRightSlaveTalon1ID = 16;
    public static final int kLeftSlaveTalon1ID = 14;

    public static final int kRightSlaveTalon2ID = 10;
    public static final int kLeftSlaveTalon2ID = 22;

    public static final int kRightMasterSparkMaxID = 0;
    public static final int kLeftMasterSparkMaxID = 0;

    public static final int kRightSlaveSparkMax1ID = 0;
    public static final int kLeftSlaveSparkMax1ID = 0;

    public static final int kRightSlaveSparkMax2ID = 0;
    public static final int kLeftSlaveSparkMax2ID = 0;

    public static final int kDrivetrainShifter1ID = 2;
    public static final int kDrivetrainShifter2ID = 5;

    // public static final int kChevalRampTalonID = 0;

    public static final int kArmTalonID = 11;
    public static final int kElevatorTalonID = 0;

    public static final int kLeftIntakeVictorID = 23;
    public static final int kRightIntakeVictorID = 2;

    public static final int kClawPiston1ID = 6;
    public static final int kClawPiston2ID = 1;

    public static final int kClimberTalonID = 99;
    public static final int kClimberTalon2ID = 98;
    public static final int kVaccumID = 400;
    public static final int kClimberRatchetForwardID = 0; // engage ratchet
    public static final int kClimberRatchetReverseID = 7; // disengage ratchet

    public static final int kUltrasonicEchoPort = 2;
    public static final int kUltrasonicPingPort = 1;


}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.AutoChooser;
import com.nerdherd.lib.misc.NerdyBadlog;
import com.nerdherd.lib.motor.dual.DualMotorIntake;
import com.nerdherd.lib.motor.single.SingleMotorTalonSRX;
import com.nerdherd.lib.motor.single.SingleMotorVictorSPX;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorElevator;
import com.nerdherd.lib.pneumatics.Piston;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Elevator;
import com.team687.subsystems.OI;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class Robot extends TimedRobot {

	public static final String kDate = "2019_01_29_";

	public static Drive drive;
	public static Subsystem livestream;
	public static DriverStation ds;
	public static AutoChooser chooser;
	public static SingleMotorElevator elevator;
	public static SingleMotorArm arm;
	public static SingleMotorVictorSPX leftIntake, rightIntake;
	// public static SingleMotorTalonSRX chevalRamp;
	public static DualMotorIntake intake;
	public static Piston claw;
	public static OI oi;


	@Override
	public void robotInit() {
		chooser = new AutoChooser();
	    drive = new Drive();
		ds = DriverStation.getInstance();
		claw = new Piston(RobotMap.kClawPiston1ID, RobotMap.kClawPiston2ID);
		elevator = Elevator.getInstance();
		arm = Arm.getInstance();

		leftIntake = new SingleMotorVictorSPX(RobotMap.kLeftIntakeVictorID, "LeftIntake", true);
		rightIntake = new SingleMotorVictorSPX(RobotMap.kRightIntakeVictorID, "RightIntake", true);
		intake = new DualMotorIntake(leftIntake, rightIntake);

		// chevalRamp = new SingleMotorTalonSRX(RobotMap.kChevalRampTalonID, "Cheval Ramp", true, true);
	
		oi = new OI();
		NerdyBadlog.initAndLog("/media/sda1/logs/", "Testing", 0.02, 
			elevator, arm);
	}

	@Override
	public void robotPeriodic() {
		elevator.reportToSmartDashboard();
		arm.reportToSmartDashboard();
	}

	@Override
	public void disabledInit() {
		drive.stopLog();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		drive.startLog();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		drive.logToCSV();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.AutoChooser;
import com.nerdherd.lib.misc.LoggableLambda;
import com.nerdherd.lib.misc.NerdyBadlog;
import com.nerdherd.lib.motor.dual.DualMotorIntake;
import com.nerdherd.lib.motor.single.SingleMotorVictorSPX;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorElevator;
import com.nerdherd.lib.pneumatics.Piston;
import com.nerdherd.lib.sensor.HallSensor;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Elevator;

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
	// public static SingleMotorTalonSRX chevalRamp;
	public static DualMotorIntake intake;
	public static Piston claw;
	public static OI oi;
	// big yummy
	public static HallSensor armHallEffect;


	@Override
	public void robotInit() {
		chooser = new AutoChooser();
	    drive = new Drive();
		ds = DriverStation.getInstance();
		claw = new Piston(RobotMap.kClawPiston1ID, RobotMap.kClawPiston2ID);
		elevator = Elevator.getInstance();
		arm = Arm.getInstance();
		armHallEffect = new HallSensor(1, "ArmHallEffect", true);

		intake = new DualMotorIntake(new SingleMotorVictorSPX(RobotMap.kLeftIntakeVictorID, "LeftIntake", true), 
									new SingleMotorVictorSPX(RobotMap.kRightIntakeVictorID, "RightIntake", true));

		// chevalRamp = new SingleMotorTalonSRX(RobotMap.kChevalRampTalonID, "Cheval Ramp", true, true);

		LoggableLambda armClosedLoopError = new LoggableLambda("ArmClosedLoopError",
				() -> (double) arm.motor.getClosedLoopError());
		LoggableLambda elevatorClosedLoopError = new LoggableLambda("ElevatorClosedLoopError",
				() -> (double) arm.motor.getClosedLoopError());
	
		oi = new OI();
		NerdyBadlog.initAndLog("/media/sda1/logs/", "testingAt4201_", 0.02, 
			elevator, arm, armClosedLoopError, elevatorClosedLoopError, armHallEffect);
	}

	@Override
	public void robotPeriodic() {
		elevator.reportToSmartDashboard();
		arm.reportToSmartDashboard();
		armHallEffect.reportToSmartDashboard();
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
		drive.setCoastMode();
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

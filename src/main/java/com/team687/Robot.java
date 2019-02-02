/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.NerdyBadlog;
import com.nerdherd.lib.motor.single.SingleMotorTalonSRX;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorElevator;
import com.nerdherd.lib.pneumatics.Piston;
import com.team687.constants.ArmConstants;
import com.team687.constants.ElevatorConstants;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Jevois;
import com.team687.utilities.AutoChooser;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class Robot extends TimedRobot {
	
	public static final String kDate = "2018_09_29_";

	public static Drive drive;
	public static Jevois jevois;
	public static Subsystem livestream;
	public static DriverStation ds;
	public static AutoChooser autoChooser;
	public static SingleMotorElevator elevator;
	public static SingleMotorArm arm;
	public static SingleMotorTalonSRX intake;
	public static Piston claw;
	public static OI oi;

	@Override
	public void robotInit() {
		autoChooser = new AutoChooser();
	    // jevois = new Jevois(115200, SerialPort.Port.kUSB);
	    // livestream = new Streamer();
	    drive = new Drive();
			ds = DriverStation.getInstance();

			claw = new Piston(4, 3);
			
			elevator = new SingleMotorElevator(RobotMap.kElevatorTalonID, "Elevator",
				ElevatorConstants.kElevatorInversion, ElevatorConstants.kElevatorSensorPhase);
			elevator.configTalonDeadband(ElevatorConstants.kElevatorTalonDeadband);
			elevator.configFFs(ElevatorConstants.kElevatorGravityFF, 
				ElevatorConstants.kElevatorStaticFrictionFF);
			elevator.configMotionMagic(ElevatorConstants.kElevatorMotionMagicMaxAccel,
				ElevatorConstants.kElevatorMotionMagicCruiseVelocity);
			elevator.configPIDF(ElevatorConstants.kElevatorP, ElevatorConstants.kElevatorI,
				ElevatorConstants.kElevatorD, ElevatorConstants.kElevatorF);
			elevator.configHeightConversion(ElevatorConstants.kElevatorDistanceRatio,
				ElevatorConstants.kElevatorHeightOffset);

			arm = new SingleMotorArm(RobotMap.kArmTalonID, "Arm", 
				ArmConstants.kArmInversion, ArmConstants.kArmSensorPhase);
			arm.configTalonDeadband(ArmConstants.kArmTalonDeadband);
			arm.configFFs(ArmConstants.kArmGravityFF, ArmConstants.kArmStaticFrictionFF);
			arm.configMotionMagic(ArmConstants.kArmMotionMagicMaxAccel, 
				ArmConstants.kArmMotionMagicCruiseVelocity);
			arm.configPIDF(ArmConstants.kArmP, ArmConstants.kArmI, 
				ArmConstants.kArmD, ArmConstants.kArmF);
			arm.configAngleConversion(ArmConstants.kArmAngleRatio, ArmConstants.kArmAngleOffset);

			intake = new SingleMotorTalonSRX(RobotMap.kIntakeTalonID, "Intake", true, true);
		
			oi = new OI();

			NerdyBadlog.initAndLog("/media/sda1/logs/2_1_19_elevatorTesting8.csv", 0.02, elevator, arm);
	}

	@Override
	public void robotPeriodic() {
		elevator.reportToSmartDashboard();
		arm.reportToSmartDashboard();
		intake.reportToSmartDashboard();
	}

	@Override
	public void disabledInit() {
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
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}

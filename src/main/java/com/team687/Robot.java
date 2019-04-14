/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.nerdherd.lib.logging.LoggableLambda;
import com.nerdherd.lib.logging.NerdyBadlog;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.dual.DualMotorIntake;
import com.nerdherd.lib.motor.single.SingleMotorTalonSRX;
import com.nerdherd.lib.motor.single.SingleMotorVictorSPX;
import com.nerdherd.lib.pneumatics.Piston;
import com.nerdherd.lib.sensor.PressureSensor;
import com.nerdherd.lib.sensor.VexUltrasonic;
import com.team687.constants.ArmConstants;
import com.team687.constants.ElevatorConstants;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Climber;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Elevator;
import com.team687.subsystems.Jevois;
import com.team687.subsystems.Limelight;
import com.team687.subsystems.Superstructure;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class Robot extends TimedRobot {

	public static final String kDate = "2019_04_13_";

	public static Drive drive;
	public static DriverStation ds;
	public static DeepSpaceAutoChooser chooser;

	// public static SingleMotorTalonSRX chevalRamp;
	public static DualMotorIntake intake;
	public static Piston claw;
	public static PressureSensor pressureSensor;
	// public static LED led;
	public static Jevois jevois;
	public static ResetSingleMotorEncoder armZero;
	public static ResetSingleMotorEncoder elevatorZero;
	public static SingleMotorVictorSPX vacuum;
	public static Limelight limelight;
	private static boolean hasBeenTeleop = false;
	private static boolean hasBeenSandstorm = false;
	public static VexUltrasonic ultrasonic;
	public static OI oi;

	
	// big yummy
	// public static HallSensor armHallEffect;

	public static Command autoCommand;

	@Override
	public void robotInit() {
		LiveWindow.disableAllTelemetry();
		// led = new LED();
		jevois = new Jevois(115200, SerialPort.Port.kUSB);
		jevois.startCameraStream();
		limelight = new Limelight();
		pressureSensor = new PressureSensor("PressureSensor", 3);

		chooser = new DeepSpaceAutoChooser();
	    drive = new Drive();
		ds = DriverStation.getInstance();
		claw = new Piston(RobotMap.kClawPiston1ID, RobotMap.kClawPiston2ID);
		// armHallEffect = new HallSensor(1, "ArmHallEffect", true);
		
		intake = new DualMotorIntake(new SingleMotorVictorSPX(RobotMap.kLeftIntakeVictorID, "LeftIntake", true), 
									new SingleMotorVictorSPX(RobotMap.kRightIntakeVictorID, "RightIntake", true));
		
		armZero = new ResetSingleMotorEncoder(Arm.getInstance());
		armZero.setRunWhenDisabled(true);
		elevatorZero = new ResetSingleMotorEncoder(Elevator.getInstance());
		elevatorZero.setRunWhenDisabled(true);

		vacuum = new SingleMotorVictorSPX(RobotMap.kVaccumID, "Climber", false);
		// chevalRamp = new SingleMotorTalonSRX(RobotMap.kChevalRampTalonID, "Cheval Ramp", true, true);

		LoggableLambda armClosedLoopError = new LoggableLambda("ArmClosedLoopError",
			() -> (double) Arm.getInstance().motor.getClosedLoopError());
		LoggableLambda elevatorClosedLoopError = new LoggableLambda("ElevatorClosedLoopError",
			() -> (double) Arm.getInstance().motor.getClosedLoopError());
	
		ultrasonic = new VexUltrasonic("ultrasonic", RobotMap.kUltrasonicPingPort, RobotMap.kUltrasonicEchoPort);
		oi = new OI();
		NerdyBadlog.initAndLog("/media/sda1/logs/", "climber2_electricboogaloo_", 0.02, 
			Elevator.getInstance(),
			Arm.getInstance(), 
			Superstructure.getInstance(),
			Climber.getInstance(),
			intake);//, drive);
		//CameraServer.getInstance().startAutomaticCapture();
		drive.startLog();
		// jevois.startLog();
		drive.setBrakeMode();
	}

	@Override
	public void robotPeriodic() {
		Elevator.getInstance().reportToSmartDashboard();
		Arm.getInstance().reportToSmartDashboard();
		jevois.reportToSmartDashboard();
		oi.reportToSmartDashboard();
		Climber.getInstance().reportToSmartDashboard();
		// pressureSensor.reportToSmartDashboard();
		// armHallEffect.reportToSmartDashboard();
		Superstructure.getInstance().reportToSmartDashboard();
		SmartDashboard.putBoolean("Claw is forwards?", Robot.claw.isForwards());
		SmartDashboard.putBoolean("Claw is reverse?", Robot.claw.isReverse());
		// if ((!hasBeenSandstorm || !hasBeenTeleop) && !ds.isDisabled()) {
		drive.logToCSV();
		// jevois.logToCSV();
		// }
		ultrasonic.reportToSmartDashboard();

	}

	@Override
	public void disabledInit() {
		
		// jevois.enableStream();	
		if(hasBeenSandstorm && hasBeenTeleop) {
			drive.stopLog();
			// jevois.stopLog();
			hasBeenSandstorm = false;
			hasBeenTeleop = false;
		}
		Superstructure.getInstance().isHatchMode = true;
		drive.setCoastMode();
	}

	@Override
	public void disabledPeriodic() {
		// jevois.reportToSmartDashboard();
		drive.reportToSmartDashboard();
		if (oi.driveJoyLeft.getRawButton(5) && oi.driveJoyRight.getRawButton(5)) {
			Arm.getInstance().configAngleOffset(ArmConstants.kEffectiveArmAngleOffset);
			Elevator.getInstance().configDistanceOffset(ElevatorConstants.kElevatorHeightOffset);
			Scheduler.getInstance().add(armZero);
			Scheduler.getInstance().add(elevatorZero);
			drive.resetEncoders();
			drive.resetYaw();
		}

		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// drive.setBrakeMode();
		// autoCommand = chooser.getSelectedAuto();
		// autoCommand = new FrontCargoShip();
		// if (autoCommand != null) {
		// 	autoCommand.start();
		// }
		if (hasBeenTeleop) {
			hasBeenTeleop = false;
		}
		hasBeenSandstorm = true;	
		Superstructure.getInstance().isHatchMode = true;	
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		// if (oi.driveJoyLeft.getRawButton(0) && oi.driveJoyRight.getRawButton(0)) {
		// 	autoCommand.cancel();
		// }
	}

	@Override
	public void teleopInit() {
		
		// jevois.startLog();
		// drive.startLog();
		// drive.setCoastMode();
		drive.setBrakeMode();
		hasBeenTeleop = true;
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		// jevois.reportToSmartDashboard();
		// pressureSensor.reportToSmartDashboard();
		drive.reportToSmartDashboard();


		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}

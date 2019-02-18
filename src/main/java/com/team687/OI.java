package com.team687;

import com.nerdherd.lib.drivetrain.shifting.ShiftHigh;
import com.nerdherd.lib.drivetrain.shifting.ShiftLow;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.commands.SetDualMotorPower;
import com.nerdherd.lib.motor.commands.mechanisms.SetArmAngleMotionMagic;
import com.nerdherd.lib.motor.commands.mechanisms.SetElevatorHeightMotionMagic;
import com.nerdherd.lib.oi.DefaultOI;
import com.nerdherd.lib.pneumatics.commands.ExtendPiston;
import com.nerdherd.lib.pneumatics.commands.RetractPiston;
import com.team687.commands.superstructure.SetHatchMode;
import com.team687.commands.superstructure.StateAwareOptimized;
import com.team687.commands.superstructure.SuperstructureIntake;
import com.team687.commands.superstructure.TeleopOptimizedSimultaneous;
import com.team687.commands.superstructure.ToggleHatchMode;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI extends DefaultOI {

	// independent elevator and arm
	// public JoystickButton intakeArm_1, outtakeRollers_2, stopRollers_3, intakeRollers_4, 
	// clawClose_5, clawOpen_6, highElevator_7, cargoArm_8, midElevator_9, hatchArm_10, lowElevator_11;

	// 6 rocket positions (too few buttons, probably shouldn't use this)
	// public JoystickButton highHatch_7, highCargo_8, lowHatch_11, midCargo_10, intakeArm_4,
	// 	intakeRollers_1, outtakeRollers_2, stopRollers_3, clawClose_5, clawOpen_6, lowCargo_12,
	// 	midHatch_9;

	// hatch/cargo "modes" (4 buttons for whole rocket, +1 if you include cargo intake)
	public JoystickButton intakeArm_1, outtakeRollers_2, stopRollers_3, intakeRollers_4, clawClose_5, 
	clawOpen_6, highElevator_7, cargoMode_8, midElevator_9, hatchMode_10, lowElevator_11, toggleMode_12;
	
	// State aware claw (3 buttons for all of rocket, but relies on closed claw = cargo and open claw = cargo)
	// public JoystickButton intakeArm_1, outtakeRollers_2, stopRollers_3, intakeRollers_4, clawClose_5, 
	// clawOpen_6, highElevator_7, midElevator_9, lowElevator_11;

	// public JoystickButton deployChevalRamps_, deployKickerWheels_,
	// retractChevalRamps_, retractKickerWheels_;
	
	public OI() {
		super();

		// intakeArm_1 = new JoystickButton(super.operatorJoy, 1);
		// outtakeRollers_2 = new JoystickButton(super.operatorJoy, 2);
		// stopRollers_3 = new JoystickButton(super.operatorJoy, 3);
		// intakeRollers_4 = new JoystickButton(super.operatorJoy, 4);
		// clawClose_5 = new JoystickButton(super.operatorJoy, 5);
		// clawOpen_6 = new JoystickButton(super.operatorJoy, 6);
		// highElevator_7 = new JoystickButton(super.operatorJoy, 7);
		// cargoArm_8 = new JoystickButton(super.operatorJoy, 8);
		// midElevator_9 = new JoystickButton(super.operatorJoy, 9);
		// hatchArm_10 = new JoystickButton(super.operatorJoy, 10);
		// lowElevator_11 = new JoystickButton(super.operatorJoy, 11);

		// intakeArm_1.whenPressed(new SuperstructureIntake());
		// intakeRollers_4.whenPressed(new SetDualMotorPower(Robot.intake, .75, -0.75));
		// outtakeRollers_2.whenPressed(new SetDualMotorPower(Robot.intake, -.75, 0.75));
		// stopRollers_3.whenPressed(new SetDualMotorPower(Robot.intake, 0, 0));
		// clawClose_5.whenPressed(new ExtendPiston(Robot.claw));
		// clawOpen_6.whenPressed(new RetractPiston(Robot.claw));
		// highElevator_7.whenPressed(new SetElevatorHeightMotionMagic(Robot.elevator, 70.6));
		// cargoArm_8.whenPressed(new SetArmAngleMotionMagic(Robot.arm, 70));
		// midElevator_9.whenPressed(new SetElevatorHeightMotionMagic(Robot.elevator, 44));
		// hatchArm_10.whenPressed(new SetArmAngleMotionMagic(Robot.arm, 20));
		// lowElevator_11.whenPressed(new SetElevatorHeightMotionMagic(Robot.elevator, 16));

		// intakeRollers_1 = new JoystickButton(super.operatorJoy, 1);
		// outtakeRollers_2 = new JoystickButton(super.operatorJoy, 2);
		// stopRollers_3 = new JoystickButton(super.operatorJoy, 3);
		// intakeArm_4 = new JoystickButton(super.operatorJoy, 4);
		// clawClose_5 = new JoystickButton(super.operatorJoy, 5);
		// clawOpen_6 = new JoystickButton(super.operatorJoy, 6);
		// highHatch_7 = new JoystickButton(super.operatorJoy, 7);
		// highCargo_8 = new JoystickButton(super.operatorJoy, 8);
		// lowHatch_11 = new JoystickButton(super.operatorJoy, 11);
		// midCargo_10 = new JoystickButton(super.operatorJoy, 10);
		// lowCargo_12 = new JoystickButton(super.operatorJoy, 12);
		// midHatch_9 = new JoystickButton(super.operatorJoy, 9);

		// intakeRollers_1.whenPressed(new SetDualMotorPower(Robot.intake, .75, -0.75));
		// outtakeRollers_2.whenPressed(new SetDualMotorPower(Robot.intake, -.75, 0.75));
		// stopRollers_3.whenPressed(new SetDualMotorPower(Robot.intake, 0, 0));
		// intakeArm_4.whenPressed(new OptimizedSimultaneousMovement(8));
		// clawClose_5.whenPressed(new ExtendPiston(Robot.claw));
		// clawOpen_6.whenPressed(new RetractPiston(Robot.claw));
		// highHatch_7.whenPressed(new OptimizedSimultaneousMovement(76));
		// highCargo_8.whenPressed(new OptimizedSimultaneousMovement(84));
		// midHatch_9.whenPressed(new OptimizedSimultaneousMovement(48));
		// midCargo_10.whenPressed(new OptimizedSimultaneousMovement(56));
		// lowHatch_11.whenPressed(new OptimizedSimultaneousMovement(20));

		// lowCargo_12.whenPressed(new OptimizedSimultaneousMovement(28));

		intakeArm_1 = new JoystickButton(super.operatorJoy, 1);
		outtakeRollers_2 = new JoystickButton(super.operatorJoy, 2);
		stopRollers_3 = new JoystickButton(super.operatorJoy, 3);
		intakeRollers_4 = new JoystickButton(super.operatorJoy, 4);
		clawClose_5 = new JoystickButton(super.operatorJoy, 5);
		clawOpen_6 = new JoystickButton(super.operatorJoy, 6);
		highElevator_7 = new JoystickButton(super.operatorJoy, 7);
		cargoMode_8 = new JoystickButton(super.operatorJoy, 8);
		midElevator_9 = new JoystickButton(super.operatorJoy, 9);
		hatchMode_10 = new JoystickButton(super.operatorJoy, 10);
		lowElevator_11 = new JoystickButton(super.operatorJoy, 11);
		toggleMode_12 = new JoystickButton(super.operatorJoy, 12);

		intakeArm_1.whenPressed(new SuperstructureIntake());
		outtakeRollers_2.whenPressed(new SetDualMotorPower(Robot.intake, -.75, 0.75));
		stopRollers_3.whenPressed(new SetDualMotorPower(Robot.intake, 0, 0));
		intakeRollers_4.whenPressed(new SetDualMotorPower(Robot.intake, .75, -0.75));
		clawClose_5.whenPressed(new ExtendPiston(Robot.claw));
		clawOpen_6.whenPressed(new RetractPiston(Robot.claw));
		highElevator_7.whenPressed(new TeleopOptimizedSimultaneous(76));
		cargoMode_8.whenPressed(new SetHatchMode(false));
		midElevator_9.whenPressed(new TeleopOptimizedSimultaneous(48));
		hatchMode_10.whenPressed(new SetHatchMode(true));
		lowElevator_11.whenPressed(new TeleopOptimizedSimultaneous(20));
		toggleMode_12.whenPressed(new ToggleHatchMode());

		// intakeArm_1 = new JoystickButton(super.operatorJoy, 1);
		// outtakeRollers_2 = new JoystickButton(super.operatorJoy, 2);
		// stopRollers_3 = new JoystickButton(super.operatorJoy, 3);
		// intakeRollers_4 = new JoystickButton(super.operatorJoy, 4);
		// clawClose_5 = new JoystickButton(super.operatorJoy, 5);
		// clawOpen_6 = new JoystickButton(super.operatorJoy, 6);
		// highElevator_7 = new JoystickButton(super.operatorJoy, 7);
		// midElevator_9 = new JoystickButton(super.operatorJoy, 9);
		// lowElevator_11 = new JoystickButton(super.operatorJoy, 11);

		// intakeArm_1.whenPressed(new SuperstructureIntake());
		// outtakeRollers_2.whenPressed(new SetDualMotorPower(Robot.intake, -.75, 0.75));
		// stopRollers_3.whenPressed(new SetDualMotorPower(Robot.intake, 0, 0));
		// intakeRollers_4.whenPressed(new SetDualMotorPower(Robot.intake, .75, -0.75));
		// clawClose_5.whenPressed(new ExtendPiston(Robot.claw));
		// clawOpen_6.whenPressed(new RetractPiston(Robot.claw));
		// highElevator_7.whenPressed(new StateAwareOptimized(76));
		// midElevator_9.whenPressed(new StateAwareOptimized(48));
		// lowElevator_11.whenPressed(new StateAwareOptimized(20));


		SmartDashboard.putData("High Speed", new ShiftHigh(Robot.drive));
		SmartDashboard.putData("Low Speed", new ShiftLow(Robot.drive));

		// SmartDashboard.putData("Voltage Ramp", new DriveCharacterizationTest(Robot.drive, 0.25));
		// SmartDashboard.putData("Reset Encoder", new ResetDriveEncoders(Robot.drive));
		// SmartDashboard.putData("Reset Gyro", new ResetGyro(Robot.drive));
		// SmartDashboard.putData("Drive 3 V", new OpenLoopDrive(Robot.drive, 0.25));
		// SmartDashboard.putData("Drive Motion Magic", new DriveDistanceMotionMagic(Robot.drive, 25000, 1000, 1000));
		// SmartDashboard.putData("Set Velocity", new VelocityTest(Robot.drive, 5000, 5));
		// SmartDashboard.putData("Right Rocket", new DriveFalconTrajectory(Robot.drive, AutoConstants.RightRocketPath1, 3, true, 0.0984, 0.00224));
		// SmartDashboard.putData("Right Rocket 2", new DriveFalconTrajectory(Robot.drive, AutoConstants.RightRocketPath2, 3, false, 0.0984, 0.00011));

		// SmartDashboard.putData("Turn 180 deg", new TurnAngle(Robot.drive, 180, 1, 5, 0.0025, 0.00042));
		// SmartDashboard.putData("Turn 0 deg", new TurnAngle(Robot.drive, 0, 1, 5, 0.009, 0.0004));

		// SmartDashboard.putData("Voltage ramp elevator", new MotorVoltageRamping(Robot.elevator, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp elevator with FF up", new MechanismVoltageRampingWithFF(Robot.elevator, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp elevator with FF down", new MechanismVoltageRampingWithFF(Robot.elevator, -0.25 / 12.0));

		SmartDashboard.putData("Reset elevator encoder", new ResetSingleMotorEncoder(Robot.elevator));

		// SmartDashboard.putData("Elevator MM 52 in", new SetElevatorHeightMotionMagic(Robot.elevator, 42));
		// SmartDashboard.putData("Elevator MM 60 in", new SetElevatorHeightMotionMagic(Robot.elevator, 60));
		// SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Robot.elevator, 75));
		SmartDashboard.putData("Elevator MM 16 in", new SetElevatorHeightMotionMagic(Robot.elevator, 16));
		// SmartDashboard.putData("Elevator MM 42 in", new SetElevatorHeightMotionMagic(Robot.elevator, 52));
		//SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Robot.elevator, 75));

		// SmartDashboard.putData("A waste of my time hatch 1", new SimultaneousMovement(18, 14.5));
		// SmartDashboard.putData("A waste of my time hatch 2", new SimultaneousMovement(45.5, 14.5));
		// SmartDashboard.putData("A waste of my time hatch 3", new SimultaneousMovement(71.5, 14.5));
		// SmartDashboard.putData("A waste of my time cargo 1", new SimultaneousMovement(18, 59));
		// SmartDashboard.putData("A waste of my time cargo 2", new SimultaneousMovement(45.5, 59));
		// SmartDashboard.putData("A waste of my time cargo 3", new SimultaneousMovement(71.5, 59));
		// SmartDashboard.putData("Test resting state", new SimultaneousMovement(16, 0));
		// SmartDashboard.putData("Huggable intake", new SimultaneousMovement(14, -30));
		
		// SmartDashboard.putData("Voltage ramp arm", new MotorVoltageRamping(Robot.arm, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp arm up with FF", new MechanismVoltageRampingWithFF(Robot.arm, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp arm down with FF", new MechanismVoltageRampingWithFF(Robot.arm, -0.25 / 12.0));
		// // SmartDashboard.putData("Set arm voltage 0", new SetMotorPower(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle 0 deg", new SetArmAngleMotionMagic(Robot.arm, 0));
		// SmartDashboard.putData("Set arm angle -30 deg", new SetArmAngleMotionMagic(Robot.arm, -30));
		// SmartDashboard.putData("Set arm angle 67 deg", new SetArmAngleMotionMagic(Robot.arm, 67));
		// SmartDashboard.putData("Set arm angle 32 deg", new SetArmAngleMotionMagic(Robot.arm, 32));
		// SmartDashboard.putData("Set arm angle 45 deg", new SetArmAngleMotionMagic(Robot.arm, 45));
		// SmartDashboard.putData("Set arm angle 22 deg", new SetArmAngleMotionMagic(Robot.arm, 22));
		SmartDashboard.putData("Reset arm encoder", new ResetSingleMotorEncoder(Robot.arm));
		// SmartDashboard.putData("Zero arm with hall effect", new ZeroMechanismWithHallEffect(Robot.arm, 
		// 						Robot.armHallEffect, 2./12.));

		// SmartDashboard.putData("Set both intake sides 3V", new SetMotorPower(Robot.intake, .25));
		// SmartDashboard.putData("Set both intake sides -3V", new SetMotorPower(Robot.intake, -.25));
		// SmartDashboard.putData("Stop rollers", new SetMotorPower(Robot.intake, 0));

		SmartDashboard.putData("Piston extend ?", new ExtendPiston(Robot.claw));
		SmartDashboard.putData("Piston retract? ?", new RetractPiston(Robot.claw));

		// SmartDashboard.putData("Elevator set position 0", new SetElevatorHeightMotionMagic(Robot.elevator, 11.75));

		// SmartDashboard.putData("Elevator zero with tach", new ZeroMechanismWithHallEffect(Robot.elevator, Robot.elevatorTach, -1));

		// SmartDashboard.putData("Set Optimized height 60", new OptimizedSimultaneousMovement(60));
		// SmartDashboard.putData("Set Optimized height 80", new OptimizedSimultaneousMovement(80));
		// SmartDashboard.putData("Set Optimized height 7", new OptimizedSimultaneousMovement(7));

		// SmartDashboard.putData("Optimized hatch 1", new OptimizedSimultaneousMovement(21.5));
		// SmartDashboard.putData("Optimized hatch 2", new OptimizedSimultaneousMovement(49));
		// SmartDashboard.putData("Optimized hatch 3", new OptimizedSimultaneousMovement(75));
		// SmartDashboard.putData("Optimized cargo 1", new OptimizedSimultaneousMovement(30));
		// SmartDashboard.putData("Optimized cargo 2", new OptimizedSimultaneousMovement(57.5));
		// SmartDashboard.putData("Optimized cargo 3", new OptimizedSimultaneousMovement(83.5));

	}
}
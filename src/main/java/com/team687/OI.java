package com.team687;

import com.nerdherd.lib.drivetrain.auto.ResetDriveEncoders;
import com.nerdherd.lib.drivetrain.auto.ResetGyro;
import com.nerdherd.lib.drivetrain.characterization.DriveCharacterizationTest;
import com.nerdherd.lib.drivetrain.shifting.ShiftHigh;
import com.nerdherd.lib.drivetrain.shifting.ShiftLow;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.commands.SetDualMotorPower;
import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.nerdherd.lib.oi.DefaultOI;
import com.nerdherd.lib.pneumatics.commands.ExtendPiston;
import com.nerdherd.lib.pneumatics.commands.RetractPiston;
import com.team687.commands.auto.LeftRocketNear;
import com.team687.commands.climber.ClimberReady;
import com.team687.commands.climber.SuckAndLift;
import com.team687.commands.superstructure.CargoShipCargo;
import com.team687.commands.superstructure.IntakeOrOuttakeRollers;
import com.team687.commands.superstructure.Stow;
import com.team687.commands.superstructure.SuperstructureIntake;
import com.team687.commands.superstructure.TeleopSimultaneous;
import com.team687.commands.superstructure.Thrust;
import com.team687.commands.superstructure.ToggleClaw;
import com.team687.commands.superstructure.ToggleHatchMode;
import com.team687.commands.superstructure.ZeroSuperstructure;
import com.team687.commands.vision.TargetTrack;
import com.team687.constants.SuperstructureConstants;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Climber;
import com.team687.subsystems.Elevator;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI extends DefaultOI {

	// hatch/cargo "modes" (4 buttons for whole rocket, +1 if you include cargo
	// intake)
	public JoystickButton intakeArm_1, outtakeRollers_2, stopRollers_3, intakeRollers_4, clawToggle_5, thrust_6,
			highElevator_7, cargoShip_8, midElevator_9, stow_10, lowElevator_11, toggleMode_12, targetTrack_L1,
			targetTrack_R1, shiftHighSpeed_R4, shiftLowSpeed_R3, zeroSuperstructure_L11, climberClimb_R7,
			toggleClimbMode_L7, climberClimb_R8, shiftHigh_11, shiftLow_12; // clawClose_6, clawOpen_5,

	public OI() {
		// super();
		super(0.35);
		super.configLerping(true);

		intakeArm_1 = new JoystickButton(super.operatorJoy, 1);
		outtakeRollers_2 = new JoystickButton(super.operatorJoy, 2);
		stopRollers_3 = new JoystickButton(super.operatorJoy, 3);
		intakeRollers_4 = new JoystickButton(super.operatorJoy, 4);
		// clawOpen_5 = new JoystickButton(super.operatorJoy, 5);
		// clawClose_6 = new JoystickButton(super.operatorJoy, 6);
		clawToggle_5 = new JoystickButton(super.operatorJoy, 5);
		thrust_6 = new JoystickButton(super.operatorJoy, 6);
		highElevator_7 = new JoystickButton(super.operatorJoy, 7);
		cargoShip_8 = new JoystickButton(super.operatorJoy, 8);
		midElevator_9 = new JoystickButton(super.operatorJoy, 9);
		stow_10 = new JoystickButton(super.operatorJoy, 10);
		lowElevator_11 = new JoystickButton(super.operatorJoy, 11);
		toggleMode_12 = new JoystickButton(super.operatorJoy, 12);

		targetTrack_L1 = new JoystickButton(super.driveJoyLeft, 1);
		toggleClimbMode_L7 = new JoystickButton(super.driveJoyLeft, 7);
		zeroSuperstructure_L11 = new JoystickButton(super.driveJoyLeft, 11);
		
		targetTrack_R1 = new JoystickButton(super.driveJoyRight, 1);
		shiftHighSpeed_R4 = new JoystickButton(super.driveJoyRight, 4);
		shiftLowSpeed_R3 = new JoystickButton(super.driveJoyRight, 3);
		climberClimb_R7 = new JoystickButton(super.driveJoyRight, 7);
		climberClimb_R8 = new JoystickButton(super.driveJoyRight, 8);

		shiftHigh_11 = new JoystickButton(super.driveJoyRight, 11);
		shiftLow_12 = new JoystickButton(super.driveJoyRight, 12);
		// targetTrack_L1 = super.driverController.aButton;
		// toggleClimbMode_L7 = super.driverController.bButton;
		// zeroSuperstructure_L11 = super.driverController.yButton;
		
		// targetTrack_R1 = super.driverController.xButton;
		// shiftHighSpeed_R4 = super.driverController.leftBumper;
		// shiftLowSpeed_R3 = super.driverController.rightBumper;
		// climberClimb_R7 = super.driverController.upDPad;
		// climberClimb_R8 = new JoystickButton(super.driveJoyRight, 8);

		intakeArm_1.whenPressed(new SuperstructureIntake());
		outtakeRollers_2.whenPressed(new IntakeOrOuttakeRollers(-.4, 0.4));
		stopRollers_3.whenPressed(new SetDualMotorPower(Robot.intake, 0, 0));
		intakeRollers_4.whenPressed(new IntakeOrOuttakeRollers(.75, -0.75));
		// clawOpen_5.whenPressed(new StopIntaking());
		// clawClose_6.whenPressed(new ExtendPiston(Robot.claw));
		clawToggle_5.whenPressed(new ToggleClaw());
		thrust_6.whenPressed(new Thrust(14));
		highElevator_7.whenPressed(new TeleopSimultaneous(SuperstructureConstants.kHighElHeight));
		cargoShip_8.whenPressed(new CargoShipCargo());
		midElevator_9.whenPressed(new TeleopSimultaneous(SuperstructureConstants.kMidElHeight));
		stow_10.whenPressed(new Stow());
		lowElevator_11.whenPressed(new TeleopSimultaneous(SuperstructureConstants.kLowElHeight));
		toggleMode_12.whenPressed(new ToggleHatchMode());

		SmartDashboard.putData("Stow", new Stow());

		SmartDashboard.putData("Left Rocket", new LeftRocketNear());

		// targetTrack_L1.whileHeld(new ClimbForwardsElseVisionTrack());
		//targetTrack_L1.whileHeld(new TargetTrack(0.008, 0.0));
		targetTrack_L1.whileHeld(new TargetTrack(0.01));
		// toggleClimbMode_L7.whenPressed(new ToggleClimbMode());
		zeroSuperstructure_L11.whileHeld(new ZeroSuperstructure(
			SuperstructureConstants.kArmZeroVoltage, SuperstructureConstants.kElZeroVoltage));

		// targetTrack_R1.whileHeld(new ClimbDeployElseVisionTrack());
		//targetTrack_R1.whileHeld(new TargetTrack(0.008, 0.00));
		targetTrack_R1.whileHeld(new TargetTrack(0.01));
		shiftHighSpeed_R4.whenPressed(new ShiftHigh(Robot.drive));
		shiftLowSpeed_R3.whenPressed(new ShiftLow(Robot.drive));
		climberClimb_R7.whenPressed(new ClimberReady());
		climberClimb_R8.whileHeld(new SuckAndLift());

		// shiftHigh_11.whenPressed(new ShiftHigh(Robot.drive));
		// shiftLow_12.whenPressed(new ShiftLow(Robot.drive));
		SmartDashboard.putData("High Speed", new ShiftHigh(Robot.drive));
		SmartDashboard.putData("Low Speed", new ShiftLow(Robot.drive));

		SmartDashboard.putData("Drive Voltage Ramp", new DriveCharacterizationTest(Robot.drive, 0.25));
		// SmartDashboard.putData("Intake Rollers", new IntakeOrOuttakeRollers(0.5, -0.5));
		// SmartDashboard.putData("Climber foot extend?", new ExtendPiston(Climber.getInstance()Foot));
		// SmartDashboard.putData("Climber foot retract?", new RetractPiston(Climber.getInstance()Foot));
		// SmartDashboard.putData("Reset Left Stinger", new ResetSingleMotorEncoder(Robot.climbStingerLeft));
		// SmartDashboard.putData("Reset Right Stinger", new ResetSingleMotorEncoder(Robot.climbStingerRight));
		// SmartDashboard.putData("Climb!!!", new ClimberClimb());
		
		// SmartDashboard.putData("Voltage Ramp Stinger", new MotorVoltageRamping(Robot.climbStingerLeft, 0.25/12.0));
		// SmartDashboard.putData("Climber voltage ramping", new DualClimberVoltageRamp());
		// SmartDashboard.putData("Climber voltage ramping FF", new DualClimberVoltageRampFF());
		// SmartDashboard.putData("Reset Gyro", new ResetGyro(Robot.drive));
		// SmartDashboard.putData("Climber joystick control", new MotorJoystickControl(super.operatorJoy, Climber.getInstance()));
		// SmartDashboard.putData("Climber voltage ramp", new MotorVoltageRamping(Climber.getInstance(), 0.25/12.));
		// SmartDashboard.putData("Climber voltage ramp with FF up", new MechanismVoltageRampingWithFF(Climber.getInstance(), 0.25/12.));
		// SmartDashboard.putData("Climber voltage ramp with FF down", new MechanismVoltageRampingWithFF(Climber.getInstance(), -0.25/12.));

		SmartDashboard.putData("Reset climber encoder", new ResetSingleMotorEncoder(Climber.getInstance()));
		SmartDashboard.putData("Engage ratchet", new ExtendPiston(Robot.climberRatchet));
		SmartDashboard.putData("Disengage ratchet", new RetractPiston(Robot.climberRatchet));
		// SmartDashboard.putData("Climber Climb", new ClimberClimb());
		// SmartDashboard.putData("Climber Ready", new ClimberReady());
		// SmartDashboard.putData("Climber Wait for succ", new WaitForSuck());
		// SmartDashboard.putData("Climber Lift", new ClimbLift());
		SmartDashboard.putData("Climber 0V", new SetMotorPower(Climber.getInstance(), 0));
		for (double i = 1; i < 3; i++) {
			SmartDashboard.putData("Climber " + String.valueOf(i) + "V", new SetMotorPower(Climber.getInstance(), i/12.0));
			SmartDashboard.putData("Climber -" + String.valueOf(i) + "V", new SetMotorPower(Climber.getInstance(), -i/12.0));
		}

		// SmartDashboard.putData("Climber succ 1V", new SetMotorPower(Robot.vacuum, 1./12.));
		// SmartDashboard.putData("Climber succ -1V", new SetMotorPower(Robot.vacuum, -1./12.));
		SmartDashboard.putData("Climber succ 12V", new SetMotorPower(Robot.vacuum, 1.));
		SmartDashboard.putData("Climber no succ", new SetMotorPower(Robot.vacuum, 0));
		// SmartDashboard.putData("Climber succ -3V", new SetMotorPower(Robot.vacuum, -3./12.));
		// SmartDashboard.putData("Climber succ 6V", new SetMotorPower(Robot.vacuum, 6./12.));
		// SmartDashboard.putData("Climber succ -6V", new SetMotorPower(Robot.vacuum, -6./12.));

		// SmartDashboard.putData("Level 1 Hatch Score", new SimultaneousMovement(SuperstructureConstants.kLowElHeight, SuperstructureConstants.kHatchModeArmAngle));
		SmartDashboard.putData("Reset Drive Encoder", new ResetDriveEncoders(Robot.drive));
		SmartDashboard.putData("Reset Gyro", new ResetGyro(Robot.drive));
		// SmartDashboard.putData("10 Volt", new OpenLoopDrive(Robot.drive, 0.83333));
		// SmartDashboard.putData("Straight Line Path", new DriveFalconTrajectory(Robot.drive, AutoConstants.straightLine, 3, true, 0.3, 0));
		// SmartDashboard.putData("Drive 3 V", new OpenLoopDrive(Robot.drive, 0.25));
		// SmartDashboard.putData("Drive Motion Magic", new DriveDistanceMotionMagic(Robot.drive, 100000, 10000, 10000));
		// SmartDashboard.putData("Set Velocity", new VelocityTest(Robot.drive, 5000, 5));
		// SmartDashboard.putData("Right Rocket", new DriveFalconTrajectory(Robot.drive, AutoConstants.RightRocketPath1, 3, true, 0.0984, 0.00224));
		// SmartDashboard.putData("Right Rocket 2", new DriveFalconTrajectory(Robot.drive, AutoConstants.RightRocketPath2, 3, false, 0.0984, 0.00011));

		// SmartDashboard.putData("Turn 180 deg", new TurnAngle(Robot.drive, 180, 1, 5, 0.0025, 0.00042));
		// SmartDashboard.putData("Turn 0 deg", new TurnAngle(Robot.drive, 0, 1, 5, 0.009, 0.0004));

		// SmartDashboard.putData("Voltage ramp elevator", new MotorVoltageRamping(Elevator.getInstance(), 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp elevator with FF up", new MechanismVoltageRampingWithFF(Elevator.getInstance(), 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp elevator with FF down", new MechanismVoltageRampingWithFF(Elevator.getInstance(), -0.25 / 12.0));

		SmartDashboard.putData("Reset elevator encoder", new ResetSingleMotorEncoder(Elevator.getInstance()));
		SmartDashboard.putData("Reset arm encoder", new ResetSingleMotorEncoder(Arm.getInstance()));
		// SmartDashboard.putData("Rocket Right", new RightRocketNear());
		// SmartDashboard.putData("Elevator MM 42 in", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 42));
		// SmartDashboard.putData("Elevator MM 60 in", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 60));
		// SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 75));
		// SmartDashboard.putData("Elevator MM 16 in", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 16));
		// SmartDashboard.putData("Elevator MM 42 in", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 52));
		//SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 75));

		// SmartDashboard.putData("A waste of my time hatch 1", new SimultaneousMovement(18, 14.5));
		// SmartDashboard.putData("A waste of my time hatch 2", new SimultaneousMovement(45.5, 14.5));
		// SmartDashboard.putData("A waste of my time hatch 3", new SimultaneousMovement(71.5, 14.5));
		// SmartDashboard.putData("A waste of my time cargo 1", new SimultaneousMovement(18, 59));
		// SmartDashboard.putData("A waste of my time cargo 2", new SimultaneousMovement(45.5, 59));
		// SmartDashboard.putData("A waste of my time cargo 3", new SimultaneousMovement(71.5, 59));
		// SmartDashboard.putData("Test resting state", new SimultaneousMovement(16, 0));
		// SmartDashboard.putData("Huggable intake", new SimultaneousMovement(14, -30));
		
		// SmartDashboard.putData("Voltage ramp arm", new MotorVoltageRamping(Arm.getInstance(), 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp arm up with FF", new MechanismVoltageRampingWithFF(Arm.getInstance(), 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp arm down with FF", new MechanismVoltageRampingWithFF(Arm.getInstance(), -0.25 / 12.0));
		// // // SmartDashboard.putData("Set arm voltage 0", new SetMotorPower(Arm.getInstance(), 0));
		// SmartDashboard.putData("Set arm angle 0 deg", new SetArmAngleMotionMagic(Arm.getInstance(), 0));
		// SmartDashboard.putData("Set arm angle -30 deg", new SetArmAngleMotionMagic(Arm.getInstance(), -30));
		// SmartDashboard.putData("Set arm angle 67 deg", new SetArmAngleMotionMagic(Arm.getInstance(), 67));
		// SmartDashboard.putData("Set arm angle 32 deg", new SetArmAngleMotionMagic(Arm.getInstance(), 32));
		// SmartDashboard.putData("Set arm angle 45 deg", new SetArmAngleMotionMagic(Arm.getInstance(), 45));
		// SmartDashboard.putData("Set arm angle 22 deg", new SetArmAngleMotionMagic(Arm.getInstance(), 22));
		// SmartDashboard.putData("Reset arm encoder", new ResetSingleMotorEncoder(Arm.getInstance()));
		// SmartDashboard.putData("Zero arm with hall effect", new ZeroMechanismWithHallEffect(Arm.getInstance(), 
		// 						HallEffect, 2./12.));

		// lineFollow = new JoystickButton(super.driv, 11);
		// lineFollow.whileHeld(new LineFollow(0.254));

		// SmartDashboard.putData("Set both intake sides 3V", new SetMotorPower(Robot.intake, .25));
		// SmartDashboard.putData("Set both intake sides -3V", new SetMotorPower(Robot.intake, -.25));
		// SmartDashboard.putData("Stop rollers", new SetMotorPower(Robot.intake, 0));

		// SmartDashboard.putData("Piston extend ?", new ExtendPiston(Robot.claw));
		// SmartDashboard.putData("Piston retract? ?", new RetractPiston(Robot.claw));

		// SmartDashboard.putData("Elevator set position 0", new SetElevatorHeightMotionMagic(Elevator.getInstance(), 11.75));

		// SmartDashboard.putData("Elevator zero with tach", new ZeroMechanismWithHallEffect(Elevator.getInstance(), Elevator.getInstance()Tach, -1));

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

	public void reportToSmartDashboard() {
		SmartDashboard.putNumber("Left Drive Y", super.getDriveJoyLeftY());
		SmartDashboard.putNumber("Left Drive X", super.getDriveJoyLeftX());
		SmartDashboard.putNumber("Right Drive Y", super.getDriveJoyRightY());
		SmartDashboard.putNumber("Right Drive X", super.getDriveJoyRightX());
	}

	public boolean isButtonPressed(int button){
		return(super.operatorJoy.getRawButton(button));
	}
}
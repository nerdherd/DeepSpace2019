package com.team687.subsystems;

import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.nerdherd.lib.motor.commands.mechanisms.SetArmAngleMotionMagic;
import com.nerdherd.lib.motor.commands.mechanisms.SetElevatorHeightMotionMagic;
import com.nerdherd.lib.oi.DefaultOI;
import com.team687.Robot;
import com.team687.commands.superstructure.OptimizedSimultaneousMovement;
import com.team687.commands.superstructure.SimultaneousMovement;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI extends DefaultOI {

	
	// public JoystickButton deployChevalRamps_, deployKickerWheels_, retractChevalRamps_, retractKickerWheels_;
	
	public OI() {
		// super();
		// SmartDashboard.putData("Voltage Ramp", new DriveCharacterizationTest(Robot.drive, 0.25));
		// SmartDashboard.putData("Reset Encoder", new ResetDriveEncoders(Robot.drive));
		// SmartDashboard.putData("Reset Gyro", new ResetGyro(Robot.drive));
		// SmartDashboard.putData("Drive 1.2V", new OpenLoopDrive(Robot.drive,0.1));
		// SmartDashboard.putData("Drive Motion Magic", new DriveDistanceMotionMagic(Robot.drive, 25000, 1000, 1000));
		// SmartDashboard.putData("Set Velocity", new VelocityTest(Robot.drive, 5000, 5));
		// SmartDashboard.putData("Left Rocket", new DriveFalconTrajectory(Robot.drive, AutoConstants.LeftRocketPath1, 3, true, 0.075, 0));
		// SmartDashboard.putData("Left Rocket 2", new DriveFalconTrajectory(Robot.drive, AutoConstants.LeftRocketPath2, 3, false, 0.075, 0.0005));

		// SmartDashboard.putData("TUrn 90 deg", new TurnAngle(Robot.drive, 180, 1, 5, 0.009, 0.0004));

		// SmartDashboard.putData("Voltage ramp elevator", new MotorVoltageRamping(Robot.elevator, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp elevator with FF up", new MechanismVoltageRampingWithFF(Robot.elevator, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp elevator with FF down", new MechanismVoltageRampingWithFF(Robot.elevator, -0.25 / 12.0));

		SmartDashboard.putData("Reset elevator encoder", new ResetSingleMotorEncoder(Robot.elevator));

		// // SmartDashboard.putData("Elevator MM up pos", new SetMotorMotionMagic(Robot.elevator, 8000));
		// // SmartDashboard.putData("Elevator MM up up pos", new SetMotorMotionMagic(Robot.elevator, 10000));
		// // SmartDashboard.putData("Elevator MM up up up pos", new SetMotorMotionMagic(Robot.elevator, 15380));
		// // SmartDashboard.putData("Elevator MM up up up up pos", new SetMotorMotionMagic(Robot.elevator, 16780));
		// // SmartDashboard.putData("Elevator MM up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 18000));
		// // SmartDashboard.putData("Elevator MM up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 20560));
		// // SmartDashboard.putData("Elevator MM up up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 27000));
		// // SmartDashboard.putData("Elevator MM up up up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 28000));
		// SmartDashboard.putData("Elevator MM down pos", new SetMotorMotionMagic(Robot.elevator, 1000));
		SmartDashboard.putData("Elevator MM 52 in", new SetElevatorHeightMotionMagic(Robot.elevator, 42));
		SmartDashboard.putData("Elevator MM 60 in", new SetElevatorHeightMotionMagic(Robot.elevator, 60));
		SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Robot.elevator, 75));
		SmartDashboard.putData("Elevator MM 16 in", new SetElevatorHeightMotionMagic(Robot.elevator, 16));
		// SmartDashboard.putData("Elevator 3V", new SetMotorPower(Robot.elevator, 0.25));
		// SmartDashboard.putData("Elevator -3V", new SetMotorPower(Robot.elevator, -0.25));(Robot.elevator, 52));
		// SmartDashboard.putData("Elevator MM 42 in", new SetElevatorHeightMotionMagic(
		//SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Robot.elevator, 75));

		SmartDashboard.putData("A waste of my time hatch 1", new SimultaneousMovement(18, 14.5));
		SmartDashboard.putData("A waste of my time hatch 2", new SimultaneousMovement(45.5, 14.5));
		SmartDashboard.putData("A waste of my time hatch 3", new SimultaneousMovement(71.5, 14.5));
		SmartDashboard.putData("A waste of my time cargo 1", new SimultaneousMovement(18, 59));
		SmartDashboard.putData("A waste of my time cargo 2", new SimultaneousMovement(45.5, 59));
		SmartDashboard.putData("A waste of my time cargo 3", new SimultaneousMovement(71.5, 59));
		SmartDashboard.putData("Test resting state", new SimultaneousMovement(16, 0));
		SmartDashboard.putData("Huggable intake", new SimultaneousMovement(14, -30));
		
		// SmartDashboard.putData("Voltage ramp arm", new MotorVoltageRamping(Robot.arm, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp arm up with FF", new MechanismVoltageRampingWithFF(Robot.arm, 0.25 / 12.0));
		// SmartDashboard.putData("Voltage ramp arm down with FF", new MechanismVoltageRampingWithFF(Robot.arm, -0.25 / 12.0));
		SmartDashboard.putData("Set arm voltage 0", new SetMotorPower(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle 0 deg", new SetArmAngleMotionMagic(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle -30 deg", new SetArmAngleMotionMagic(Robot.arm, -30));
		SmartDashboard.putData("Set arm angle 67 deg", new SetArmAngleMotionMagic(Robot.arm, 67));
		SmartDashboard.putData("Set arm angle 32 deg", new SetArmAngleMotionMagic(Robot.arm, 32));
		SmartDashboard.putData("Set arm angle 45 deg", new SetArmAngleMotionMagic(Robot.arm, 45));
		SmartDashboard.putData("Set arm angle 22 deg", new SetArmAngleMotionMagic(Robot.arm, 22));
		SmartDashboard.putData("Reset arm encoder", new ResetSingleMotorEncoder(Robot.arm));

		// SmartDashboard.putData("Set both intake sides 3V", new SetMotorPower(Robot.intake, .25));
		// SmartDashboard.putData("Set both intake sides -3V", new SetMotorPower(Robot.intake, -.25));
		// SmartDashboard.putData("Stop rollers", new SetMotorPower(Robot.intake, 0));

		// SmartDashboard.putData("Piston extend ?", new ExtendPiston(Robot.claw));
		// SmartDashboard.putData("Piston retract? ?", new RetractPiston(Robot.claw));

		// SmartDashboard.putData("Elevator set position 0", new SetElevatorHeightMotionMagic(Robot.elevator, 11.75));

		// SmartDashboard.putData("Elevator zero with tach", new ZeroMechanismWithHallEffect(Robot.elevator, Robot.elevatorTach, -1));

		SmartDashboard.putData("Set Optimized height 60", new OptimizedSimultaneousMovement(60));
		SmartDashboard.putData("Set Optimized height 80", new OptimizedSimultaneousMovement(80));
		SmartDashboard.putData("Set Optimized height 7", new OptimizedSimultaneousMovement(7));

		SmartDashboard.putData("Optimized hatch 1", new OptimizedSimultaneousMovement(21.5));
		SmartDashboard.putData("Optimized hatch 2", new OptimizedSimultaneousMovement(49));
		SmartDashboard.putData("Optimized hatch 3", new OptimizedSimultaneousMovement(75));
		SmartDashboard.putData("Optimized cargo 1", new OptimizedSimultaneousMovement(30));
		SmartDashboard.putData("Optimized cargo 2", new OptimizedSimultaneousMovement(57.5));
		SmartDashboard.putData("Optimized cargo 3", new OptimizedSimultaneousMovement(83.5));

	}
}
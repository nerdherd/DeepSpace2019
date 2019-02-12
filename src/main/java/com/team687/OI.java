package com.team687;

import com.nerdherd.lib.motor.commands.MotorVoltageRamping;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.commands.SetMotorMotionMagic;
import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.nerdherd.lib.motor.commands.mechanisms.MechanismVoltageRampingWithFF;
import com.nerdherd.lib.motor.commands.mechanisms.SetArmAngleMotionMagic;
import com.nerdherd.lib.motor.commands.mechanisms.SetElevatorHeightMotionMagic;
import com.nerdherd.lib.motor.commands.mechanisms.ZeroMechanismWithHallEffect;
import com.nerdherd.lib.pneumatics.commands.ExtendPiston;
import com.nerdherd.lib.pneumatics.commands.RetractPiston;
import com.team687.commands.superstructure.OptimizedSimultaneousMovement;
import com.team687.commands.superstructure.SimultaneousMovement;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI {
	
	Joystick joy;
	JoystickButton ping, streamoff, streamon, liveTargetTrack, stopDrive; 
	public Joystick driveJoyLeft = new Joystick(0);
	public Joystick driveJoyRight = new Joystick(1);
	public Joystick articJoy = new Joystick(2);
	public JoystickButton upperElevator_7, lowElevator_8, lowArm_9, highArm_10, intakeArm_4,
		intakeRollers_1, outtakeRollers_2, stopRollers_3, clawClose_5, clawOpen_6;

	public OI() {
		// joy = new Joystick(2);

		// intakeRollers_1 = new JoystickButton(articJoy, 1);
		// outtakeRollers_2 = new JoystickButton(articJoy, 2);
		// stopRollers_3 = new JoystickButton(articJoy, 3);
		// intakeArm_4 = new JoystickButton(articJoy, 4);
		// clawClose_5 = new JoystickButton(articJoy, 5);
		// clawOpen_6 = new JoystickButton(articJoy, 6);
		// upperElevator_7 = new JoystickButton(articJoy, 7);
		// lowElevator_8 = new JoystickButton(articJoy, 8);
		// lowArm_9 = new JoystickButton(articJoy, 11);
		// highArm_10 = new JoystickButton(articJoy, 10);

		// intakeRollers_1.whenPressed(new SetMotorPower(Robot.intake, .25));
		// outtakeRollers_2.whenPressed(new SetMotorPower(Robot.intake, -.25));
		// stopRollers_3.whenPressed(new SetMotorPower(Robot.intake, 0));
		// intakeArm_4.whenPressed(new SetArmAngleMotionMagic(Robot.arm, -30));
		// clawClose_5.whenPressed(new ExtendPiston(Robot.claw));
		// clawOpen_6.whenPressed(new RetractPiston(Robot.claw));
		// upperElevator_7.whenPressed(new SetElevatorHeightMotionMagic(Robot.elevator, 42));
		// lowElevator_8.whenPressed(new SetElevatorHeightMotionMagic(Robot.elevator, 16));
		// lowArm_9.whenPressed(new SetArmAngleMotionMagic(Robot.arm, 42));
		// highArm_10.whenPressed(new SetArmAngleMotionMagic(Robot.arm, 67));


		SmartDashboard.putData("Voltage ramp elevator", new MotorVoltageRamping(Robot.elevator, 0.25 / 12.0));
		SmartDashboard.putData("Voltage ramp elevator with FF up", new MechanismVoltageRampingWithFF(Robot.elevator, 0.25 / 12.0));
		SmartDashboard.putData("Voltage ramp elevator with FF down", new MechanismVoltageRampingWithFF(Robot.elevator, -0.25 / 12.0));

		SmartDashboard.putData("Reset elevator encoder", new ResetSingleMotorEncoder(Robot.elevator));

		// SmartDashboard.putData("Elevator MM up pos", new SetMotorMotionMagic(Robot.elevator, 8000));
		// SmartDashboard.putData("Elevator MM up up pos", new SetMotorMotionMagic(Robot.elevator, 10000));
		// SmartDashboard.putData("Elevator MM up up up pos", new SetMotorMotionMagic(Robot.elevator, 15380));
		// SmartDashboard.putData("Elevator MM up up up up pos", new SetMotorMotionMagic(Robot.elevator, 16780));
		// SmartDashboard.putData("Elevator MM up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 18000));
		// SmartDashboard.putData("Elevator MM up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 20560));
		// SmartDashboard.putData("Elevator MM up up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 27000));
		// SmartDashboard.putData("Elevator MM up up up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 28000));
		// SmartDashboard.putData("Elevator MM down pos", new SetMotorMotionMagic(Robot.elevator, 1000));
		SmartDashboard.putData("Elevator MM 52 in", new SetElevatorHeightMotionMagic(Robot.elevator, 52));
		SmartDashboard.putData("Elevator MM 42 in", new SetElevatorHeightMotionMagic(Robot.elevator, 42));
		SmartDashboard.putData("Elevator MM 60 in", new SetElevatorHeightMotionMagic(Robot.elevator, 60));
		SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Robot.elevator, 75));
		SmartDashboard.putData("Elevator MM 16 in", new SetElevatorHeightMotionMagic(Robot.elevator, 16));
		SmartDashboard.putData("Elevator 3V", new SetMotorPower(Robot.elevator, 0.25));
		SmartDashboard.putData("Elevator -3V", new SetMotorPower(Robot.elevator, -0.25));
		//SmartDashboard.putData("Elevator MM 75 in", new SetElevatorHeightMotionMagic(Robot.elevator, 75));

		SmartDashboard.putData("A waste of my time hatch 1", new SimultaneousMovement(18, 14.5));
		SmartDashboard.putData("A waste of my time hatch 2", new SimultaneousMovement(45.5, 14.5));
		SmartDashboard.putData("A waste of my time hatch 3", new SimultaneousMovement(71.5, 14.5));
		SmartDashboard.putData("A waste of my time cargo 1", new SimultaneousMovement(18, 59));
		SmartDashboard.putData("A waste of my time cargo 2", new SimultaneousMovement(45.5, 59));
		SmartDashboard.putData("A waste of my time cargo 3", new SimultaneousMovement(71.5, 59));
		SmartDashboard.putData("Test resting state", new SimultaneousMovement(16, 0));
		SmartDashboard.putData("Huggable intake", new SimultaneousMovement(14, -30));
		
		SmartDashboard.putData("Voltage ramp arm", new MotorVoltageRamping(Robot.arm, 0.25 / 12.0));
		SmartDashboard.putData("Voltage ramp arm up with FF", new MechanismVoltageRampingWithFF(Robot.arm, 0.25 / 12.0));
		SmartDashboard.putData("Voltage ramp arm down with FF", new MechanismVoltageRampingWithFF(Robot.arm, -0.25 / 12.0));
		SmartDashboard.putData("Set arm voltage 0", new SetMotorPower(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle 0 deg", new SetArmAngleMotionMagic(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle 4 deg", new SetArmAngleMotionMagic(Robot.arm, 4));
		SmartDashboard.putData("Set arm angle -30 deg", new SetArmAngleMotionMagic(Robot.arm, -30));
		SmartDashboard.putData("Set arm angle -26 deg", new SetArmAngleMotionMagic(Robot.arm, -26));
		SmartDashboard.putData("Set arm angle 67 deg", new SetArmAngleMotionMagic(Robot.arm, 67));
		SmartDashboard.putData("Set arm angle 71 deg", new SetArmAngleMotionMagic(Robot.arm, 71));
		SmartDashboard.putData("Set arm angle 32 deg", new SetArmAngleMotionMagic(Robot.arm, 32));
		SmartDashboard.putData("Set arm angle 36 deg", new SetArmAngleMotionMagic(Robot.arm, 36));
		SmartDashboard.putData("Set arm angle 45 deg", new SetArmAngleMotionMagic(Robot.arm, 45));
		SmartDashboard.putData("Set arm angle 49 deg", new SetArmAngleMotionMagic(Robot.arm, 49));
		SmartDashboard.putData("Set arm angle 22 deg", new SetArmAngleMotionMagic(Robot.arm, 22));
		SmartDashboard.putData("Set arm angle 26 deg", new SetArmAngleMotionMagic(Robot.arm, 26));
		SmartDashboard.putData("Reset arm encoder", new ResetSingleMotorEncoder(Robot.arm));

		SmartDashboard.putData("Set both intake sides 3V", new SetMotorPower(Robot.intake, .25));
		SmartDashboard.putData("Set both intake sides -3V", new SetMotorPower(Robot.intake, -.25));
		SmartDashboard.putData("Stop rollers", new SetMotorPower(Robot.intake, 0));

		SmartDashboard.putData("Piston extend ?", new ExtendPiston(Robot.claw));
		SmartDashboard.putData("Piston retract? ?", new RetractPiston(Robot.claw));

		SmartDashboard.putData("Elevator set position 0", new SetElevatorHeightMotionMagic(Robot.elevator, 11.75));

		SmartDashboard.putData("Set Optimized height 60", new OptimizedSimultaneousMovement(60));

	}

	 /**
     * @return input power from left drive joystick Y (-1.0 to +1.0)
     */
    public double getDriveJoyLeftY() {
		// return -gamepadJoy.getRawAxis(1);
		return -driveJoyLeft.getY();
		}
	
		/**
		 * @return input power from right drive joystick Y (-1.0 to +1.0)
		 */
		public double getDriveJoyRightY() {
		// return -gamepadJoy.getRawAxis(3);
		return -driveJoyRight.getY();
		}
	
		/**
		 * @return input power from left drive joystick X (-1.0 to +1.0)
		 */
		public double getDriveJoyLeftX() {
		// return gamepadJoy.getRawAxis(0);
		return driveJoyLeft.getX();
		}
	
		/**
		 * @return input power from right drive joystick X (-1.0 to +1.0)
		 */
		public double getDriveJoyRightX() {
		// return gamepadJoy.getRawAxis(2);
		return driveJoyRight.getX();
		}
	
		/**
		 * @return input throttle from right drive joystick (0 to +1.0)
		 */
		public double getThrottleR() {
		return (driveJoyRight.getThrottle() + 1) / 2;
		}
	
		/**
		 * @return input throttle from left drive josytick
		 */
		public double getThrottleL() {
		return (driveJoyLeft.getThrottle() + 1) / 2;
		}
}
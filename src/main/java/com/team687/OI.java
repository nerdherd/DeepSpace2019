package com.team687;

import com.nerdherd.lib.motor.commands.MotorVoltageRamping;
import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.nerdherd.lib.motor.commands.SetMotorMotionMagic;
import com.nerdherd.lib.motor.commands.SetMotorPositionPID;
import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.nerdherd.lib.motor.commands.mechanisms.MechanismVoltageRampingWithFF;
import com.nerdherd.lib.motor.commands.mechanisms.SetArmAngleMotionMagic;
import com.nerdherd.lib.pneumatics.commands.ExtendPiston;
import com.nerdherd.lib.pneumatics.commands.RetractPiston;

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

	public OI() {
		joy = new Joystick(2);

	SmartDashboard.putData("Voltage ramp elevator", new MotorVoltageRamping(Robot.elevator, 0.25 / 12.0));
	SmartDashboard.putData("Voltage ramp elevator with FF up", new MechanismVoltageRampingWithFF(Robot.elevator, 0.25 / 12.0));
	SmartDashboard.putData("Voltage ramp elevator with FF down", new MechanismVoltageRampingWithFF(Robot.elevator, -0.25 / 12.0));

    SmartDashboard.putData("Reset elevator encoder", new ResetSingleMotorEncoder(Robot.elevator));
    SmartDashboard.putData("Elevator up pos", new SetMotorPositionPID(Robot.elevator, 8000));
    SmartDashboard.putData("Elevator up up pos", new SetMotorPositionPID(Robot.elevator, 10000));
    SmartDashboard.putData("Elevator up up up pos", new SetMotorPositionPID(Robot.elevator, 15380));
    SmartDashboard.putData("Elevator up up up up pos", new SetMotorPositionPID(Robot.elevator, 16780));
    SmartDashboard.putData("Elevator up up up up up pos", new SetMotorPositionPID(Robot.elevator, 18000));
    SmartDashboard.putData("Elevator down pos", new SetMotorPositionPID(Robot.elevator, 1000));


    SmartDashboard.putData("Elevator MM up pos", new SetMotorMotionMagic(Robot.elevator, 8000));
    SmartDashboard.putData("Elevator MM up up pos", new SetMotorMotionMagic(Robot.elevator, 10000));
    SmartDashboard.putData("Elevator MM up up up pos", new SetMotorMotionMagic(Robot.elevator, 15380));
    SmartDashboard.putData("Elevator MM up up up up pos", new SetMotorMotionMagic(Robot.elevator, 16780));
    SmartDashboard.putData("Elevator MM up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 18000));
    SmartDashboard.putData("Elevator MM up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 20560));
    SmartDashboard.putData("Elevator MM up up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 27000));
    SmartDashboard.putData("Elevator MM up up up up up up up up pos", new SetMotorMotionMagic(Robot.elevator, 28000));
		SmartDashboard.putData("Elevator MM down pos", new SetMotorMotionMagic(Robot.elevator, 1000));  
		
		SmartDashboard.putData("Voltage ramp arm", new MotorVoltageRamping(Robot.arm, 0.25 / 12.0));
		SmartDashboard.putData("Voltage ramp arm up with FF", new MechanismVoltageRampingWithFF(Robot.arm, 0.25 / 12.0));
		SmartDashboard.putData("Voltage ramp arm down with FF", new MechanismVoltageRampingWithFF(Robot.arm, -0.25 / 12.0));
		SmartDashboard.putData("Set arm voltage 0", new SetMotorPower(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle 0 deg", new SetArmAngleMotionMagic(Robot.arm, 0));
		SmartDashboard.putData("Set arm angle -30 deg", new SetArmAngleMotionMagic(Robot.arm, -30));
		SmartDashboard.putData("Set arm angle 67 deg", new SetArmAngleMotionMagic(Robot.arm, 67));
		SmartDashboard.putData("Set arm angle 32 deg", new SetArmAngleMotionMagic(Robot.arm, 32));
		SmartDashboard.putData("Reset arm encoder", new ResetSingleMotorEncoder(Robot.arm));

		SmartDashboard.putData("Set both intake sides 3V", new SetMotorPower(Robot.intake, .25));
		SmartDashboard.putData("Set both intake sides -3V", new SetMotorPower(Robot.intake, -.25));
		SmartDashboard.putData("Stop rollers", new SetMotorPower(Robot.intake, 0));

		SmartDashboard.putData("Piston extend ?", new ExtendPiston(Robot.claw));
		SmartDashboard.putData("Piston retract? ?", new RetractPiston(Robot.claw));

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
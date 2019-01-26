package com.team687.subsystems;

import com.team687.commands.vision.streamoff;
import com.team687.commands.vision.streamon;
import com.team687.commands.vision.LiveTargetTrack;
import com.team687.commands.vision.LockOnLeftTarget;
import com.team687.commands.vision.LockOnRightTarget;
import com.team687.commands.StopDrive;

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
		
		streamoff = new JoystickButton(joy,2);
		streamoff.whenPressed(new streamoff());
		
		streamon = new JoystickButton(joy,3);
		streamon.whenPressed(new streamon());
			
		liveTargetTrack = new JoystickButton(joy, 1);
		liveTargetTrack.whenPressed(new LiveTargetTrack());

		stopDrive = new JoystickButton(joy, 12);
		stopDrive.whenPressed(new StopDrive());

		SmartDashboard.putData("LockOnLeftTarget", new LockOnLeftTarget());
		SmartDashboard.putData("LockOnRightTarget", new LockOnRightTarget());

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
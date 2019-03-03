package com.team687;

import com.team687.commands.drive.auto.ResetDriveEncoders;
import com.team687.commands.led.LedBlue;
import com.team687.commands.led.LedRed;
import com.team687.commands.led.LightGreen;
import com.team687.commands.vision.DriveAtHeading;
import com.team687.commands.vision.LiveTargetTrack;
import com.team687.commands.vision.TurnAndApproach;

// import com.team687.commands.vision.LiveTargetTrack;
// import com.team687.commands.vision.DisableStream;
// import com.team687.commands.vision.EnableStream;
// import com.team687.commands.vision.ping;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI {
	
	JoystickButton ping, disableStream, enableStream, liveTargetTrack, stopDrive, lineFollow, driveAtHeading; 
	public Joystick driveJoyRight = new Joystick(0);
	public Joystick driveJoyLeft = new Joystick(1);

	public OI() {
		
		liveTargetTrack = new JoystickButton(driveJoyRight, 1);
		liveTargetTrack.whileHeld(new LiveTargetTrack(0.0139));

		// lineFollow = new JoystickButton(driveJoyRight, 11);
		// lineFollow.whileHeld(new LineFollow(0.254));

		
		SmartDashboard.putData("TurnAndApproach", new TurnAndApproach());
		SmartDashboard.putData("DriveAtHeading", new DriveAtHeading(.0139, .0000354));
		SmartDashboard.putData("LiveTargetTrack", new LiveTargetTrack(0.0139));
		SmartDashboard.putData("RESET ENCODERS", new ResetDriveEncoders());
		SmartDashboard.putData("Blue", new LedBlue());
		SmartDashboard.putData("Green", new LightGreen());
	
		//disableStream = new JoystickButton(driveJoyLeft,2);
		//disableStream.whenPressed(new DisableStream());
		
		//enableStream = new JoystickButton(driveJoyLeft,3);
		//enableStream.whenPressed(new EnableStream());
			

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
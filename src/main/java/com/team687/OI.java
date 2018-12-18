package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.vision.streamoff;
import org.usfirst.frc.team687.robot.commands.vision.streamon;
import org.usfirst.frc.team687.robot.commands.vision.LiveTargetTrack;
import org.usfirst.frc.team687.robot.commands.vision.LockOnLeftTarget;
import org.usfirst.frc.team687.robot.commands.vision.LockOnRightTarget;
import org.usfirst.frc.team687.robot.commands.StopDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI {
	
	Joystick joy;
	JoystickButton ping, streamoff, streamon, liveTargetTrack, stopDrive; 
	
	public OI() {
		joy = new Joystick(0);
		
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
}
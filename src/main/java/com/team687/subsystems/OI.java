package com.team687.subsystems;

import com.nerdherd.lib.drivetrain.characterization.DriveCharacterizationTest;
import com.nerdherd.lib.drivetrain.shifting.SwitchShift;
import com.nerdherd.lib.oi.DefaultOI;
import com.team687.Robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class OI extends DefaultOI {
	
	
	public JoystickButton deployChevalRamps_, deployKickerWheels_, retractChevalRamps_, retractKickerWheels_;
	public OI() {
		super();
		SmartDashboard.putData("Switch Shift", new SwitchShift(Robot.drive));
		SmartDashboard.putData("Voltage Ramp", new DriveCharacterizationTest(Robot.drive, 0.25));
		}

}
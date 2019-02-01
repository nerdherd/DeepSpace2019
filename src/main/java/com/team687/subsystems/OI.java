package com.team687.subsystems;

import com.nerdherd.lib.oi.DefaultOI;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 */
public class OI extends DefaultOI {
	
	
	public JoystickButton deployChevalRamps_, deployKickerWheels_, retractChevalRamps_, retractKickerWheels_;
	public OI() {
		super();
		deployChevalRamps_ = new JoystickButton(super.driveJoyLeft, 0);
	}

}
package com.team687.subsystems;

import com.nerdherd.lib.drivetrain.auto.ResetDriveEncoders;
import com.nerdherd.lib.drivetrain.auto.ResetGyro;
import com.nerdherd.lib.drivetrain.characterization.DriveCharacterizationTest;
import com.nerdherd.lib.drivetrain.characterization.OpenLoopDrive;
import com.nerdherd.lib.drivetrain.characterization.VelocityTest;
import com.nerdherd.lib.drivetrain.shifting.ShiftHigh;
import com.nerdherd.lib.drivetrain.shifting.ShiftLow;
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
		SmartDashboard.putData("Voltage Ramp", new DriveCharacterizationTest(Robot.drive, 0.25));
		SmartDashboard.putData("Shift High Gear", new ShiftHigh(Robot.drive));
		SmartDashboard.putData("Shift Low Gear", new ShiftLow(Robot.drive));
		SmartDashboard.putData("Reset Encoder", new ResetDriveEncoders(Robot.drive));
		SmartDashboard.putData("Reset Gyro", new ResetGyro(Robot.drive));
		SmartDashboard.putData("Drive 3V", new OpenLoopDrive(Robot.drive,0.25));
		// SmartDashboard.putData("Drive Motion Magic", new DriveDistanceMotionMagic(Robot.drive, 50000, 5000, 5000));
		SmartDashboard.putData("Set Velocity", new VelocityTest(Robot.drive, 5000, 5));
		}
}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.AutoChooser;
import com.team687.constants.DriveConstants;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Jevois;
import com.team687.subsystems.LED;
import com.team687.subsystems.Sensor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 */
public class Robot extends TimedRobot {

	public static final String kDate = "2019_03_02_";

	public static Drive drive;
	public static Jevois jevois;
	public static Subsystem livestream;
	public static DriverStation ds;
	public static AutoChooser autoChooser;
	public static Sensor sensor;
	public static LED led;

	public static OI oi;

	@Override
	public void robotInit() {
		autoChooser = new AutoChooser();
		led = new LED();
		jevois = new Jevois(115200, SerialPort.Port.kUSB1);
		jevois.startCameraStream();
		sensor = new Sensor();
		drive = new Drive();
		ds = DriverStation.getInstance();	
	  oi = new OI();
	}

	@Override
	public void disabledInit() {
		jevois.stopLog();
		jevois.enableStream();	
	}

	@Override
	public void disabledPeriodic() {
		jevois.reportToSmartDashboard();
		drive.reportToSmartDashboard();
		// sensor.reportToSmartDashboard();

		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		jevois.startLog();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		jevois.reportToSmartDashboard();
		// sensor.reportToSmartDashboard();
		drive.reportToSmartDashboard();
		jevois.logToCSV();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}

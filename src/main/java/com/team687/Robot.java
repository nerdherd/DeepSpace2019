/*----------------------------------------------------------------------------*/
  /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.team687.subsystems.Drive;
import com.team687.subsystems.Jevois;
import com.team687.utilities.AutoChooser;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class Robot extends TimedRobot {
	
	public static final String kDate = "2018_09_29_";

	public static Drive drive;
	public static Jevois jevois;
	public static Subsystem livestream;
	public static DriverStation ds;
	public static AutoChooser autoChooser;
	public static OI oi;

	@Override
	public void robotInit() {
		autoChooser = new AutoChooser();
	    jevois = new Jevois(115200, SerialPort.Port.kUSB);
	    drive = new Drive();
	    oi = new OI();
	    ds = DriverStation.getInstance();
	}

	@Override
	public void disabledInit() {
		jevois.stopLog();
	}

	@Override
	public void disabledPeriodic() {
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
		Scheduler.getInstance().run();
		
		jevois.reportToSmartDashboard();
		jevois.logToCSV();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}

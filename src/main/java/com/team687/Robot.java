/*----------------------------------------------------------------------------*/
  /* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.NerdyBadlog;
import com.nerdherd.lib.motor.single.SingleMotorElevator;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Jevois;
import com.team687.subsystems.Streamer;
import com.team687.utilities.AutoChooser;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Notifier;
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
  public static SingleMotorElevator elevator;
  public static OI oi;
  public static Notifier logger;
	@Override
	public void robotInit() {
		autoChooser = new AutoChooser();
	    // jevois = new Jevois(115200, SerialPort.Port.kUSB);
	    // livestream = new Streamer();
	    drive = new Drive();
			ds = DriverStation.getInstance();
			
			elevator = new SingleMotorElevator(0, "Elevator", true, true);
			elevator.configGravityFF(1.13);
			elevator.configMotionMagic(3000, 3000);
			elevator.configPIDF(0.15, 0, 0, 0.256);
		
			oi = new OI();

			NerdyBadlog.init("/media/sda1/logs/elevator_testing10.csv", elevator);
			logger = new Notifier(() -> {
				NerdyBadlog.log();
			});
			logger.startPeriodic(0.01);
	}

	@Override
	public void robotPeriodic() {
		elevator.reportToSmartDashboard();
	}

	@Override
	public void disabledInit() {
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
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}

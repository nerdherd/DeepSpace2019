/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.AutoChooser;
import com.team687.subsystems.Drive;
import com.team687.subsystems.Jevois;
import com.team687.subsystems.LED;
import com.team687.subsystems.Sensor;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource;
import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

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

	// Double camera testing

	public static UsbCamera camera1;
	public static UsbCamera camera2;
	public static VideoSink server;
	public static Joystick joy1;
	public static boolean prevTrigger = false;

	public static OI oi;

	@Override
	public void robotInit() {
		autoChooser = new AutoChooser();
		// led = new LED();
		jevois = new Jevois(115200, SerialPort.Port.kUSB1);
		jevois.startCameraStream();
		// try {
		// 	camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		// 	camera1.setVideoMode(PixelFormat.kMJPEG, 320, 240, 30);
		// } catch (Exception e) {
		// }
		
		sensor = new Sensor();
		// drive = new Drive();
		ds = DriverStation.getInstance();	
		oi = new OI();
		// joy1 = new Joystick(0);

		CameraServer.getInstance().startAutomaticCapture(0);
	
		// camera1 = CameraServer.getInstance().startAutomaticCapture(0);
		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		server = CameraServer.getInstance().addServer("Switched camera");
		camera2.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);	


	}

	@Override
	public void disabledInit() {
		// jevois.stopLog();
		// jevois.enableStream();	
	}

	@Override
	public void disabledPeriodic() {
		jevois.reportToSmartDashboard();
		// drive.reportToSmartDashboard();
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
		// jevois.startLog();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		// jevois.reportToSmartDashboard();
		// sensor.reportToSmartDashboard();
		// drive.reportToSmartDashboard();
		// jevois.logToCSV();
		Scheduler.getInstance().run();
		// if (joy1.getTrigger() && !prevTrigger) {
		// 	System.out.println("Setting camera 2");
		// 	server.setSource(camera2);
		//   } else if (!joy1.getTrigger() && prevTrigger) {
		// 	System.out.println("Setting camera 1");
		// 	server.setSource(camera1);
		//   }
		//   prevTrigger = joy1.getTrigger();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
    public void testPeriodic() {
    }
}
 
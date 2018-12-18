package org.usfirst.frc.team687.robot.subsystems;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Streamer extends Subsystem implements Runnable{

	private int screenWidth = 1240;
	private int screenHeight = 960;
	
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	private Thread livestream;


    public Streamer() {
	System.out.println("streamer init");
        mat = new Mat();
        
        camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(screenWidth, screenHeight);
        
        cvSink = CameraServer.getInstance().getVideo();
        outputStream = CameraServer.getInstance().putVideo("Rectangle", screenWidth, screenHeight);    	
        livestream = new Thread(this);
	livestream.start();
    }


    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void run() {
//	System.out.println("WE ARE STREAMING BOYS");
	// TODO Auto-generated method stub
	if (cvSink.grabFrame(mat) == 0) {
		outputStream.notifyError(cvSink.getError());
	}
	
	outputStream.putFrame(mat);
    }
}


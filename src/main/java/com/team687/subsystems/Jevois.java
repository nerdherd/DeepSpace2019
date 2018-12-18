package com.team687.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.team687.Constants;
import com.team687.Robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Jevois extends Subsystem implements Runnable {
	private SerialPort cam;
	private Thread stream;
	public double focalLength;

	private boolean send;
	private boolean writeException = false;

	String[] parts;
	private String sendValue;

	private String m_filePath1 = "/media/sda1/logs/";
	private String m_filePath2 = "/home/lvuser/logs/";
	private File m_file;
	public FileWriter m_writer;
	private double m_logStartTime = 0;

	// Jevois Serial Output Data
	private double contour_id, target_area_pixel, target_centroid_X_pixel, target_centroid_Y_pixel, target_height_pixel,
			target_length_pixel;

	public Jevois(int baud, SerialPort.Port port) {
		focalLength = (Constants.kVerticalPixels / 2) / Math.tan(Constants.kVerticalFOV / 2);
		send = false;
		sendValue = "None";
		cam = new SerialPort(baud, port);
		stream = new Thread(this);
		stream.start();
	}

	public void run() {
		while (stream.isAlive()) {
			Timer.delay(0.005);
			if (send) {
				cam.writeString(sendValue);
				send = false;
			}
			if (cam.getBytesReceived() > 0) {
				String read = cam.readString();
				if (read.charAt(0) == '/') {
					parts = dataParse(read);
					contour_id = Integer.parseInt(getData(1));
					target_area_pixel = Double.parseDouble(getData(2));
					target_centroid_X_pixel = Double.parseDouble(getData(3));
					target_centroid_Y_pixel = Double.parseDouble(getData(4));
					target_length_pixel = Double.parseDouble(getData(5));
					target_height_pixel = Double.parseDouble(getData(6));
				} else {
					System.out.println(read);
					System.out.println("NO CUBE DETECTED");
				}
			}
		}
	}

	// Robot functionalities
	public double getAngularTargetError() {
		return pixelToDegree(getTargetX());
	}

	private double pixelToDegree(double pixel) {
		double radian = Math.signum(pixel) * Math.atan(Math.abs(pixel / focalLength));
		double degree = 180 * radian / Math.PI;
		return degree;
	}

	public double getDistanceTargetError() {
		double alpha = Math.atan(Robot.jevois.getTargetY() / focalLength);
		double beta = Constants.kCameraMountAngle + alpha;
		double centroidVar = (-alpha / 45) + 1;
		double centroidHeight = Constants.kCubeHeight * centroidVar;
		return centroidHeight * Math.tan(beta); // distance target error
	}

	public double getContourID() {
		return contour_id;
	}

	public double getTargetX() {
		return target_centroid_X_pixel;
	}

	public double getTargetY() {
		return target_centroid_Y_pixel;
	}

	public double getTargetLength() {
		return target_length_pixel;
	}

	public double getTargetHeight() {
		return target_height_pixel;
	}

	public double getTargetArea() {
		return target_area_pixel;
	}

	public void end() {
		stream.interrupt();
	}

	private void sendCommand(String value) {
		sendValue = value + "\n";
		send = true;
		Timer.delay(0.1);
	}

	private String[] dataParse(String input) {
		return input.split("/");
	}

	private String getData(int data) {
		return parts[data];
	}

	public void ping() {
		sendCommand("ping");
	}

	public void streamon() {
		sendCommand("streamon");
	}

	public void streamoff() {
		sendCommand("streamoff");
	}

	public void reportToSmartDashboard() {
		SmartDashboard.putNumber("Target Distance Error", getDistanceTargetError());
		SmartDashboard.putNumber("Target Angle Error", getAngularTargetError());
		
		SmartDashboard.putNumber("Contour ID", getContourID()); // 1st in data output
		SmartDashboard.putNumber("Area", getTargetArea()); // 2nd
		SmartDashboard.putNumber("Y coord", getTargetY()); // 3rd
		SmartDashboard.putNumber("X coord", getTargetX()); // 4th
		SmartDashboard.putNumber("Length", getTargetLength()); // 5th
		SmartDashboard.putNumber("Height", getTargetHeight()); // 6th

	
	}

	public void startLog() {
		// Check to see if flash drive is mounted.
		File logFolder1 = new File(m_filePath1);
		File logFolder2 = new File(m_filePath2);
		Path filePrefix = Paths.get("");
		if (logFolder1.exists() && logFolder1.isDirectory()) {
			filePrefix = Paths.get(logFolder1.toString(),
					Robot.kDate + Robot.ds.getMatchType().toString() + Robot.ds.getMatchNumber() + "Drive");
		} else if (logFolder2.exists() && logFolder2.isDirectory()) {
			filePrefix = Paths.get(logFolder2.toString(),
					Robot.kDate + Robot.ds.getMatchType().toString() + Robot.ds.getMatchNumber() + "Drive");
		} else {
			writeException = true;
		}

		if (!writeException) {
			int counter = 0;
			while (counter <= 99) {
				m_file = new File(filePrefix.toString() + String.format("%02d", counter) + ".csv");
				if (m_file.exists()) {
					counter++;
				} else {
					break;
				}
				if (counter == 99) {
					System.out.println("file creation counter at 99!");
				}
			}
			try {
				m_writer = new FileWriter(m_file);
				m_writer.append("Time,Contours,Area,TargetX,TargetY,Length,Height\n");
				m_logStartTime = Timer.getFPGATimestamp();
			} catch (IOException e) {
				e.printStackTrace();
				writeException = true;
			}
		}
	}

	public void stopLog() {
		try {
			if (null != m_writer)
				m_writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			writeException = true;
		}
	}

	public void logToCSV() {
		if (!writeException) {
			try {
				double timestamp = Timer.getFPGATimestamp() - m_logStartTime;
				m_writer.append(String.valueOf(timestamp) + "," + getContourID() + "," + getTargetArea() + "," + getTargetX() + "," + getTargetY() + "," + getTargetLength() + "," + getTargetHeight());
//			m_writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
				writeException = true;
			}
		}
	}

	@Override
	protected void initDefaultCommand() {

	}

}
package com.team687.subsystems;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.team687.constants.Constants;
import com.team687.Robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Jevois extends Subsystem implements Runnable {
	private SerialPort m_cam;
	private Thread m_stream;
	private double m_focalLength;
	private boolean m_send;
	private double m_threshold = 25.4;
	public double m_offset; 
	public double m_distinguish = 0.0;
	


	private boolean writeException = false;

	String[] parts;
	private String sendValue;

	private String m_filePath1 = "/media/sda1/logs/";
	private String m_filePath2 = "/home/lvuser/logs/";
	private File m_file;
	public FileWriter m_writer;
	private double m_logStartTime = 0;

	// Jevois Serial Output Data
	private double m_contourNum, m_area, m_centerX, m_centerY, m_degreesToTurn, m_distance;

	public Jevois(int baud, SerialPort.Port port) {
		m_focalLength = (Constants.kVerticalPixels / 2) / Math.tan(Constants.kVerticalFOV / 2);
		m_send = false;
		sendValue = "None";
		m_cam = new SerialPort(baud, port);
		m_stream = new Thread(this);
		m_stream.start();
	}

	public void run() {
		while (m_stream.isAlive()) {
			Timer.delay(0.001);

			if (m_send) {
				m_cam.writeString(sendValue);
				m_send = false;
			}
			if (m_cam.getBytesReceived() > 0) {
				String read = m_cam.readString();
				if (read.charAt(0) == '/') {
					parts = dataParse(read);
					m_contourNum = Integer.parseInt(getData(1));
					m_area = Double.parseDouble(getData(2));
					m_centerX = Double.parseDouble(getData(3));
					m_centerY = Double.parseDouble(getData(4));
					m_degreesToTurn = Double.parseDouble(getData(5));
					m_distance = Double.parseDouble(getData(6));

				} else {
					System.out.println(read);
					// System.out.println("No target detected. Check that videomappings.cfg is set to NONE with an *");
				}
			}
		}
	}

	// Robot functionalities
	public double getAngularTargetError() {
		return pixelToDegree(getTargetX());
	}

	private double pixelToDegree(double pixel) {
		double radian = Math.signum(pixel) * Math.atan(Math.abs(pixel / m_focalLength));
		double degree = 180 * radian / Math.PI;
		return degree;
	}

	

	

	/*
	public double cameraTest(){
		return m_distinguish;
	}

	public void changeDistinguish(){
		while(m_cam.getBytesReceived() > 0){
			m_distinguish += 1;
		}
	}
	*/

	public double getOffset() {
        double angularError = getAngularTargetError();
        if(-m_threshold < angularError && angularError < m_threshold){
            m_offset = 0.0;
        }
        else if(m_threshold > 0){
            m_offset = angularError - m_threshold; 
        }
        else if(m_threshold < 0){
            m_offset = angularError + m_threshold;
        }
        return m_offset; 
    }


	public double getContourNum() {
		return m_contourNum;
	}

	public double getTargetX() {
		return m_centerX;
	}

	public double getTargetY() {
		return m_centerY;
	}

	public double getTargetArea() {
		return m_area;
	}

	public double getAngleToTurn(){
		return m_degreesToTurn;
	}

	public double getDistance(){
		return m_distance;
	}

	public void end() {
		m_stream.interrupt();
	}

	private void sendCommand(String value) {
		sendValue = value + "\n";
		m_send = true;
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

	public void enableStream() {
		sendCommand("streamon");
	}

	public void disableStream() {
		sendCommand("streamoff");
	}

	public void reportToSmartDashboard() {
		SmartDashboard.putString("Alive?", "ye");
		SmartDashboard.putNumber("Total contours", getContourNum()); // 1st in data output
		SmartDashboard.putNumber("Area", getTargetArea()); // 2nd
		SmartDashboard.putNumber("Y coord", getTargetY()); // 3rd
		SmartDashboard.putNumber("X coord", getTargetX()); // 3rd
		SmartDashboard.putNumber("Angle to Turn", getAngleToTurn());
		SmartDashboard.putNumber("Distance", getDistance());
		// SmartDashboard.putNumber("Angular Target Error", getAngularTargetError()); // 4th 
		// SmartDashboard.putNumber("Offset", getOffset());

	}

	public void startLog() {
		// Check to see if flash drive is mounted.
		File logFolder1 = new File(m_filePath1);
		File logFolder2 = new File(m_filePath2);
		Path filePrefix = Paths.get("");
		if (logFolder1.exists() && logFolder1.isDirectory()) {
			filePrefix = Paths.get(logFolder1.toString(),
					Robot.kDate + Robot.ds.getMatchType().toString() + Robot.ds.getMatchNumber() + "Jevois");
		} else if (logFolder2.exists() && logFolder2.isDirectory()) {
			filePrefix = Paths.get(logFolder2.toString(),
					Robot.kDate + Robot.ds.getMatchType().toString() + Robot.ds.getMatchNumber() + "Jevois");
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
				// m_writer.append(String.valueOf(timestamp) + "," + getContourNum() + "," + getTargetArea() + ","
				// 		+ getTargetX() + "," + getTargetY());
				m_writer.append("yeetus");
				// m_writer.flush();
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
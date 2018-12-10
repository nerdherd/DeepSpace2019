package com.team687.utilities;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.team687.Robot;
// Test later if possible

public class Odometry implements Runnable {

    private double m_previousDistance, m_currentX, m_currentY;

    public Odometry() {
        m_previousDistance = 0;
    }

    @Override
    public void run() {
        double m_currentDistance = (Robot.drive.getRightMasterPosition() + Robot.drive.getLeftMasterPosition())/2;
    	double m_distanceTraveled = (m_currentDistance - m_previousDistance);
    	double angle = Robot.drive.getRawYaw();
    	m_currentX = m_currentX + m_distanceTraveled * Math.sin(Math.toRadians(angle));
    	m_currentY = m_currentY + m_distanceTraveled * Math.cos(Math.toRadians(angle));
        m_previousDistance = m_currentDistance;
        SmartDashboard.putNumber("Robot X", m_currentX);
        SmartDashboard.putNumber("Robot X", m_currentX);
    }

    public void resetOdometry() {
        m_currentX = 0;
        m_currentY = 0;
    }

    public double getCurrentX() {
        return m_currentX;
    }

    public double getCurrentY() {
        return m_currentY;
    }
}
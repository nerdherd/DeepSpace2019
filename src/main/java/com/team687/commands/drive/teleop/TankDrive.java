package com.team687.commands.drive.teleop;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankDrive extends Command {
	
	private double m_leftPower, m_rightPower;
    public TankDrive() {
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString("Current Command", "Tank Drive");
    }


    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	m_leftPower = Robot.oi.getDriveJoyLeftY();
    	m_rightPower = Robot.oi.getDriveJoyRightY();
    	Robot.drive.setPower(-m_leftPower, -m_rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    protected void interrupted() {
    }
}

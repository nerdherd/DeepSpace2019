package com.team687.commands.drive.auto;

import com.team687.constants.DriveConstants;
import com.team687.utilities.NerdyMath;
import com.team687.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistancePID extends Command {

	private double m_distance;
	private double m_error;
	private double m_power;
	
    public DriveDistancePID(double distance) {
    	m_distance = distance;
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	m_error = m_distance - Robot.drive.getAverageEncoderPosition();
    	m_power = m_error * DriveConstants.kDriveP;    	
    	Robot.drive.setPower(m_power, m_power);   	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // return NerdyMath.errorTolerance(m_error, DriveConstants.kDriveTolerance);
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.setPowerZero();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

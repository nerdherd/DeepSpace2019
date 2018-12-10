package com.team687.commands.drive.auto;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Reset encoders
 */

public class ResetDriveEncoders extends Command {

    public ResetDriveEncoders() {
	requires(Robot.drive);
    }

    @Override
    protected void initialize() {
	SmartDashboard.putString("Current Drive Command", "ResetDriveEncoders");
	Robot.drive.resetEncoders();
    }

    @Override
    protected void execute() {
	Robot.drive.resetEncoders();
    }

    @Override
    protected boolean isFinished() {
	return Robot.drive.getAverageEncoderPosition() == 0;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}

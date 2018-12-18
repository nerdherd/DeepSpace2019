package com.team687.commands;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestDrive extends Command {

    public TestDrive() {
        // Use requires() here to declare subsystem dependencies
	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	Robot.drive.setPower(0.5, 0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

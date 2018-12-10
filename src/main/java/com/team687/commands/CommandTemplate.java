package com.team687.commands;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command template (copy and paste this to create a new command)
 */

public class CommandTemplate extends Command {

    public CommandTemplate() {
	requires(Robot.drive);
    }

    @Override
    protected void initialize() {
	SmartDashboard.putString("Current Command", "CommandTemplate");
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
	return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
	end();
    }

}

package com.team687.commands.drive.auto;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Reset gyro with a command so we do not have to enable/disable every time
 */

public class ResetGyro extends Command {

    public ResetGyro() {
	requires(Robot.drive);
    }

    @Override
    protected void initialize() {
	SmartDashboard.putString("Current Drive Command", "ResetGyro");
    Robot.drive.resetYaw();
    Robot.drive.resetXY();
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
    }

}

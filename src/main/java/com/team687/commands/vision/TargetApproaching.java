package com.team687.commands.vision;

import com.team687.Robot;
// import com.team687.util.NerdyPID;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetApproaching extends Command {

    // NerdyPID pid;

    public TargetApproaching() {
	SmartDashboard.putString("Current Command", "TargetApproaching");
	requires(Robot.drive);
	// pid = new NerdyPID();
	// pid.setPID(0.001, 0, 0.001);
	setTimeout(3.0);
    }

    protected void initialize() {
    }

    protected void execute() {

    }

    protected boolean isFinished() {
	return false;
	// return isTimedOut() || (x < Constants.kDistanceTolerance) ? true :
	// false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

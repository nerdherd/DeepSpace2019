package com.team687.commands.drive.auto;

import com.team687.Robot;
import com.team687.constants.DriveConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive for a specified time
 */

public class DriveTime extends Command {

    private double m_straightPower;
    private double m_timeout;
    private double m_startTime;

    /**
     * @param straightPower
     * @param timeout
     */
    public DriveTime(double straightPower, double timeout) {
	m_straightPower = straightPower;
	m_timeout = timeout;

	requires(Robot.drive);
    }

    @Override
    protected void initialize() {
	SmartDashboard.putString("Current Drive Command", "DriveTime");
	m_startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
	Robot.drive.setPower(m_straightPower, m_straightPower);
    }
// so, like, Dipsy made some comments on ur code. Hah. Hahaha. Good luck figuring out what we changed in your drive system. ;)
    @Override
    protected boolean isFinished() {
	return Timer.getFPGATimestamp() - m_startTime > m_timeout;
    }

    @Override
    protected void end() {
	Robot.drive.setPowerZero();
    }

    @Override
    protected void interrupted() {
	end();
    }

}

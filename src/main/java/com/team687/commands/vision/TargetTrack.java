package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TargetTrack extends Command {

    private double m_rotP, m_rotD, m_lastError, strPower, m_strP;
    private boolean isLockedOn;

    public TargetTrack(double kRotP, double kRotD) {
        requires(Robot.drive);
        requires(Robot.jevois);

        isLockedOn = false;

        m_rotP = kRotP;
        m_strP = 0.004;

        m_rotD = kRotD;
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        m_lastError = -Robot.jevois.getAngleToTurn();
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
    }

    @Override
    protected void execute() {
        double angularTargetError = -Robot.jevois.getAngleToTurn();
        if (Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband) {
            isLockedOn = true;
        }
        double rotPower = m_rotP * angularTargetError + m_rotD * (angularTargetError - m_lastError);

        double pow = m_strP * Robot.jevois.getDistance() + 0.1;
        if (Robot.oi.getDriveJoyRightY() > pow && Robot.jevois.getContourNum() > 0) {
            strPower = pow;
        } else {
            strPower = Robot.oi.getDriveJoyRightY();
        }

        if ((Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0)
                || (Robot.jevois.getDistance() > 95 && Robot.jevois.getContourNum() > 0)) {
            Robot.drive.setPowerFeedforward(0.8 * strPower, strPower);
        }
        else if ((Math.abs(angularTargetError) > VisionConstants.kDriveRotationDeadband) && isLockedOn == false) {
            Robot.drive.setPowerFeedforward(rotPower, -rotPower);

        } else {
            Robot.drive.setPowerFeedforward(rotPower + (0.8 * strPower), -rotPower + strPower);
        }

        m_lastError = angularTargetError;
    }

    @Override
    protected boolean isFinished() {
        return Robot.oi.operatorJoy.getRawButton(10);
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
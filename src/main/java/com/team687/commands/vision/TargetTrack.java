package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TargetTrack extends Command {

    private double m_rotP, m_rotD, m_lastError, strPower, m_strP;
    private double yaw = 0;
    private boolean isLockedOn;

    public TargetTrack(double kRotP, double kRotD) {
        requires(Robot.drive);
        requires(Robot.jevois);

        m_rotP = kRotP;
        m_strP = 0.004;

        m_rotD = kRotD;
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        m_lastError = -Robot.jevois.getAngleToTurn();
        SmartDashboard.putString("Current Command", "LiveTargetTrack");

        isLockedOn = false;
    }

    @Override
    protected void execute() {
        double angularTargetError = -Robot.jevois.getAngleToTurn() * 0.95;
        if (Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband) {
            isLockedOn = true;
        } else {
            isLockedOn = false;
        }
        double rotPower = m_rotP * angularTargetError + m_rotD * (angularTargetError - m_lastError);

        // double pow = m_strP * Robot.jevois.getDistance() + 0.1;
        // if (Robot.oi.getDriveJoyRightY() > pow && Robot.jevois.getContourNum() > 0) {
        //     strPower = pow;
        // } else {
        //     strPower = Robot.oi.getDriveJoyRightY();
        // }

        strPower = Robot.oi.getDriveJoyRightY();

        if ((Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0)
                || (Robot.jevois.getDistance() > 95 && Robot.jevois.getContourNum() > 0)) {
            Robot.drive.setPowerFeedforward(0.9 * strPower, strPower);
            SmartDashboard.putString("State:", "1");
        }
        else if ((Math.abs(angularTargetError) > VisionConstants.kDriveRotationDeadband) && isLockedOn == false) {
            Robot.drive.setPowerFeedforward(rotPower, -rotPower);
            SmartDashboard.putString("State:", "2");


        } else {
            Robot.drive.setPowerFeedforward((0.9 * strPower), strPower);
            SmartDashboard.putString("State:", "3");

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
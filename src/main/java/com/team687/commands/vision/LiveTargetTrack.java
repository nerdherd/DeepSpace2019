package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiveTargetTrack extends Command {

    private double m_rotP, m_rotD, m_lastError, strPower, m_strP;

    public LiveTargetTrack(double kRotP, double kRotD) {
        requires(Robot.drive);
        requires(Robot.jevois);

        m_rotP = kRotP;
        m_strP = 0.004;

        m_rotD = kRotD;
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        // m_lastError = 0;
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
    }

    @Override
    protected void execute() {
        double angularTargetError = -Robot.jevois.getAngleToTurn();
        // double power = m_rotP * angularTargetError;
        double rotPower = m_rotP * angularTargetError + m_rotD * (angularTargetError - m_lastError);

        double pow = m_strP * Robot.jevois.getDistance() +0.1;
        if(Robot.oi.getDriveJoyRightY() > pow && Robot.jevois.getContourNum() > 0){
            // double strPower = m_strP * Robot.oi.getDriveJoyRightY() +0.25;
            strPower = pow;
        }
        else{
            strPower = Robot.oi.getDriveJoyRightY();
        }

        if(Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0) {
            Robot.drive.setPowerFeedforward(strPower, 0.7 * strPower);
        }

        else if(Robot.jevois.getDistance() > 95 && Robot.jevois.getContourNum() > 0) {
            Robot.drive.setPowerFeedforward(strPower, 0.7 * strPower);

        }
        
        else if(!(Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband)){
            Robot.drive.setPowerFeedforward(rotPower + strPower, -rotPower + strPower);
        }
        else{
            Robot.drive.setPowerFeedforward(strPower, 0.7 *  strPower);
        }       
        
        SmartDashboard.putBoolean("Target Detected", Robot.jevois.getContourNum() > 0);
        m_lastError = angularTargetError;
    }

    @Override
    protected boolean isFinished() {
        return Robot.oi.operatorJoy.getRawButton(10);// || 
            // !(Robot.oi.driveJoyLeft.getRawButton(1) || 
            // Robot.oi.driveJoyRight.getRawButton(1));
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
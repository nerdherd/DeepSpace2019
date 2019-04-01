package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoLiveTargetTrack extends Command {

    private double m_rotP, m_rotD, m_lastError, strPower, m_strP, m_straightPower, m_distance, m_initialDist;
    private boolean isLockedOn;

    public AutoLiveTargetTrack(double straightPower, double dist, double kRotP, double kRotD) {
        requires(Robot.drive);
        requires(Robot.jevois);

        isLockedOn = false;

        m_rotP = kRotP;
        m_strP = 0.004;

        m_rotD = kRotD;
        m_straightPower = straightPower;
        m_distance = dist;
        
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        m_lastError = -Robot.jevois.getAngleToTurn();
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
        m_initialDist = Robot.drive.getAverageEncoderPosition();
    }

    @Override
    protected void execute() {
        
        double angularTargetError = -Robot.jevois.getAngleToTurn();
        if(Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband){
            isLockedOn = true;
        }
        // double power = m_rotP * angularTargetError;
        double rotPower = m_rotP * angularTargetError + m_rotD * (angularTargetError - m_lastError);

        double pow = m_strP * Robot.jevois.getDistance() +0.1;
        if(m_straightPower > pow && Robot.jevois.getContourNum() > 0){
            // double strPower = m_strP * Robot.oi.getDriveJoyRightY() +0.25;
            strPower = pow;
        }
        else{
            strPower = m_straightPower;
        }

        if(Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0) {
            Robot.drive.setPowerFeedforward(0.8 *  strPower, strPower);
        }

        else if(Robot.jevois.getDistance() > 95 && Robot.jevois.getContourNum() > 0) {
            Robot.drive.setPowerFeedforward(0.8 *  strPower, strPower);

        }
        
        else if(!(Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband) && isLockedOn == false ){
            // Robot.drive.setPowerFeedforward(rotPower + strPower, -rotPower + strPower);
            Robot.drive.setPowerFeedforward(rotPower, -rotPower);
           
        }
        else{
            Robot.drive.setPowerFeedforward(rotPower + (0.8 *  strPower), -rotPower + strPower);
        }       
        
        SmartDashboard.putBoolean("Target Detected", Robot.jevois.getContourNum() > 0);
        m_lastError = angularTargetError;
    }

    @Override
    protected boolean isFinished() {
        // return ;
        return Robot.drive.getAverageEncoderPosition() - m_initialDist >= m_distance;
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
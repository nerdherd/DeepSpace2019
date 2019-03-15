package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoLiveTargetTrack extends Command {

    private double m_rotP, m_rotD, m_lastError, m_strP, visionStrPower, m_startTime, m_timeout, m_straightPower;

    public AutoLiveTargetTrack(double kRotP, double straightPower, double timeout) {
        requires(Robot.drive);
        requires(Robot.jevois);
        // strPower = straightPower;
        m_straightPower = straightPower;
        m_rotP = kRotP;
        m_strP = 0.003;
        m_timeout = timeout;
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        // m_lastError = 0;
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
        m_startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void execute() {
        double angularTargetError = -Robot.jevois.getAngleToTurn();
        double power = m_rotP * angularTargetError;
        
        double pow = m_strP * Robot.jevois.getDistance();
        if(Robot.jevois.getContourNum() > 0){
            // double strPower = m_strP * Robot.oi.getDriveJoyRightY() +0.25;
            visionStrPower = pow;
        } else {
            visionStrPower = m_straightPower;
        }

        if(Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0) {
            Robot.drive.setPowerFeedforward(visionStrPower, 0.7 * visionStrPower);
        }
        
        else if(!(Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband)){
            Robot.drive.setPowerFeedforward(power + visionStrPower, -power + visionStrPower);
        }
        else{
            Robot.drive.setPowerFeedforward(visionStrPower, visionStrPower);
        }  
        
        SmartDashboard.putBoolean("Target Detected", Robot.jevois.getContourNum() > 0);
        SmartDashboard.putNumber("Calculated number" , pow);
        // m_lastError = angularTargetError;
    }

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
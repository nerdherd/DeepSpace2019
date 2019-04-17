package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends Command {

    private double m_rotP, m_rotD, m_lastError, strPower, m_strP;

    public TurnToAngle(double kRotP) {
        // requires(Robot.drive);
        // requires(Robot.jevois);

        // m_rotP = kRotP;
        // m_strP = 0.004;

        // m_rotD = kRotD;
    }

    @Override
    protected void initialize() {
        Robot.jevois.enableStream();
        SmartDashboard.putString("Current Command", "TurnToAngle");
    }

    @Override
    protected void execute() {
        // double angularTargetError = -Robot.jevois.getAngleToTurn();
        // // double power = m_rotP * angularTargetError;
        // double rotPower = m_rotP * angularTargetError + m_rotD * (angularTargetError - m_lastError);

        // double pow = m_strP * Robot.jevois.getDistance() +0.1;
        // if(Robot.oi.getDriveJoyRightY() > pow && Robot.jevois.getContourNum() > 0){
        //     // double strPower = m_strP * Robot.oi.getDriveJoyRightY() +0.25;
        //     strPower = pow;
        // }
        // else{
        //     strPower = Robot.oi.getDriveJoyRightY();
        // }

        // if(Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0) {
        //     Robot.drive.setPowerFeedforward(0.8 *  strPower, strPower);
        // }

        // else if(Robot.jevois.getDistance() > 95 && Robot.jevois.getContourNum() > 0) {
        //     Robot.drive.setPowerFeedforward(0.8 *  strPower, strPower);

        // }
        
        // else if(!(Math.abs(angularTargetError) < VisionConstants.kDriveRotationDeadband)){
        //     Robot.drive.setPowerFeedforward(rotPower + strPower, -rotPower + strPower);
        // }
        // else{
        //     Robot.drive.setPowerFeedforward(0.8 *  strPower, strPower);
        // }       
        
        // SmartDashboard.putBoolean("Target Detected", Robot.jevois.getContourNum() > 0);
        // m_lastError = angularTargetError;
    }

    @Override
    protected boolean isFinished() {
        return false;
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
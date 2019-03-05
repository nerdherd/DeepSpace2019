package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiveTargetTrack extends Command {

    private double m_rotP;

    public LiveTargetTrack(double kRotP) {
        requires(Robot.drive);
        requires(Robot.jevois);

        m_rotP = kRotP;
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
    }

    @Override
    protected void execute() {
        double getAngularTargetError = Robot.jevois.getAngleToTurn();
        double power = m_rotP * getAngularTargetError;

        if(Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0) {
            Robot.drive.setPowerFeedforward(Robot.oi.getDriveJoyRightY(), Robot.oi.getDriveJoyRightY());
        }
        
        if(!(Math.abs(getAngularTargetError) < VisionConstants.kDriveRotationDeadband)){
            Robot.drive.setPowerFeedforward(power + Robot.oi.getDriveJoyRightY(), -power + Robot.oi.getDriveJoyRightY());
        }
        else{
            Robot.drive.setPowerFeedforward(Robot.oi.getDriveJoyRightY(), Robot.oi.getDriveJoyRightY());
        }       
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
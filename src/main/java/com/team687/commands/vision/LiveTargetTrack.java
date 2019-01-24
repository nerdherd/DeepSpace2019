package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiveTargetTrack extends Command {

    private double kP = 0.00125;
    private double m_error;
    private double  m_desiredAngle;


    public LiveTargetTrack() {
        requires(Robot.drive);
        requires(Robot.jevois);
    }

    @Override

   
    protected void initialize() {
        Robot.jevois.enableStream();
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
        // double robotAngle = (360 - Robot.drive.getRawYaw()) % 360;
        // m_desiredAngle = robotAngle + Robot.jevois.getAngularTargetError() - Robot.jevois.getOffset();

        
    }

    @Override
    protected void execute() {
        // double relativeAngleError = Robot.jevois.getAngularTargetError();

        // double power = kP * relativeAngleError;
        // Robot.drive.setPowerFeedforward(power, -power);
        // SmartDashboard.putNumber("Rotational Power", power);

        double getAngularTargetError = Robot.jevois.getAngularTargetError() - Robot.jevois.getOffset();
        double robotAngle = (360 - Robot.drive.getRawYaw()) % 360;
        m_desiredAngle = robotAngle + Robot.jevois.getAngularTargetError();

        SmartDashboard.putNumber("Robot Angle", robotAngle);
        SmartDashboard.putNumber("Desired Angle", m_desiredAngle);
        SmartDashboard.putNumber("Angular Target Error", getAngularTargetError); 
        
        double power = kP * getAngularTargetError;
        Robot.drive.setPowerFeedforward(power - Robot.oi.getDriveJoyLeftY(), -power - Robot.oi.getDriveJoyLeftY());
        SmartDashboard.putNumber("Left Power", power - Robot.oi.getDriveJoyLeftY());
        SmartDashboard.putNumber("Right Power", -power - Robot.oi.getDriveJoyLeftY());
        // SmartDashboard.putNumber("Robot Angle", robotAngle);
        
    }

    @Override
    protected boolean isFinished() {
        double relativeAngleError = Robot.jevois.getAngularTargetError();
        return (Math.abs(relativeAngleError) <= Constants.kDriveRotationDeadband);
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
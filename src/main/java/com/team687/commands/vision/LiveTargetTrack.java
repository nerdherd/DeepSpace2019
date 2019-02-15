package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiveTargetTrack extends Command {

    private double kP = 0.0139;
    // private double kP = 0.0042;
    private double  m_desiredAngle;


    public LiveTargetTrack() {
        requires(Robot.drive);
        requires(Robot.jevois);
        // requires(Robot.rightJevois);
    }

    @Override

   
    protected void initialize() {
        Robot.jevois.enableStream();
        // Robot.rightJevois.enableStream();
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
        // double robotAngle = (360 - Robot.drive.getRawYaw()) % 360;
        // m_desiredAngle = robotAngle + Robot.leftJevois.getAngularTargetError();
        
        boolean headingFound = false;
        double heading = 0;


        double getAngularTargetError = Robot.jevois.getOffsetAngleToTurn();
        double robotAngle = (360 - Robot.drive.getRawYaw()) % 360;
        double rotError;
        double power = -kP * getAngularTargetError;
        double rotPower;

        if(!(Math.abs(getAngularTargetError) < Constants.kDriveRotationDeadband)){
            Robot.drive.setPowerFeedforward(power + -Robot.oi.getDriveJoyLeftY(), -power + -Robot.oi.getDriveJoyLeftY());
        }
        else{
            Robot.drive.setPowerFeedforward(-Robot.oi.getDriveJoyLeftY(), -Robot.oi.getDriveJoyLeftY());
            // Robot.drive.setPowerFeedforward(-Robot.oi.getDriveJoyLeftY(), -Robot.oi.getDriveJoyLeftY());
        }

        if(Robot.jevois.getDistance() < 35) {
            if(headingFound == false){
                headingFound = true;
                heading = Robot.drive.getRawYaw();
            }
            rotError = -heading - robotAngle;
            
            rotError = (rotError > 180) ? rotError - 360 : rotError;
            rotError = (rotError < -180) ? rotError + 360 : rotError;
            rotPower = rotError * -kP;
            Robot.drive.setPowerFeedforward(rotPower + Robot.oi.getDriveJoyLeftY(), rotPower - Robot.oi.getDriveJoyLeftY());
        }

        // SmartDashboard.putNumber("Robot Angle", robotAngle);
        // SmartDashboard.putNumber("Desired Angle", m_desiredAngle);
        // SmartDashboard.putNumber("Angular Target Error", getAngularTargetError); 
        
        SmartDashboard.putNumber("Left Power", power);
        SmartDashboard.putNumber("Right Power", -power);
        // SmartDashboard.putNumber("Robot Angle", robotAngle);
        
    }

    @Override
    protected boolean isFinished() {
        // double getAngularTargetError = Robot.jevois.getAngularTargetError();

        return(false);
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
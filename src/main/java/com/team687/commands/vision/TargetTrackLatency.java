package com.team687.commands.vision;

import java.util.*;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TargetTrackLatency extends Command {

    private double m_rotP;
    private double theta_c, theta_g, theta_now;
    private double x_c, x_g, x_now;    
    private double y_c, y_g, y_now;
    private double hyp_now;
    private HashMap<Integer, Double> timestamp;


    public TargetTrackLatency(double kRotP) {
        requires(Robot.drive);
        requires(Robot.jevois);

        m_rotP = kRotP;
    }

    @Override

    protected void initialize() {
        Robot.jevois.enableStream();
        x_now = Robot.drive.getXpos(); //initialize now vars
        y_now = Robot.drive.getYpos();
        hyp_now = Math.sqrt(Math.pow(x_now,2) + Math.pow(y_now,2));
        theta_now = Robot.drive.getRawYaw();
        timestamp = new HashMap<>();
    }

    @Override
    protected void execute() {
        // timestamp loop
        
        // pose control
        theta_c = theta_now; //set c to prev timestamp
        x_c = x_now;
        y_c = y_now;

        x_now = Robot.drive.getXpos(); //update now vars
        y_now = Robot.drive.getYpos();
        hyp_now = Math.sqrt(Math.pow(x_now,2) + Math.pow(y_now,2));
        theta_now = Robot.drive.getRawYaw();

       
        x_g = x_now + Math.cos(Robot.jevois.getAngleToTurn()) * hyp_now;
        y_g = y_now + Math.sin(Robot.jevois.getAngleToTurn()) * hyp_now;
        theta_g = Math.atan2(y_g-y_now, x_g-x_now); //initialize goal vars


        SmartDashboard.putString("Current Command", "TargetTrackLatency");


        // double getAngularTargetError = Robot.jevois.getAngleToTurn();
        double robotAngle = (360 - Robot.drive.getRawYaw()) % 360;
        double power = -m_rotP * theta_g; //getAngularTargetError
        if (!(Math.abs(theta_g) < VisionConstants.kDriveRotationDeadband)) { //getAngularTargetError
            Robot.drive.setPowerFeedforward(power + -Robot.oi.getDriveJoyRightY(),
                    -power + -Robot.oi.getDriveJoyRightY());
        } else {
            Robot.drive.setPowerFeedforward(-Robot.oi.getDriveJoyRightY(), -Robot.oi.getDriveJoyRightY());
        }

        SmartDashboard.putNumber("Left Power", power);
        SmartDashboard.putNumber("Right Power", -power);
        SmartDashboard.putNumber("Robot Angle", robotAngle);
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
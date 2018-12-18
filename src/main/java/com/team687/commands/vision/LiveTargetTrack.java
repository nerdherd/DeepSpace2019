package com.team687.commands.vision;

import com.team687.Constants;
import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiveTargetTrack extends Command {

    private double kP = 0.013;

    public LiveTargetTrack() {
        requires(Robot.drive);
        requires(Robot.jevois);
    }

    @Override
    protected void initialize() {
        SmartDashboard.putString("Current Command", "LiveTargetTrack");
    }

    @Override
    protected void execute() {
        double relativeAngleError = Robot.jevois.getAngularTargetError();

        double power = kP * -relativeAngleError;

        if (Math.abs(relativeAngleError) <= Constants.kDriveRotationDeadband || (Math.abs(relativeAngleError) >= 25)) {
            power = 0;
        } else {
            Robot.drive.setPower(power, power);
        }
        SmartDashboard.putNumber("Rotational Power", power);

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
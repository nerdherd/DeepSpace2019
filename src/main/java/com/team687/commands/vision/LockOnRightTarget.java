/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import com.team687.Constants;
import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LockOnRightTarget extends Command {

  private double kP;

  public LockOnRightTarget() {
    requires(Robot.drive);
    requires(Robot.jevois);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {

    double relativeAngleError = Robot.jevois.getAngularTargetError();

    if (Robot.jevois.getContourID() > 0) {
      if (Math.abs(relativeAngleError) <= Constants.kDriveRotationDeadband || (Math.abs(relativeAngleError) >= 25)) {
        Robot.drive.setPower(0, 0);
      } else {
        double power = -relativeAngleError * kP;
        Robot.drive.setPower(power, power);
      }
    }

    else {
      Robot.drive.setPower(-0.2, -0.2);
    }
  }

  @Override
  protected boolean isFinished() {
    double relativeAngleError = Robot.jevois.getAngularTargetError();
    return (Math.abs(relativeAngleError) <= Constants.kDriveRotationDeadband);
  }

  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

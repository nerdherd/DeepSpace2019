/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class FindLeftContour extends Command {

  private double kP = 0.012;

  public FindLeftContour() {
    requires(Robot.drive);
    requires(Robot.jevois);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    
    if (Robot.jevois.getContourID() > 0) {
      double power = Robot.jevois.getAngularTargetError() * kP;
      Robot.drive.setPower(power, power);
    }
    else {
      Robot.drive.setPower(0.2, 0.2);
    }
  }

  @Override
  protected boolean isFinished() {
    return (Robot.jevois.getContourID() > 0);
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}

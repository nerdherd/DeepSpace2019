/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.Robot;
import com.team687.subsystems.Superstructure;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeOrOuttakeRollers extends Command {

  private double m_leftPow, m_rightPow;

  public IntakeOrOuttakeRollers(double leftPow, double rightPow) {
    requires(Robot.intake);
    m_leftPow = leftPow;
    m_rightPow = rightPow;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Superstructure.getInstance().isHatchMode) {
      Robot.intake.setPower(-m_leftPow, -m_rightPow);
    } else {
      Robot.intake.setPower(m_leftPow, m_rightPow);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

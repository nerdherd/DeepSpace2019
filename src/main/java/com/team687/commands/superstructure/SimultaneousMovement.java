/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SimultaneousMovement extends Command {

  private double m_elevatorHeight, m_armAngle;

  public SimultaneousMovement(double elevatorHeightInches, double armAngleDegrees) {
    requires(Robot.arm);
    requires(Robot.elevator);
    m_elevatorHeight = elevatorHeightInches;
    m_armAngle = armAngleDegrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevator.setHeightMotionMagic(m_elevatorHeight);
    Robot.arm.setAngleMotionMagic(m_armAngle);
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

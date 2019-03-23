/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.subsystems.Arm;
import com.team687.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class SimultaneousMovement extends Command {

  protected double m_elevatorHeight;
protected double m_armAngle;

  public SimultaneousMovement(double elevatorHeightInches, double armAngleDegrees) {
    requires(Arm.getInstance());
    requires(Elevator.getInstance());
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
    Elevator.getInstance().setHeightMotionMagic(m_elevatorHeight);
    Arm.getInstance().setAngleMotionMagic(m_armAngle);
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

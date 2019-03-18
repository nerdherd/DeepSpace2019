/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.team687.Robot;
import com.team687.constants.ClimberConstants;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberClimb extends Command {
  public ClimberClimb() {
    requires(Robot.climberFoot);
    requires(Robot.climbStinger1);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climberFoot.setForwards();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climbStinger1.setAngle(ClimberConstants.kClimbAngle);
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

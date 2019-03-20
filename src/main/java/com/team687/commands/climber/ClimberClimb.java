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
    requires(Robot.climbStingerLeft);
    requires(Robot.climbStingerRight);
    requires(Robot.drive);
    requires(Robot.climberWheelLeft);
    requires(Robot.climberWheelRight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.climberFoot.setForwards();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climbStingerLeft.setAngle(ClimberConstants.kClimbAngle);
    Robot.climbStingerRight.setAngle(ClimberConstants.kClimbAngle);
    
    if (Robot.climbStingerLeft.getAngle() < ClimberConstants.kStartClimbingAngle) {
      Robot.climberFoot.setForwards();
    }
    Robot.climberWheelLeft.setPower(Robot.oi.getDriveJoyRightY());
    Robot.climberWheelRight.setPower(Robot.oi.getDriveJoyRightY());

    Robot.drive.setPower(Robot.oi.getDriveJoyRightY(), Robot.oi.getDriveJoyRightY());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climberFoot.setReverse();
    Robot.climbStingerLeft.setAngle(ClimberConstants.kStinger1StowAngle);
    Robot.climbStingerRight.setAngle(ClimberConstants.kStinger2AngleOffset);
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

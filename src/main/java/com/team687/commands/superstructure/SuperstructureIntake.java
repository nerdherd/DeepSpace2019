/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SuperstructureIntake extends Command {

  // There's no real reason to make this do the optimized thing here
  // I think
  // I might actually change this later when my brain is working enough to solve the equations
  public SuperstructureIntake() {
    requires(Robot.intake);
    requires(Robot.arm);
    requires(Robot.elevator);
    requires(Robot.claw);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.claw.setForwards();
    Robot.intake.setPower(0.75, -0.75);
    Robot.arm.setAngleMotionMagic(-15);
    Robot.elevator.setHeightMotionMagic(10);
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

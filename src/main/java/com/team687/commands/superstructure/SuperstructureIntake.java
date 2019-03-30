/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.Robot;
import com.team687.constants.SuperstructureConstants;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Elevator;
import com.team687.subsystems.Superstructure;

import edu.wpi.first.wpilibj.command.Command;

public class SuperstructureIntake extends Command {

  // There's no real reason to make this do the optimized thing here
  // I think
  // I might actually change this later when my brain is working enough to solve the equations
  public SuperstructureIntake() {
    requires(Robot.intake);
    requires(Arm.getInstance());
    requires(Elevator.getInstance());
    requires(Robot.claw);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Superstructure.getInstance().isHatchMode = false;
    Robot.claw.setForwards();
    Robot.intake.setPower(SuperstructureConstants.kCargoIntakeVoltage, 
      -SuperstructureConstants.kCargoIntakeVoltage);
    Arm.getInstance().setAngleMotionMagic(SuperstructureConstants.kCargoIntakeArmAngle);
    Elevator.getInstance().setHeightMotionMagic(SuperstructureConstants.kCargoIntakeElHeight);
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

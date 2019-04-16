/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.team687.Robot;
import com.team687.constants.ClimberConstants;
import com.team687.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class WaitForSuck extends Command {

  private int m_detectionCounter;

  public WaitForSuck() {
    requires(Climber.getInstance());
    requires(Robot.vacuum);
    requires(Robot.climberRatchet);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Climber.getInstance().setPower(0);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.mapSensor.getRaw() < 1.75) {
      m_detectionCounter++;
    } else {
      m_detectionCounter = 0;
    }
    Robot.vacuum.setPower(ClimberConstants.kSuckPower);
    Robot.climberRatchet.setForwards();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_detectionCounter > 5;
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

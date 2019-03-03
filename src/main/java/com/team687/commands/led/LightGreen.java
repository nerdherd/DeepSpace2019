/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.led;

import edu.wpi.first.wpilibj.command.Command;

import com.team687.Robot;

import edu.wpi.first.wpilibj.Timer;

public class LightGreen extends Command {

  public LightGreen() {
    requires(Robot.led);
    requires(Robot.jevois);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Timer.delay(0.05);
    // Robot.led.makeLEDGreen();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    if(Robot.jevois.getContourNum() >= 2) {
      Robot.led.setColorRGB(0, 255, 0);
    }
    else {
      Robot.led.setColorRGB(255, 0, 0);
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

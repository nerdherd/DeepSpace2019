/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.mechanisms.SetElevatorHeightPID;
import com.team687.constants.ClimberConstants;
import com.team687.subsystems.Climber;

public class ClimberReady extends SetElevatorHeightPID {
  public ClimberReady() {
    super(Climber.getInstance(), ClimberConstants.kDesiredUpPos);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return (Math.abs(Climber.getInstance().getHeight() - ClimberConstants.kDesiredUpPos) < 
            ClimberConstants.kPositionTolerance);     

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

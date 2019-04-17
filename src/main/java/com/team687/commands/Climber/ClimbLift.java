/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.team687.constants.ClimberConstants;
import com.team687.subsystems.Climber;

public class ClimbLift extends SetMotorPower {
  public ClimbLift() {
    super(Climber.getInstance(), ClimberConstants.kDesiredLiftPow);
  }

  @Override
  protected boolean isFinished() {
    return Climber.getInstance().getHeight() < ClimberConstants.kClimbGoodPos;
  }

  @Override
  protected void end() {
    Climber.getInstance().setPower(0);
  }

  @Override
  protected void interrupted() {
    this.end();
  }
}

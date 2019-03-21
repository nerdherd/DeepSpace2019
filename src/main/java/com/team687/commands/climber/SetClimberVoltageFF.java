/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.mechanisms.SetMechanismVoltageWithFF;
import com.team687.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetClimberVoltageFF extends CommandGroup {
  /**
   * Add your docs here.
   */
  public SetClimberVoltageFF(double voltage) {
    addParallel(new SetMechanismVoltageWithFF(Robot.climbStingerLeft, voltage));
    addParallel(new SetMechanismVoltageWithFF(Robot.climbStingerRight, voltage));
  }
}

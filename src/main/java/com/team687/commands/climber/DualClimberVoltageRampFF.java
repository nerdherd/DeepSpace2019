/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.MotorVoltageRamping;
import com.nerdherd.lib.motor.commands.mechanisms.MechanismVoltageRampingWithFF;
import com.team687.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DualClimberVoltageRampFF extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DualClimberVoltageRampFF() {
    addParallel(new MechanismVoltageRampingWithFF(Robot.climbStingerLeft, -0.125/12));
    addParallel(new MechanismVoltageRampingWithFF(Robot.climbStingerRight, -0.125/12));
  }
}

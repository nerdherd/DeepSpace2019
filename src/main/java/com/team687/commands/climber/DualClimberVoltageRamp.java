/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.MotorVoltageRamping;
import com.team687.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DualClimberVoltageRamp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DualClimberVoltageRamp() {
    addParallel(new MotorVoltageRamping(Robot.climbStingerLeft, -0.25/12));
    addParallel(new MotorVoltageRamping(Robot.climbStingerRight, -0.25/12));
  }
}

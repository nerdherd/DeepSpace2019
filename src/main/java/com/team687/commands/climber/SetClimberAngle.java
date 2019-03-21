/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.mechanisms.SetArmAngleMotionMagic;
import com.team687.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetClimberAngle extends CommandGroup {
  /**
   * Add your docs here.
   */
  public SetClimberAngle(double angle) {
    addParallel(new SetArmAngleMotionMagic(Robot.climbStingerLeft, angle));
    addParallel(new SetArmAngleMotionMagic(Robot.climbStingerRight, angle));
  }
}

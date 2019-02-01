/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.misc.WaitTime;
import com.nerdherd.lib.motor.commands.SetMotorPositionPID;
import com.nerdherd.lib.pneumatics.commands.RetractPiston;
import com.team687.Robot;
import com.team687.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RetractChevalRamp extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RetractChevalRamp() {
    addSequential(new RetractPiston(Robot.chevalLock));
    // addSequential(new WaitTime(0.1));
    addSequential(new SetMotorPositionPID(Robot.chevalRamp, SuperstructureConstants.kChevalRampRetractPosition, 100, false));
  }
}

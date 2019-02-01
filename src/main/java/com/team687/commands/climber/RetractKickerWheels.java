/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.SetMotorPositionPID;
import com.team687.Robot;
import com.team687.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RetractKickerWheels extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RetractKickerWheels() {
    addParallel(new SetMotorPositionPID(Robot.leftKickerWheel, SuperstructureConstants.kLeftKickerWheelDeployPosition, 100, false));
    addParallel(new SetMotorPositionPID(Robot.rightKickerWheel, SuperstructureConstants.kRightKickerWheelDeployPosition, 100, false));
  }
}

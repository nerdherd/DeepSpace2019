/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.SetMotorPositionPID;
import com.nerdherd.lib.motor.commands.SetMotorPower;
import com.team687.Robot;
import com.team687.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeployKickerWheels extends CommandGroup {
  /**
   * Add your docs here.
   */
  public DeployKickerWheels() {
    addParallel(new SetMotorPositionPID(Robot.leftKickerWheel, SuperstructureConstants.kLeftKickerWheelRetractPosition));
    addParallel(new SetMotorPositionPID(Robot.rightKickerWheel, SuperstructureConstants.kRightKickerWheelRetractPosition));
    addSequential(new SetMotorPower(Robot.leftKickerWheel, 0));
    addSequential(new SetMotorPower(Robot.rightKickerWheel, 0));
  }
}

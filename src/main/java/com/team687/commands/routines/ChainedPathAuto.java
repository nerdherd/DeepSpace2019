/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.routines;

import com.team687.commands.drive.auto.DriveTrajectory;
import com.team687.constants.AutoConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ChainedPathAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ChainedPathAuto() {
    addSequential(new DriveTrajectory(AutoConstants.testTraj, 5, true, 0.2, 0));
    addSequential(new DriveTrajectory(AutoConstants.testTraj2, 5, true, 0.2, 0));
  }
}

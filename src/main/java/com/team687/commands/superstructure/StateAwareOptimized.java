/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class StateAwareOptimized extends OptimizedSimultaneousMovement {
  public StateAwareOptimized(double desiredHeight) {
    super(desiredHeight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (Robot.claw.isForwards()) {
      super.m_desiredHeight += 8;
    }
    super.initialize();
  }
}

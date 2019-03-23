/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure.optimizedmovement;

import com.team687.subsystems.Superstructure;

public class TeleopOptimizedSimultaneous extends OptimizedSimultaneousMovement {

  private boolean wasHatchModeLastUpdate;

  public TeleopOptimizedSimultaneous(double desiredHeight) {
    super(desiredHeight);
  }

  public void adjustDesiredHeight(boolean isInitialize) {
    if (isInitialize) {
      if (!Superstructure.getInstance().isHatchMode) {
        super.m_desiredHeight += 8;
      }
    } else {
      if (!Superstructure.getInstance().isHatchMode) {
        super.m_desiredHeight += 8;
      } else {
        super.m_desiredHeight -= 8;
      }
    }
  }

  @Override
  protected void initialize() {
    adjustDesiredHeight(true);
    wasHatchModeLastUpdate = Superstructure.getInstance().isHatchMode;
    super.initialize();
  }

  @Override
  protected void execute() {
    if (wasHatchModeLastUpdate != Superstructure.getInstance().isHatchMode) {
      adjustDesiredHeight(false);
      super.initialize();
    }
    super.execute();
    wasHatchModeLastUpdate = Superstructure.getInstance().isHatchMode;
  }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.Robot;

/**
 * Add your docs here.
 */
public class TeleopSimultaneous extends SimultaneousMovement {

    private boolean m_wasHatchModeLastUpdate;
    private double m_initialElevatorHeight;

    public TeleopSimultaneous(double elevatorHeight) {
        super(elevatorHeight, 40);
        m_initialElevatorHeight = elevatorHeight;
    }

    public void adjustDesiredHeight(boolean isInitialize) {
        if (isInitialize) {
          if (!Robot.superstructureData.isHatchMode) {
            super.m_armAngle = 70;
            super.m_elevatorHeight = m_initialElevatorHeight + 4;
          } else {
            super.m_armAngle = 35;
            super.m_elevatorHeight = m_initialElevatorHeight;
          }
        } else {
          if (!Robot.superstructureData.isHatchMode) {
            super.m_armAngle = 70;
            super.m_elevatorHeight += 4;
          } else {
            super.m_armAngle = 35;
            super.m_elevatorHeight -= 4;
          }
        }
      }

    @Override
    protected void initialize() {
        adjustDesiredHeight(true);
        m_wasHatchModeLastUpdate = Robot.superstructureData.isHatchMode;
        super.initialize();
    }

    @Override
    protected void execute() {
        if (m_wasHatchModeLastUpdate != Robot.superstructureData.isHatchMode) {
            adjustDesiredHeight(false);
        }
        super.execute();
        m_wasHatchModeLastUpdate = Robot.superstructureData.isHatchMode;
    }

}

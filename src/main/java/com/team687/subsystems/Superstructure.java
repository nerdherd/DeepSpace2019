/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.misc.Loggable;
import com.nerdherd.lib.misc.NerdyBadlog;
import com.nerdherd.lib.misc.NerdyMath;
import com.team687.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This technically isn't a subsystem oops
 */
public class Superstructure implements Loggable { 
  
  public boolean isHatchMode;
  private static Superstructure m_superstructureInstance = null;

  private Superstructure() {
    isHatchMode = false;
  }
  
  public static Superstructure getInstance() {
    if (m_superstructureInstance == null) {
      m_superstructureInstance = new Superstructure();
    }
    return m_superstructureInstance;
  }

  public double getSuperstructureHeight() {
    return Arm.getArmHeight() + Robot.elevator.getHeight();
  }

  public void reportToSmartDashboard() {
    SmartDashboard.putBoolean("Is Hatch Mode?", Superstructure.getInstance().isHatchMode);
    SmartDashboard.putBoolean("Is Cargo Mode?", !Superstructure.getInstance().isHatchMode);
    SmartDashboard.putNumber("Superstructure Height", Arm.getArmHeight() + Robot.elevator.getHeight());
  }

  @Override
  public void initLoggingData() {
    NerdyBadlog.createTopic("Superstructure/HatchMode", () -> NerdyMath.boolToDouble(m_superstructureInstance.isHatchMode));
    NerdyBadlog.createTopic("Superstructure/Height", () -> Arm.getArmHeight() + Robot.elevator.getHeight());
  }

}

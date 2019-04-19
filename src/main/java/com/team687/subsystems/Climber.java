/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.nerdherd.lib.motor.NerdyTalon;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorElevator;
import com.team687.RobotMap;
import com.team687.constants.ClimberConstants;

/**
 * Add your docs here.
 */
public class Climber extends SingleMotorElevator {

  private static Climber m_climberInstance = new Climber();

  private Climber() {
    super(RobotMap.kClimberTalonID, "Climber", ClimberConstants.kClimberInversion, ClimberConstants.kClimberSensorPhase);
    super.configTalonDeadband(ClimberConstants.kClimberTalonDeadband);
    super.configFFs(ClimberConstants.kClimberGravityFF, 
      ClimberConstants.kClimberStaticFrictionFF);
    super.configMotionMagic(ClimberConstants.kClimberMotionMagicMaxAccel,
      ClimberConstants.kClimberMotionMagicCruiseVelocity);
    super.configPIDF(ClimberConstants.kClimberP, ClimberConstants.kClimberI,
      ClimberConstants.kClimberD, ClimberConstants.kClimberF);
    super.configHeightConversion(ClimberConstants.kClimberDistanceRatio,
      ClimberConstants.kClimberHeightOffset);
    super.configCurrentLimit(60, 30);
    super.motor.configFollowerVictors(new VictorSPX[]{new VictorSPX(RobotMap.kClimberVictorID)});
  }

  public static synchronized Climber getInstance() {
    return m_climberInstance;
  }

  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void reportToSmartDashboard() {
    super.reportToSmartDashboard();
    // SmartDashboard.putNumber(name + "Height", getHeight());
  }
}

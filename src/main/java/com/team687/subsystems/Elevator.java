/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.motor.single.mechanisms.SingleMotorElevator;
import com.team687.RobotMap;
import com.team687.constants.ElevatorConstants;

/**
 * Add your docs here.
 */
public class Elevator extends SingleMotorElevator {

  private static Elevator m_elevatorInstance = null;

  private Elevator() {
    super(RobotMap.kElevatorTalonID, "Elevator",
      ElevatorConstants.kElevatorInversion, ElevatorConstants.kElevatorSensorPhase);
    super.configTalonDeadband(ElevatorConstants.kElevatorTalonDeadband);
    super.configFFs(ElevatorConstants.kElevatorGravityFF, 
      ElevatorConstants.kElevatorStaticFrictionFF);
    super.configMotionMagic(ElevatorConstants.kElevatorMotionMagicMaxAccel,
      ElevatorConstants.kElevatorMotionMagicCruiseVelocity);
    super.configPIDF(ElevatorConstants.kElevatorP, ElevatorConstants.kElevatorI,
      ElevatorConstants.kElevatorD, ElevatorConstants.kElevatorF);
    super.configHeightConversion(ElevatorConstants.kElevatorDistanceRatio,
      ElevatorConstants.kElevatorHeightOffset);
  }

  public static Elevator getInstance() {
    if (m_elevatorInstance == null) {
      m_elevatorInstance = new Elevator();
    }
    return m_elevatorInstance;
  }

}

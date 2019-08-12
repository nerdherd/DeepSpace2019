/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.nerdherd.lib.misc.NerdyMath;
import com.team687.constants.ArmConstants;
import com.team687.constants.ElevatorConstants;
import com.team687.constants.SuperstructureConstants;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Elevator;
import com.team687.subsystems.Superstructure;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Adjusts the horizontal distance the arm extends while maintaining the same vertical height
 */
public class Thrust extends SimultaneousMovement {
  public Thrust(double extendDistance) {
    super(
      NerdyMath.boundBetween(
        Superstructure.getInstance().getSuperstructureHeight(), 
        ElevatorConstants.kMinElevatorHeight, 
        ElevatorConstants.kMaxElevatorHeight
      ), 

      NerdyMath.radiansToDegrees(Math.acos(NerdyMath.boundBetween(extendDistance, 0, 14) / 14.0))
    );
  }

  @Override
  protected void initialize() {
    super.initialize();
    Arm.getInstance().configMotionMagic(SuperstructureConstants.kArmThrustMaxAccel, SuperstructureConstants.kArmThrustMaxVel);
    Elevator.getInstance().configMotionMagic(SuperstructureConstants.kElevatorThrustMaxAccel, SuperstructureConstants.kElevatorThrustMaxVel);
  }

  @Override
  protected void end() {
    super.end();
    Arm.getInstance().configMotionMagic(ArmConstants.kArmMotionMagicMaxAccel, ArmConstants.kArmMotionMagicCruiseVelocity);
    Elevator.getInstance().configMotionMagic(ElevatorConstants.kElevatorMotionMagicMaxAccel, ElevatorConstants.kElevatorMotionMagicCruiseVelocity);
  }
}

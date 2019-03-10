/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.nerdherd.lib.motor.commands.ResetSingleMotorEncoder;
import com.team687.Robot;
import com.team687.subsystems.Arm;
import com.team687.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;

public class ZeroSuperstructure extends Command {

  private double m_armVoltage, m_elevatorVoltage;

  public ZeroSuperstructure(double armVoltage, double elevatorVoltage) {
    requires(Arm.getInstance());
    requires(Elevator.getInstance());
    m_armVoltage = armVoltage;
    m_elevatorVoltage = elevatorVoltage;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Arm.getInstance().setVoltage(m_armVoltage);
    Elevator.getInstance().setVoltage(m_elevatorVoltage);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // (new ResetSingleMotorEncoder(Robot.arm)).start();
    Arm.getInstance().resetEncoder();
    Arm.getInstance().setVoltage(0);
    Elevator.getInstance().resetEncoder();
    Elevator.getInstance().setVoltage(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

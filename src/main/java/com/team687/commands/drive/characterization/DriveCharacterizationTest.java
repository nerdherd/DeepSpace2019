/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.drive.characterization;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import com.team687.Robot;

public class DriveCharacterizationTest extends Command {

  private double m_voltage, m_startTime, m_time;
  private double m_rampRate;
  
  public DriveCharacterizationTest(double rampRate) {
    m_rampRate = rampRate;
    requires(Robot.drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      m_startTime = Timer.getFPGATimestamp();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    m_time = Timer.getFPGATimestamp() - m_startTime;
    m_voltage = (m_rampRate * m_time)/12;
    Robot.drive.setPower(m_voltage, m_voltage);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return m_time > 12 / m_rampRate;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drive.setPowerZero();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

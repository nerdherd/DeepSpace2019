/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.DriveConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAtHeading extends Command {

  private double m_initDistanceTicks;
  private double m_rotP;
  private double m_strP;

  /**
   * @param straightPower (determines direction and magnitude)
   * @param distance      (absolute value)
   * @param kRotP
   */
  public DriveAtHeading(double kRotP, double kStrP) {
    requires(Robot.drive);
    requires(Robot.jevois);

    m_rotP = kRotP;
    m_strP = kStrP;
  }

  @Override
  protected void initialize() {
    Robot.jevois.enableStream();
    Robot.drive.resetEncoders();

    SmartDashboard.putString("Current Command", "DriveAtHeading");
    SmartDashboard.putNumber("Distance in ft", Robot.jevois.getDistanceFeet());

    m_initDistanceTicks = Robot.drive.feetToTicks(Robot.jevois.getDistanceFeet(), DriveConstants.kLeftTicksPerFoot);
    SmartDashboard.putNumber("Initial Distance Ticks", m_initDistanceTicks);
  }

  @Override
  protected void execute() {
    double straightError = m_initDistanceTicks + Robot.drive.getAverageEncoderPosition();
    double straightPower = straightError * -m_strP;
    double angularTargetError = Robot.jevois.getAngleToTurn();
    double rotPower = -m_rotP * angularTargetError;

    Robot.drive.setPowerFeedforward(rotPower + (0.8 * straightPower), -rotPower + straightPower);
  }

  @Override
  protected boolean isFinished() {
    return Robot.drive.getAverageEncoderPosition() >= m_initDistanceTicks;
  }

  @Override
  protected void end() {
    Robot.drive.setPowerZero();
  }

  @Override
  protected void interrupted() {
    end();
  }

}

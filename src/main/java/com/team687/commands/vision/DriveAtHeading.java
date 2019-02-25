/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import com.team687.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAtHeading extends Command {

  private double m_straightPower;
  private double m_distance;
  private double m_rotP = 0.0139;


  /**
   * @param straightPower (determines direction and magnitude)
   * @param distance      (absolute value)
   * @param kRotP
   */
  public DriveAtHeading(double straightPower, double distance, double kRotP) {
    m_straightPower = straightPower;
    m_distance = distance;
    m_rotP = kRotP;

    requires(Robot.drive);
    requires(Robot.jevois);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putString("Current Command", "DriveAtHeading");
    SmartDashboard.putNumber("Distance in ft", Robot.jevois.getDistanceFeet());
    Robot.jevois.enableStream();

  }

  @Override
  protected void execute() {
    double getAngularTargetError = Robot.jevois.getOffsetAngleToTurn();
    double rotPower = -m_rotP * getAngularTargetError;

    Robot.drive.setPowerFeedforward(rotPower + -m_straightPower, rotPower - m_straightPower);

    double averagePositionFeet = (Robot.drive.getLeftPositionFeet() + Robot.drive.getRightPositionFeet()) / 2;
    SmartDashboard.putNumber("Average Position Feet", averagePositionFeet);
  }

  @Override
  protected boolean isFinished() {
    double averagePositionFeet = (Robot.drive.getLeftPositionFeet() + Robot.drive.getRightPositionFeet()) / 2;
    return averagePositionFeet >= m_distance;
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

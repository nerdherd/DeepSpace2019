/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.DriveConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveAtHeading extends Command {
  private double m_rotP = 0.0139;
  private double m_strP = 0.0000228571;
  private double m_initDistance;


  /**
   * @param straightPower (determines direction and magnitude)
   * @param distance      (absolute value)
   * @param kRotP
   */
  public DriveAtHeading(double kRotP, double kStrP) {
    m_rotP = kRotP;
    m_strP = kStrP;

    requires(Robot.drive);
    requires(Robot.jevois);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putString("Current Command", "DriveAtHeading");
    SmartDashboard.putNumber("Distance in ft", Robot.jevois.getDistanceFeet());
    Robot.jevois.enableStream();
    Robot.drive.resetEncoders();

    m_initDistance = Robot.drive.feetToTicks(Robot.jevois.getDistanceFeet(), DriveConstants.kTicksPerFootRight);
    SmartDashboard.getNumber("InitDistance", m_initDistance);
   

  }

  @Override
  protected void execute() {
    double getAngularTargetError = Robot.jevois.getOffsetAngleToTurn();
    double rotPower = -m_rotP * getAngularTargetError;
    // double straightPower = -m_strP * Robot.jevois.getDistance();
    double straightError = m_initDistance - Robot.drive.getAverageEncoderPosition();
    double straightPower = straightError * m_strP;

    Robot.drive.setPowerFeedforward(-(rotPower + -straightPower), -(-rotPower + -straightPower));
    

    double averagePositionFeet = (Robot.drive.getLeftPositionFeet() + Robot.drive.getRightPositionFeet()) / 2;
    SmartDashboard.putNumber("Average Position Feet", averagePositionFeet);
    SmartDashboard.putNumber("InitDistance", m_initDistance);
  }

  @Override
  protected boolean isFinished() {
    // double averagePositionFeet = (Robot.drive.getLeftPositionFeet() + Robot.drive.getRightPositionFeet()) / 2;
    return Robot.drive.getAverageEncoderPosition() >= m_initDistance;
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

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
  private double timeStart;
  private boolean lostTarget;
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
    lostTarget = false;
    // Robot.drive.resetEncoders();

    // SmartDashboard.putString("Current Command", "DriveAtHeading");
    // SmartDashboard.putNumber("Distance in ft", Robot.jevois.getDistanceFeet());

    // m_initDistanceTicks = Robot.drive.feetToTicks(Robot.jevois.getDistanceFeet(), DriveConstants.kTicksPerFootRight);
    // SmartDashboard.putNumber("Initial Distance", m_initDistanceTicks);

  }

  @Override
  protected void execute() {

    // double straightError = m_initDistanceTicks - Robot.drive.getAverageEncoderPosition();
    // double straightPower = straightError * m_strP;

    if(Robot.jevois.getDistance() <= 50 && !lostTarget){
        timeStart = Timer.getFPGATimestamp();
        Robot.drive.setPowerFeedforward(-0.15, -0.15);
        lostTarget = true;
    }
    else if(Robot.jevois.getDistance() > 50){
      double angularTargetError = Robot.jevois.getOffsetAngleToTurn();
      double rotPower = -m_rotP * angularTargetError;
      double straightPower = Robot.jevois.getDistance() * m_strP;

      Robot.drive.setPowerFeedforward(rotPower + -straightPower, -rotPower + -straightPower);  
    }
   
    double averagePositionFeet = (Robot.drive.getLeftPositionFeet() + Robot.drive.getRightPositionFeet()) / 2;
    SmartDashboard.putNumber("Average Position Feet", averagePositionFeet);
  }

  @Override
  protected boolean isFinished() {
    return Timer.getFPGATimestamp() - timeStart >= 2;
    // double averagePositionFeet = (Robot.drive.getLeftPositionFeet() +
    // Robot.drive.getRightPositionFeet()) / 2;
    // return Robot.drive.getAverageEncoderPosition() >= m_initDistance;
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

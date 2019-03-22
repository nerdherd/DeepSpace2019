/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.misc.NerdyMath;
import com.team687.Robot;
import com.team687.constants.ClimberConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;



public class ClimberClimb extends Command {

  private double m_integrator, m_lastTime;

  public ClimberClimb() {
    requires(Robot.climberFoot);
    requires(Robot.climbStingerLeft);
    requires(Robot.climbStingerRight);
    requires(Robot.drive);
    requires(Robot.climberWheelLeft);
    requires(Robot.climberWheelRight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.climberFoot.setReverse();
    m_integrator = 0;
    m_lastTime = Timer.getFPGATimestamp();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // m_integrator += ClimberConstants.kRollI * 
    //   (Timer.getFPGATimestamp() - m_lastTime) * Robot.drive.getRoll();
    Robot.climbStingerLeft.setPower(NerdyMath.boundBetween(
      Robot.oi.getOperatorJoyY(), // + 
      //(ClimberConstants.kRollP * Robot.drive.getRoll() + m_integrator), 
      -1, 0.25));
    Robot.climbStingerRight.setPower(NerdyMath.boundBetween(
      Robot.oi.getOperatorJoyY(), // -
      //(ClimberConstants.kRollP * Robot.drive.getRoll() + m_integrator), 
      -1, 0.25));
    // Robot.climbStingerLeft.setAngle(ClimberConstants.kLevel3Angle + 
    //   ClimberConstants.kRollP * Robot.drive.getRoll() + m_integrator);
    // Robot.climbStingerRight.setAngle(ClimberConstants.kLevel3Angle - 
    //   ClimberConstants.kRollP * Robot.drive.getRoll() + m_integrator);

    // if (Robot.climbStingerLeft.getAngle() < ClimberConstants.kStartClimbingAngle) {
    //   Robot.climberFoot.setForwards();
    // }
    Robot.climberWheelLeft.setPower(Robot.oi.getDriveJoyRightY());
    Robot.climberWheelRight.setPower(Robot.oi.getDriveJoyRightY());

    Robot.drive.setPower(Robot.oi.getDriveJoyRightY(), Robot.oi.getDriveJoyRightY());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climberFoot.setReverse();
    Scheduler.getInstance().add(new StowStingers());
    // Robot.climbStingerLeft.setAngle(ClimberConstants.kStinger1StowAngle);
    // Robot.climbStingerRight.setAngle(ClimberConstants.kStinger2AngleOffset);
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

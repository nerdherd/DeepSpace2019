/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.nerdherd.lib.misc.NerdyMath;
import com.team687.Robot;
import com.team687.constants.ArmConstants;
import com.team687.constants.ElevatorConstants;
import com.team687.constants.SuperstructureConstants;
import com.team687.subsystems.Arm;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class OptimizedSimultaneousMovement extends Command {

  private double m_desiredHeight, m_desiredHeightDelta, m_direction, m_thetaInitial;
  private double m_elevatorGoal, m_armGoal, m_timeTaken;

  private static final double kArmVmax = ArmConstants.kArmMotionMagicCruiseVelocity;
  private static final double kArmAccel = ArmConstants.kArmMotionMagicMaxAccel;
  private static final double kElVmax = ElevatorConstants.kElevatorMotionMagicCruiseVelocity;
  private static final double kElAccel = ElevatorConstants.kElevatorMotionMagicMaxAccel;
  private static final double kArmL = ArmConstants.kArmLength;

  public OptimizedSimultaneousMovement(double desiredHeight) {
    m_desiredHeight = desiredHeight;
    // requires(Robot.elevator);
    // requires(Robot.arm);

  }

  private double calcElevatorDistance(double thetaDelta) {
    double timeDelta = thetaDelta / kArmVmax + kArmVmax / kArmAccel;
    double heightDelta = kElVmax * timeDelta - (kElVmax * kElVmax / kElAccel);
    return heightDelta;
  }

  private void searchAndSetGoals() {
    // in radians relative to current angle
    double currentSearchAngle;
    if (m_direction > 0) {
      currentSearchAngle = (ArmConstants.kArmMaxAngleRads - m_thetaInitial)/2;
    } else {
      currentSearchAngle = (ArmConstants.kArmMinAngleRads - m_thetaInitial)/2;
    }
    // in inches relative to current elevator height
    double currentElHeightDelta;
    // in inches relative to current total height
    double currentTotalHeightDelta;

    boolean isCurrentSliceFar;
     
    for (int i = 0; i < SuperstructureConstants.kSimultaneousOptimizedSearchIterations; i++) {
      currentElHeightDelta = calcElevatorDistance(currentSearchAngle);
      currentTotalHeightDelta = currentElHeightDelta + kArmL * Math.sin(currentSearchAngle);
      isCurrentSliceFar = !(Math.abs(currentTotalHeightDelta) > Math.abs(m_desiredHeightDelta));
      if (isCurrentSliceFar) {
        currentSearchAngle = 1.5 * currentSearchAngle;
      } else {
        currentSearchAngle = 0.5 * currentSearchAngle;
      }
    }

    // in absolute degrees
    m_armGoal = Robot.arm.getAngle() + NerdyMath.radiansToDegrees(currentSearchAngle);
    double armGoalHeight = kArmL * Math.sin(NerdyMath.boundBetween(m_armGoal, ArmConstants.kArmMinAngle, ArmConstants.kArmMinAngle));
    m_elevatorGoal = NerdyMath.boundBetween(m_desiredHeight - armGoalHeight, 
      ElevatorConstants.kMinElevatorHeight, ElevatorConstants.kMaxElevatorHeight);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double initStartTime = Timer.getFPGATimestamp();
    m_direction = Math.signum(m_desiredHeightDelta - (Robot.elevator.getHeight() + 
      Arm.getArmHeight()));
    m_thetaInitial = NerdyMath.degreesToRadians(Robot.arm.getAngle());
    m_desiredHeightDelta = m_desiredHeight - Robot.elevator.getHeight() - Arm.getArmHeight();
    this.searchAndSetGoals();
    m_timeTaken = Timer.getFPGATimestamp() - initStartTime;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("elevator goal:");
    System.out.println(m_elevatorGoal);
    System.out.println("arm goal:");
    System.out.println(m_armGoal);
    System.out.println("Time taken:");
    System.out.print(m_timeTaken);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

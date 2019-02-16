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

  private static final double kArmVmax = ArmConstants.kArmMaxDegPerS;
  private static final double kArmAccel = ArmConstants.kArmDegPerS2;
  private static final double kElVmax = ElevatorConstants.kElevatorInPerS;
  private static final double kElAccel = ElevatorConstants.kElevatorInPerS2;
  private static final double kArmL = ArmConstants.kArmLength;
  private static final double kArmMaxAngle = ArmConstants.kArmMaxAngle;
  private static final double kArmMinAngle = ArmConstants.kArmMinAngle;

  public OptimizedSimultaneousMovement(double desiredHeight) {
    m_desiredHeight = desiredHeight;
    requires(Robot.elevator);
    requires(Robot.arm);

  }

  private double calcElevatorDistance(double thetaDelta) {
    double timeDelta = thetaDelta / kArmVmax + kArmVmax / kArmAccel;
    double heightDelta = kElVmax * (timeDelta - (kElVmax / kElAccel));
    return heightDelta;
  }

  private void searchAndSetGoals() {
    // in radians relative to current angle
    double currentSearchAngle;
    if (m_direction > 0) {
      currentSearchAngle = Math.abs(ArmConstants.kArmMaxAngle - m_thetaInitial)/2;
    } else {
      currentSearchAngle = -Math.abs(ArmConstants.kArmMinAngle - m_thetaInitial)/2;
    }
    // in inches relative to current elevator height
    double currentElHeightDelta;
    // in inches relative to current total height
    double currentTotalHeightDelta;

    boolean isCurrentSliceFar;
     
    for (int i = 0; i < SuperstructureConstants.kSimultaneousOptimizedSearchIterations; i++) {
      currentElHeightDelta = calcElevatorDistance(currentSearchAngle);
      currentTotalHeightDelta = currentElHeightDelta + 
        kArmL * Math.sin(NerdyMath.degreesToRadians(currentSearchAngle));
      isCurrentSliceFar = !(Math.abs(currentTotalHeightDelta) > Math.abs(m_desiredHeightDelta));
      if (isCurrentSliceFar) {
        currentSearchAngle += Math.pow(0.5, i+2) * (kArmMaxAngle - m_thetaInitial) * m_direction;
      } else {
        currentSearchAngle -= Math.pow(0.5, i+2) * (kArmMaxAngle - m_thetaInitial) * m_direction;
      }
    }

    // in absolute degrees
    m_armGoal = m_thetaInitial + currentSearchAngle;
    double armGoalHeight = kArmL * Math.sin(NerdyMath.degreesToRadians(
      NerdyMath.boundBetween(m_armGoal, 
      ArmConstants.kArmMinAngle, ArmConstants.kArmMaxAngle)));
    m_elevatorGoal = NerdyMath.boundBetween(m_desiredHeight - armGoalHeight, 
      ElevatorConstants.kMinElevatorHeight, ElevatorConstants.kMaxElevatorHeight);
    if (!NerdyMath.isApproximately(m_elevatorGoal + armGoalHeight, m_desiredHeight, 0.001)) {
      m_armGoal = NerdyMath.boundBetween(
        NerdyMath.radiansToDegrees(
          Math.asin((m_desiredHeight - m_elevatorGoal) / kArmL)), 
        kArmMinAngle, kArmMaxAngle);
    }
    m_elevatorGoal = NerdyMath.boundBetween(m_desiredHeight - armGoalHeight, 
      ElevatorConstants.kMinElevatorHeight, ElevatorConstants.kMaxElevatorHeight);

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    double initStartTime = Timer.getFPGATimestamp();
    m_direction = Math.signum(m_desiredHeight - (Robot.elevator.getHeight() + 
      Arm.getArmHeight()));
    m_thetaInitial = Robot.arm.getAngle();
    m_desiredHeightDelta = m_desiredHeight - Robot.elevator.getHeight() - Arm.getArmHeight();
    if (m_direction == 0) {
      m_armGoal = Robot.arm.getAngle();
      m_elevatorGoal = m_desiredHeight - Arm.getArmHeight();
    } else {
      this.searchAndSetGoals();
    }
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
    Robot.elevator.setHeightMotionMagic(m_elevatorGoal);
    Robot.arm.setAngleMotionMagic(m_armGoal);
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

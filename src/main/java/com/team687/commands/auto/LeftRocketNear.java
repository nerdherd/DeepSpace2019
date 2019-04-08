/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.auto;

import com.nerdherd.lib.drivetrain.auto.DriveFalconTrajectory;
import com.nerdherd.lib.drivetrain.auto.DriveTime;
import com.nerdherd.lib.drivetrain.auto.TurnToAngle;
import com.nerdherd.lib.misc.WaitTime;
import com.nerdherd.lib.motor.commands.SetDualMotorPower;
import com.nerdherd.lib.pneumatics.commands.RetractPiston;
import com.team687.Robot;
import com.team687.commands.superstructure.SetHatchMode;
import com.team687.commands.superstructure.SimultaneousMovement;
import com.team687.commands.superstructure.Stow;
import com.team687.commands.vision.AutoLiveTargetTrack;
import com.team687.constants.AutoConstants;
import com.team687.constants.DriveConstants;
import com.team687.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LeftRocketNear extends CommandGroup {
  /**
   * Add your docs here.
   */
  public LeftRocketNear() {
  addParallel(new SimultaneousMovement(SuperstructureConstants.kLowElHeight, SuperstructureConstants.kHatchModeArmAngle));
    addSequential(new DriveFalconTrajectory(Robot.drive, AutoConstants.leftRocketNearPathOne, 3, true, 0.35, 0));
    addSequential(new AutoLiveTargetTrack(0.2, Robot.drive.feetToTicks(2.5,  DriveConstants.kLeftTicksPerFoot), 0.00784, 0, .06));
    addParallel(new SetHatchMode(true));
    addParallel(new SetDualMotorPower(Robot.intake, 0.75, -0.75));
    addSequential(new DriveTime(Robot.drive, -0.2, 1));
    addParallel(new SetDualMotorPower(Robot.intake, 0, 0));
    addParallel(new Stow());
    addSequential(new TurnToAngle(Robot.drive, 180, 1, 1.5, 0.0027, 0));
    addParallel(new SimultaneousMovement(SuperstructureConstants.kLowElHeight,                   SuperstructureConstants.kHatchModeArmAngle));
    addParallel(new RetractPiston(Robot.claw));
    addSequential(new DriveFalconTrajectory(Robot.drive, AutoConstants.leftRocketNearPathTwo, 3, true, 0.23, 0));
    addSequential(new AutoLiveTargetTrack(0.2, Robot.drive.feetToTicks(3.75, DriveConstants.kLeftTicksPerFoot), 0.01254, 0, 0.5));
    addParallel(new SetDualMotorPower(Robot.intake, -0.75, 0.75));
    addSequential(new WaitTime(0.75));
    addSequential(new Stow());
    addSequential(new DriveTime(Robot.drive, -0.2, 0.05));


    // addParallel(new Stow());
    // addParallel(new DriveAtHeading(0, 0));
    // addParallel(new SetHatchMode(true));
    // addParallel(new TeleopSimultaneous(67));
    // addSequential(new SetDualMotorPower(Robot.intake, 0.25, 0.25));
    // addParallel(new Stow());
    // addSequential(new DriveFalconTrajectory(Robot.drive, AutoConstants.leftRocketNearPathTwo, 3, false, 0.01, 0));
  }
}

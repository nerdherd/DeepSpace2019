/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.auto;

import com.nerdherd.lib.drivetrain.auto.DriveFalconTrajectory;
import com.nerdherd.lib.drivetrain.auto.DriveTime;
import com.nerdherd.lib.motor.commands.SetDualMotorPower;
import com.team687.Robot;
import com.team687.commands.superstructure.SetHatchMode;
import com.team687.commands.superstructure.SimultaneousMovement;
import com.team687.commands.vision.AutoLiveTargetTrack;
import com.team687.constants.AutoConstants;
import com.team687.constants.DriveConstants;
import com.team687.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightRocketNear extends CommandGroup {
  /**
   * Add your docs here.
   */
  public RightRocketNear() {
    addParallel(new SimultaneousMovement(SuperstructureConstants.kLowElHeight, SuperstructureConstants.kHatchModeArmAngle));
    addSequential(new DriveFalconTrajectory(Robot.drive, AutoConstants.rightRocketNearPathOne, 3, true, 0.3, 0));
    addSequential(new AutoLiveTargetTrack(0.2, Robot.drive.feetToTicks(2.25, DriveConstants.kLeftTicksPerFoot), 0.00934, 0, 1.75));
    // addParallel(new DriveAtHeading(0, 0));
    // addParallel(new SetHatchMode(true));
    // addParallel(new TeleopSimultaneous(67));
    addParallel(new SetHatchMode(true));
    addParallel(new SetDualMotorPower(Robot.intake, 0.75, -0.75));
    addSequential(new DriveTime(Robot.drive, -0.2, 0.5));
    addParallel(new SetDualMotorPower(Robot.intake, 0, 0));
    // addParallel(new Stow());
    // addSequential(new DriveFalconTrajectory(Robot.drive, AutoConstants.rightRocketNearPathTwo, 3, false, 0.01, 0));
  }
}

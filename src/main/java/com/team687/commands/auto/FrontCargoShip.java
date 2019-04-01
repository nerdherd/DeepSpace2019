/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.auto;

import com.team687.Robot;
import com.team687.commands.superstructure.SimultaneousMovement;
import com.team687.commands.vision.AutoLiveTargetTrack;
import com.team687.constants.AutoConstants;
import com.team687.constants.DriveConstants;
import com.team687.constants.SuperstructureConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FrontCargoShip extends CommandGroup {
  /**
   * Add your docs here.
   */
  public FrontCargoShip() {
    // addParallel(new SetHatchMode(true));
    // addParallel(new TeleopSimultaneous(SuperstructureConstants.kLowElHeight));
    addParallel(new SimultaneousMovement(SuperstructureConstants.kLowElHeight, SuperstructureConstants.kHatchModeArmAngle));
    addSequential(new AutoLiveTargetTrack(0.3, Robot.drive.feetToTicks(5 , DriveConstants.kLeftTicksPerFoot), 0.00834, 0));
    // outtake hatch

    // addParallel(new IntakeOrOuttakeRollers(-0.45, 0.45));
    // addSequential(new DriveTime(Robot.drive, -0.2, 3));
    // addSequential(new DriveTime(Robot.drive, 0.2, 3));
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.auto;

import com.team687.Robot;
import com.team687.commands.superstructure.IntakeOrOuttakeRollers;
import com.team687.commands.superstructure.SetHatchMode;
import com.team687.commands.superstructure.TeleopSimultaneous;
import com.team687.commands.vision.AutoLiveTargetTrack;
import com.nerdherd.lib.drivetrain.auto.DriveTime;
import com.nerdherd.lib.misc.WaitTime;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FrontCargoShip extends CommandGroup {
  /**
   * Add your docs here.
   */
  public FrontCargoShip() {
    addParallel(new SetHatchMode(true));
    addParallel(new TeleopSimultaneous(11));
    addSequential(new AutoLiveTargetTrack(0.0139, 5, 0.2));
    // outtake hatch
    addSequential(new IntakeOrOuttakeRollers(-0.45, 0.45));
    addSequential(new WaitTime(2));
    addSequential(new DriveTime(Robot.drive, -0.2, 2));
  }
}

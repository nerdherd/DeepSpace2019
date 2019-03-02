/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team687.commands.vision.LiveTargetTrack;
import com.team687.commands.vision.TurnToAngle;
import com.team687.commands.vision.DriveAtHeading;

public class TurnAndApproach extends CommandGroup {
  /**
   * Add your docs here.
   */

  public TurnAndApproach() {
    addSequential(new TurnToAngle(0.0139));
    addSequential(new DriveAtHeading(.0139, .0000354));
  }
}

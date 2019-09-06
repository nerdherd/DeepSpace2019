/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.climber;

import com.nerdherd.lib.motor.commands.MotorVoltageRamping;
import com.team687.subsystems.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

public class SuckAndLift extends CommandGroup {
  /**
   * Add your docs here.
   */
  public SuckAndLift() {
    addSequential(new WaitForSuck());
    addSequential(new ClimbLift());
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }

  @Override
  protected void end() {
    Scheduler.getInstance().add(new MotorVoltageRamping(Climber.getInstance(), 0.75) {
      @Override
      protected boolean isFinished() {
        return super.isFinished() || m_motor.getVoltage() >= 0;
      }
    });
  }

  @Override
  protected void interrupted() {
    end();
  }

}

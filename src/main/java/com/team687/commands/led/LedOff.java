// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package com.team687.commands.led;

// import edu.wpi.first.wpilibj.command.Command;

// import com.team687.Robot;

// import edu.wpi.first.wpilibj.Timer;

// public class LedOff extends Command {
//   public LedOff() {
//     requires(Robot.led);
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     Timer.delay(0.05);
//     Robot.led.setColorRGB(0, 0, 0);
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return false;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//   }
// }

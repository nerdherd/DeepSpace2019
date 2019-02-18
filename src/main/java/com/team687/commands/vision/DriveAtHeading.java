// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package com.team687.commands.vision;

// import com.team687.Robot;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// public class DriveAtHeading extends Command {

//   private double m_straightPower;
//   private double m_heading, m_distance;
//   private boolean m_isHighGear;
//   private double kP = 0.0139;


//   /**
//    * @param straightPower (determines direction and magnitude)
//    * @param heading
//    * @param distance      (absolute value)
//    * @param isHighGear
//    * @param kRotP
//    */
//   public DriveAtHeading(double straightPower, double heading, double distance, boolean isHighGear, double kRotP) {
//     m_straightPower = straightPower;
//     m_heading = heading;
//     m_distance = distance;
    

//     requires(Robot.drive);
//     requires(Robot.jevois);
//   }

//   @Override
//   protected void initialize() {
//     SmartDashboard.putString("Current Command", "DriveAtHeading");
//     Robot.jevois.enableStream();

//   }

//   @Override
//   protected void execute() {
//     double getAngularTargetError = Robot.jevois.getOffsetAngleToTurn();
//     double rotPower = kP * getAngularTargetError;

//     // double robotAngle = (360 - Robot.drive.getCurrentYaw()) % 360;
//     // double rotError = -m_heading - robotAngle;
//     // rotError = (rotError > 180) ? rotError - 360 : rotError;
//     // rotError = (rotError < -180) ? rotError + 360 : rotError;

//     Robot.drive.setPowerFeedforward(rotPower + m_straightPower, rotPower - m_straightPower);
//   }

//   @Override
//   protected boolean isFinished() {
//     return Math.abs(Robot.drive.getDrivetrainPosition()) >= m_distance;
//   }

//   @Override
//   protected void end() {
//     Robot.drive.stopDrive();
//   }

//   @Override
//   protected void interrupted() {
//     end();
//   }

// }

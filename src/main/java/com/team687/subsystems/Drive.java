/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.nerdherd.lib.drivetrain.shifting.ShiftingDrivetrain;
import com.nerdherd.lib.drivetrain.teleop.ArcadeDrive;
import com.nerdherd.lib.pneumatics.Piston;
import com.team687.Robot;
import com.team687.RobotMap;


/**
 * Add your docs here.
 */
public class Drive extends ShiftingDrivetrain {


  public Drive() {
    super(RobotMap.kLeftMasterTalonID, RobotMap.kRightMasterTalonID,
     new VictorSPX[] {
       new VictorSPX(RobotMap.kRightSlaveVictor1ID),
       new VictorSPX(RobotMap.kLeftSlaveVictor2ID)
     },
     new VictorSPX[] {
       new VictorSPX(RobotMap.kRightSlaveVictor1ID),
       new VictorSPX(RobotMap.kRightSlaveVictor2ID)
     },
     true, true,
     new Piston(RobotMap.kDrivetrainShifter1ID, RobotMap.kDrivetrainShifter2ID));
    
     super.configAutoChooser(Robot.chooser);
     super.configMaxVelocity(24000);
     super.configSensorPhase(false, true);
     
     super.configTicksPerFoot(17000, 17000);
     super.configDate("2019_2_09_");
     // floor
     super.configLeftPIDF(0.0, 0, 0, 0.025089075);
     super.configRightPIDF(0.0, 0, 0, 0.025259575);
     super.configStaticFeedforward(0.6737, 0.6505);

  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive(Robot.drive, Robot.oi));
  }

  @Override
  public void periodic() {
    super.reportToSmartDashboard();
    super.calcXY();
  }

  @Override
  public void startLog() {
    super.startLog();
  }

  @Override
  public void stopLog() {
    super.stopLog();
  }

  @Override
  public void logToCSV() {
    super.logToCSV();
  }
}

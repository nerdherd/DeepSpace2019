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
import com.nerdherd.lib.motor.NerdyTalon;
import com.nerdherd.lib.pneumatics.Piston;
import com.team687.Robot;
import com.team687.RobotMap;
import com.team687.constants.DriveConstants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class Drive extends ShiftingDrivetrain {


  public Drive() {
    super(RobotMap.kLeftMasterTalonID, RobotMap.kRightMasterTalonID,
     new NerdyTalon[] {
       new NerdyTalon(RobotMap.kLeftSlaveTalon1ID),
       new NerdyTalon(RobotMap.kLeftSlaveTalon2ID)
     },
     new NerdyTalon[] {
       new NerdyTalon(RobotMap.kRightSlaveTalon1ID),
       new NerdyTalon(RobotMap.kRightSlaveTalon2ID)
     },
     true, false,
     new Piston(RobotMap.kDrivetrainShifter1ID, RobotMap.kDrivetrainShifter2ID));
    
     super.configAutoChooser(Robot.chooser);
     super.configMaxVelocity(DriveConstants.kMaxVelocity);
     super.configSensorPhase(false, false);
     
     super.configTicksPerFoot(DriveConstants.kLeftTicksPerFoot, DriveConstants.kRightTicksPerFoot);
     super.configDate("AZN_");
     // floor
     super.configLeftPIDF(0.0, 0, 0, DriveConstants.kLeftF);
     super.configRightPIDF(0.0, 0, 0, DriveConstants.kRightF);
     super.configStaticFeedforward(DriveConstants.kLeftStatic, DriveConstants.kRightStatic);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ArcadeDrive(Robot.drive, Robot.oi));
  }

  @Override
  public void periodic() {
    reportToSmartDashboard();
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

  @Override
  public void reportToSmartDashboard() {
    SmartDashboard.putNumber("Left Master Voltage", getLeftOutputVoltage());
		SmartDashboard.putNumber("Right Master Voltage", getRightOutputVoltage());
		SmartDashboard.putNumber("Left Master Position", getLeftMasterPosition());
		SmartDashboard.putNumber("Right Master Position", getRightMasterPosition());
    SmartDashboard.putNumber("Yaw", getRawYaw());
    SmartDashboard.putNumber("Pitch", getPitch());
    SmartDashboard.putNumber("Roll", getRoll());
  }
  
}


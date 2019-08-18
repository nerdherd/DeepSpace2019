/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.drivetrain.experimental.ShiftingDrivetrain;
import com.nerdherd.lib.drivetrain.teleop.ArcadeDrive;
import com.nerdherd.lib.logging.NerdyBadlog;
import com.nerdherd.lib.motor.motorcontrollers.NerdySparkMax;
import com.nerdherd.lib.motor.motorcontrollers.NerdyTalon;
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
    super(new NerdyTalon(RobotMap.kLeftMasterTalonID), new NerdyTalon(RobotMap.kRightMasterTalonID),
    new CANMotorController[] {
      new NerdyTalon(RobotMap.kLeftSlaveTalon1ID),
      new NerdyTalon(RobotMap.kLeftSlaveTalon2ID)
    },
    new CANMotorController[] {
      new NerdyTalon(RobotMap.kRightSlaveTalon1ID),
      new NerdyVictorSPX(RobotMap.kRightSlaveTalon2ID)
    },

    // super(new NerdySparkMax(RobotMap.kLeftMasterSparkMaxID, MotorType.kBrushless), 
    // new NerdySparkMax(RobotMap.kRightMasterSparkMaxID, MotorType.kBrushless), 
    // new NerdySparkMax[]{new NerdySparkMax(RobotMap.kLeftSlaveSparkMaxID, MotorType.kBrushless)}, 
    // new NerdySparkMax[]{new NerdySparkMax(RobotMap.kRightSlaveSparkMaxID, MotorType.kBrushless)},
    //  true, false,
    super(
    new NerdyTalon(RobotMap.kLeftMasterTalonID), 
    new NerdyTalon(RobotMap.kRightMasterTalonID), 
    new NerdySparkMax[]{
      new NerdySparkMax(RobotMap.kLeftSlaveSparkMax1ID, MotorType.kBrushed),
      new NerdySparkMax(RobotMap.kLeftSlaveSparkMax2ID, MotorType.kBrushed)
    }, 
    new NerdySparkMax[]{
      new NerdySparkMax(RobotMap.kRightSlaveSparkMax1ID, MotorType.kBrushed),
      new NerdySparkMax(RobotMap.kRightSlaveSparkMax2ID, MotorType.kBrushed)
    },
     true, false,
     new Piston(RobotMap.kDrivetrainShifter1ID, RobotMap.kDrivetrainShifter2ID));
    
     super.configAutoChooser(Robot.chooser);
     super.configMaxVelocity(DriveConstants.kMaxVelocity);
     super.configSensorPhase(false, false);
     
     super.configTicksPerFoot(DriveConstants.kLeftTicksPerFoot, DriveConstants.kRightTicksPerFoot);
     super.configDate("04_13_19_");
     // floor
     super.configLeftPIDF(0.0, 0, 0, DriveConstants.kLeftF);
     super.configRightPIDF(0.0, 0, 0, DriveConstants.kRightF);
     super.configStaticFeedforward(DriveConstants.kLeftStatic, DriveConstants.kRightStatic);
      m_rightSlaves[0].followCANMotorController(m_rightMaster);
      m_rightSlaves[1].followCANMotorController(m_rightMaster);
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
    // SmartDashboard.putNumber("Left Master Voltage", getLeftOutputVoltage());
		// SmartDashboard.putNumber("Right Master Voltage", getRightOutputVoltage());
		// SmartDashboard.putNumber("Left Master Position", getLeftMasterPosition());
		// SmartDashboard.putNumber("Right Master Position", getRightMasterPosition());
    // SmartDashboard.putNumber("Yaw", getRawYaw());
    // SmartDashboard.putNumber("Pitch", getPitch());
    // SmartDashboard.putNumber("Roll", getRoll());
    super.reportToSmartDashboard();
    SmartDashboard.putNumber("Left Vel FPS", super.getLeftVelocityFeet());
    SmartDashboard.putNumber("Right Vel FPS", super.getRightVelocityFeet());
  }

  @Override
  public void initLoggingData() {
    NerdyBadlog.createTopic("Pitch", () -> getPitch());
  }
  
}


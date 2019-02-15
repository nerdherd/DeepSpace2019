/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.nerdherd.lib.drivetrain.singlespeed.Drivetrain;
import com.nerdherd.lib.drivetrain.teleop.ArcadeDrive;
import com.nerdherd.lib.motor.NerdyTalon;
import com.team687.Robot;
import com.team687.RobotMap;
import com.team687.constants.DriveConstants;


/**
 * Add your docs here.
 */
public class Drive extends Drivetrain {


	private static Drive m_driveInstance = null;

  private Drive() {
    super(RobotMap.kLeftMasterTalonID, RobotMap.kRightMasterTalonID,
     new NerdyTalon[] {
       new NerdyTalon(RobotMap.kRightSlaveTalon1ID)
     },
     new NerdyTalon[] {
       new NerdyTalon(RobotMap.kRightSlaveTalon1ID)
     },
     true, true);
    
     super.configAutoChooser(Robot.chooser);
     super.configMaxVelocity(DriveConstants.kMaxVelocity);
     super.configSensorPhase(false, true);
     
     super.configTicksPerFoot(DriveConstants.kLeftTicksPerFoot, DriveConstants.kRightTicksPerFoot);
     super.configDate("2019_2_15_");
     // floor
     super.configLeftPIDF(0.0, 0, 0, DriveConstants.kLeftF);
     super.configRightPIDF(0.0, 0, 0, DriveConstants.kRightF);
     super.configStaticFeedforward(DriveConstants.kLeftStatic, DriveConstants.kRightStatic);
  }

  public static Drive getInstance() {
	  if (m_driveInstance == null) {
		  m_driveInstance = new Drive();
	  }
	  return m_driveInstance;
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

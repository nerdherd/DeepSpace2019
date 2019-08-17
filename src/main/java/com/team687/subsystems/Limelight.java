/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Limelight extends Subsystem {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx;
  NetworkTableEntry ty;
  NetworkTableEntry ta;
  NetworkTableEntry pipeline;
  NetworkTableEntry ledMode;
  NetworkTableEntry camMode;
  
  public Limelight() {
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    ta = table.getEntry("ta");
    pipeline = table.getEntry("pipeline");
    camMode = table.getEntry("camMode");
    ledMode = table.getEntry("ledMode");

    pipeline.setValue(7);
    camMode.setValue(1);
  }

  public double getXOffsetFromTarget() {
    double x = tx.getDouble(0.0);
    return x;
  }

  public double getYOffsetFromTarget() {
    double y = ty.getDouble(0.0);
    return y;
  }

  public double getTargetArea() {
    double area = ta.getDouble(0.0);
    return area;
  }

  public void blink() {
    ledMode.setNumber(2);
  }

  public void setOff(){
    ledMode.setNumber(1);
  }

  public void reportToSmartDashboard() {
    // SmartDashboard.putNumber("Limelight X", getXOffsetFromTarget());
    // SmartDashboard.putNumber("Limelight Y", getYOffsetFromTarget());
    // SmartDashboard.putNumber("Limelight Area", getTargetArea());
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new BlinkLimelight());
  }
}

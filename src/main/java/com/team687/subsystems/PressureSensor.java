/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.logging.Loggable;
import com.nerdherd.lib.logging.NerdyBadlog;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PressureSensor implements Loggable {
  
  // private final AnalogInput m_line0;
  // private final AnalogInput m_line1;
  // private final AnalogInput m_line2;
  private final AnalogInput m_analogSensor;

  //private final Ultrasonic m_ultra;

  //private final DigitalInput m_echo;
  //private final DigitalOutput m_ping;

  public PressureSensor() {

    m_analogSensor = new AnalogInput(2);
    //m_echo = new DigitalInput(1);
    //m_ping = new DigitalOutput(2);
    //m_ultra = new Ultrasonic(m_ping, m_echo);
  
    //m_ultra.setAutomaticMode(true);
    //m_ultra.setEnabled(true);

    // m_line0 = new AnalogInput(0); // left
    // m_line1 = new AnalogInput(2); // mid
    // m_line2 = new AnalogInput(1); // right

    //m_ultra.setOversampleBits(2);
    //m_ultra.setAverageBits(5);
  }
  
  public double getVoltage() {
    return m_analogSensor.getVoltage();
  }
  
  public double getPressure() {
    return 3.51336 * (this.getVoltage() * this.getVoltage())+ 30.3481 * (this.getVoltage()) + 2.62798;
  }
  /*
  public double getRangeInches() {
    m_ultra.ping();
    return m_ultra.getRangeInches();
  }

  public double getRangeMM() {
    m_ultra.ping();
    return m_ultra.getRangeMM();
  }

  public double getPIDRange() {
    m_ultra.ping();
    return m_ultra.pidGet();
  } 
  */
  public void reportToSmartDashboard() {
    SmartDashboard.putNumber("Pressure", getPressure());
    //SmartDashboard.putNumber("Distance", getInches());
    //SmartDashboard.putNumber("PID Range", getPIDRange());
    //SmartDashboard.putNumber("Range Inches", getRangeInches());
    //SmartDashboard.putNumber("Range MM", getRangeMM());
    //SmartDashboard.putBoolean("Enabled", m_ultra.isEnabled());
    //SmartDashboard.putBoolean("Valid Range", m_ultra.isRangeValid());
  }

  public void initLoggingData() {
    NerdyBadlog.createTopic("Pressure Sensor", () -> getPressure());
  }
  
}

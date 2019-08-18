/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.team687.constants.LEDConstants;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class LED extends Subsystem {

  public SerialPort m_arduino;
  private String lastColor = "";
  public LED(){
    m_arduino = new SerialPort(115200, SerialPort.Port.kUSB2);
  }

  public void makeLEDBlue(){
    m_arduino.write(LEDConstants.kWesleyBlue.getBytes(), LEDConstants.kWesleyBlue.length());
    // Timer.delay(0.1);
  }

  public void makeLEDRed(){
    String red = "255 0 0";
    m_arduino.write(red.getBytes(), red.length());
    // Timer.delay(0.1);
  }

  public void makeLEDGreen(){
    String green = "0 255 0";
    m_arduino.write(green.getBytes(), green.length());
    // Timer.delay(0.1);
  }

  public void makeColor(int r, int g, int b) {
    String color = String.valueOf(r) + " " + String.valueOf(g) + " " + String.valueOf(b);
    SmartDashboard.putString("THIS IS THE COLOR YOU CHOSE", color);
    m_arduino.write(color.getBytes(), color.length());
  }
  
  public byte[] readStuff(){
    return m_arduino.read(16);
  }

  public void reportToSmartDashBoard(){
    SmartDashboard.putString("Adruino Says: ", new String(readStuff()));
  }
  
  public void setColorRGB(int r, int g, int b) {
    String color = String.valueOf(r) + " " + String.valueOf(g) + " " + String.valueOf(b);
    if (!lastColor.equals(color)) {
      m_arduino.write(color.getBytes(), color.length());
      SmartDashboard.putString("Color", color);
      lastColor = color;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

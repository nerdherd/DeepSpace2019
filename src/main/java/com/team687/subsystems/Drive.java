package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Subsystem {

	private final TalonSRX m_leftMaster, m_rightMaster, m_leftSlave1, m_rightSlave1;
	// public AHRS m_nav;

	public Drive() {
		m_rightMaster = new TalonSRX(RobotMap.kRightTalon1);
		m_rightSlave1 = new TalonSRX(RobotMap.kRightTalon2);

		m_leftMaster = new TalonSRX(RobotMap.kLeftTalon1); 
		m_leftSlave1 = new TalonSRX(RobotMap.kLeftTalon2);
	}

	public void initDefaultCommand() {
	}

	public void setPower(double leftPower, double rightPower) {
		SmartDashboard.putNumber("Right Power", rightPower);
		SmartDashboard.putNumber("Left Power ", leftPower);
		
		m_leftMaster.set(ControlMode.PercentOutput, leftPower);
		m_rightMaster.set(ControlMode.PercentOutput, rightPower);

		m_leftSlave1.set(ControlMode.PercentOutput, leftPower);
		m_rightSlave1.set(ControlMode.PercentOutput, rightPower);

	}

	public void stopDrive() {
		setPower(0.0, 0.0);
	}

	public void testDrive() {
		setPower(.25, -.25);
	}
}
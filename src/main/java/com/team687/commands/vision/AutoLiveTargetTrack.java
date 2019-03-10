/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import com.team687.Robot;
import com.team687.constants.VisionConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Add your docs here.
 */
public class AutoLiveTargetTrack extends Command{

    private double m_rotP, m_time, m_straightPower, m_startTime;

    public AutoLiveTargetTrack(double kRotP, double time, double power) {
        requires(Robot.drive);
        requires(Robot.jevois);
        m_time = time;
        m_straightPower = power;
        m_rotP = kRotP;
    }

    @Override
    protected void initialize() {
        Robot.jevois.enableStream();
        m_startTime = Timer.getFPGATimestamp();
       // SmartDashboard.putString("Current Command", "LiveTargetTrack");
    }

    @Override
    protected void execute() {
        if (Robot.jevois.getContourNum() >= 2) {
            double getAngularTargetError = -Robot.jevois.getAngleToTurn();
            double rotPower = m_rotP * getAngularTargetError;

            if(Robot.jevois.getDistance() < VisionConstants.kDetectDistance && Robot.jevois.getContourNum() > 0) {
                Robot.drive.setPowerFeedforward(m_straightPower, m_straightPower);
            }
            
            if(!(Math.abs(getAngularTargetError) < VisionConstants.kDriveRotationDeadband)){
                Robot.drive.setPowerFeedforward(rotPower + m_straightPower, -rotPower + m_straightPower);
            }
            else{
                Robot.drive.setPowerFeedforward(m_straightPower, m_straightPower);
            }       
        } else {
            Robot.drive.setPowerFeedforward(m_straightPower, m_straightPower);
        }

    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - m_startTime > m_time;
    }

    @Override
    protected void end() {
        Robot.drive.setPowerZero();
    }

    @Override
    protected void interrupted() {
        end();
    }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.vision;

import com.team687.Robot;

/**
 * Add your docs here.
 */
public class UltrasonicAutoLiveTargetTrack extends AutoLiveTargetTrack{

    private double m_ultrasonicDistance;
    public UltrasonicAutoLiveTargetTrack(double straightPower, double rotP, double rotD, double driveRotationDeadband, double ultrasonicDistance) {
        super(straightPower, 0, rotP, rotD, driveRotationDeadband);
        m_ultrasonicDistance = ultrasonicDistance;
    }

    @Override
    protected boolean isFinished() {
        return Robot.ultrasonic.getInches() < m_ultrasonicDistance;  
    }
}

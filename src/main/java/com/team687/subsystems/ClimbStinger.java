/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.logging.NerdyBadlog;
import com.nerdherd.lib.misc.NerdyMath;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.team687.Robot;

/**
 * Add your docs here.
 */
public class ClimbStinger extends SingleMotorArm {

    public ClimbStinger(int talonID, String name, boolean inversion, 
                        boolean sensorPhase) {
        super(talonID, name, inversion, sensorPhase);
    }

    public double getRelativeAngle() {
        return super.getAngle();
    }

    public double getAbsoluteAngle() {
        return super.getAngle() + Robot.drive.getPitch();
    }

    @Override
    public double getFFIfMoving() {
        if (this.getAbsoluteAngle() > 90) {
            return -this.m_gravityFF * 
                Math.cos(NerdyMath.degreesToRadians(Robot.drive.getPitch())) *
                Math.cos(NerdyMath.degreesToRadians(this.getAbsoluteAngle()));
        } else {
            return this.m_gravityFF * 
                Math.cos(NerdyMath.degreesToRadians(Robot.drive.getPitch())) *
                Math.cos(NerdyMath.degreesToRadians(this.getAbsoluteAngle()));
        }
    }
    
    @Override
    public double getFFIfNotMoving(double error) {
        return this.getFFIfMoving() + Math.signum(error) * this.m_staticFF;
    }

    @Override
    public void initLoggingData() {
        super.initLoggingData();
        NerdyBadlog.createTopic(name + "/AbsoluteAngle", () -> getAbsoluteAngle());
    }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.team687.Robot;

/**
 * Add your docs here.
 */
public class ClimbStinger extends SingleMotorArm {

    private double originalAngleOffset;

    public ClimbStinger(int talonID, String name, boolean inversion, 
                        boolean sensorPhase, double angleOffset) {
        super(talonID, name, inversion, sensorPhase);
        originalAngleOffset = angleOffset;
        super.configAngleOffset(originalAngleOffset);
        super.configFFs(0, 0);
    }

    @Override
    public void periodic() {
        updateAbsoluteAngle(Robot.drive.getPitch());
    }

    @Override
    public void configFFs(double newGravityFF, double newStaticFF) {
    }

    public double getRelativeAngle() {
        return super.getAngle();
    }

    public void updateAbsoluteAngle(double angle) {
        this.configAngleOffset(angle + originalAngleOffset);
    }

    public double getAbsoluteAngle() {
        return super.getAngle();
    }

    @Override
    public double angleToTicks(double angle) {
        return super.angleToTicks(angle);
    }

    //TODO: put this in SingleMotorTalonSRX
    public void configPIDF(double kP, double kI, double kD, double kF, int pidIndex) {
        super.motor.configPIDF(kP, kI, kD, kF, pidIndex);
    }

}

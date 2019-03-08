/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.subsystems;

import com.nerdherd.lib.misc.NerdyMath;
import com.nerdherd.lib.motor.single.mechanisms.SingleMotorArm;
import com.team687.RobotMap;
import com.team687.constants.ArmConstants;

/**
 * Add your docs here.
 */
public class Arm extends SingleMotorArm {

    private static Arm m_armInstance = new Arm();

    private Arm() {
        super(RobotMap.kArmTalonID, "Arm", 
            ArmConstants.kArmInversion, ArmConstants.kArmSensorPhase);
        super.configTalonDeadband(ArmConstants.kArmTalonDeadband);
        super.configFFs(ArmConstants.kArmGravityFF, ArmConstants.kArmStaticFrictionFF);
        super.configMotionMagic(ArmConstants.kArmMotionMagicMaxAccel, 
            ArmConstants.kArmMotionMagicCruiseVelocity);
        super.configPIDF(ArmConstants.kArmP, ArmConstants.kArmI, 
            ArmConstants.kArmD, ArmConstants.kArmF);
        super.configAngleConversion(ArmConstants.kArmAngleRatio, 
            ArmConstants.kEffectiveArmAngleOffset);
    }

    public static Arm getInstance() {
        return m_armInstance;
    }

    public static double getArmHeight() {
        return ArmConstants.kArmLength * Math.sin(NerdyMath.degreesToRadians(getInstance().getAngle()));
    }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687.commands.superstructure;

import com.team687.constants.SuperstructureConstants;
import com.team687.subsystems.Superstructure;

/**
 * Add your docs here.
 */
public class CargoShipCargo extends SimultaneousMovement {

    public CargoShipCargo() {
        super(SuperstructureConstants.kCargoIntakeElHeight, 
            SuperstructureConstants.kCargoIntakeArmAngle);
        Superstructure.getInstance().isHatchMode = false;
    }

}

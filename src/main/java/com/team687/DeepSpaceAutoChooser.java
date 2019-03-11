/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team687;

import com.nerdherd.lib.misc.AutoChooser;
import com.team687.commands.auto.FrontCargoShip;
import com.team687.commands.auto.LeftCargoShip;
import com.team687.commands.auto.LeftRocketNear;
import com.team687.commands.auto.RightCargoShip;
import com.team687.commands.auto.RightRocketNear;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class DeepSpaceAutoChooser extends AutoChooser {

    public SendableChooser<AutoMode> modeChooser;

    public DeepSpaceAutoChooser() {
        super();
        modeChooser = new SendableChooser<>();
        modeChooser.setDefaultOption("Rocket Auto", AutoMode.RocketShip);
        modeChooser.addOption("Cargo Ship Auto", AutoMode.CargoShip);
        SmartDashboard.putData("Mode Chooser", modeChooser);
        SmartDashboard.putData("Starting Position Chooser", super.startingPositionChooser);
    }

    public AutoMode getAutoMode() {
        return modeChooser.getSelected();
    }

    public CommandGroup getSelectedAuto() {

        CommandGroup auto = null;
        if (getStartingPosition() == StartingPosition.RIGHT) {
            // if (getAutoMode() == AutoMode.CargoShip) {
            //     auto = new RightCargoShip();
            // } else if (getAutoMode() == AutoMode.RocketShip) {
            auto =  new RightRocketNear();
            // }
        } else if (getStartingPosition() == StartingPosition.LEFT) {
            // if (getAutoMode() == AutoMode.CargoShip) {
            //     auto = new LeftCargoShip();
            // } else if (getAutoMode() == AutoMode.RocketShip) {
                auto =  new LeftRocketNear();
            // }
        } else if (getStartingPosition() == StartingPosition.CENTER) {
            auto = new FrontCargoShip();
        }
        return auto;
    }

    enum AutoMode{
        RocketShip, CargoShip, TeleOp;
    }
}



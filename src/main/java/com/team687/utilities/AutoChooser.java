package com.team687.utilities;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {

    SendableChooser<StartingPosition> startingPositionChooser;
    SendableChooser<Double> directionChooser;
    public AutoChooser() {
        startingPositionChooser = new SendableChooser<>();
        startingPositionChooser.addDefault("Center", StartingPosition.CENTER);
        startingPositionChooser.addObject("Left", StartingPosition.LEFT);
        startingPositionChooser.addObject("Right", StartingPosition.RIGHT);
        directionChooser = new SendableChooser<>();
        directionChooser.addDefault("Forwards", 0.0);
        directionChooser.addObject("Backwards", 180.0);
        // Add additional choosers based on what the game needs
        SmartDashboard.putData(startingPositionChooser);
        SmartDashboard.putData(directionChooser);
    }

    public double getDirection() {
        return directionChooser.getSelected();
    }

    public StartingPosition getStartingPosition() {
        return startingPositionChooser.getSelected();
    }

    public CommandGroup getSelectedAuto() {
        return new CommandGroup();
        // replace with auto selection logic
    }
}

enum StartingPosition {
    CENTER, LEFT, RIGHT;
}
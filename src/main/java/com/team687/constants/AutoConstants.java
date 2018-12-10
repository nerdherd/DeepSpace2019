package com.team687.constants;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.modifiers.TankModifier;
public class AutoConstants {

    // pathfinder constants
    public static final double dt = 0.02;
    // max speed and accel
    public static final double kAcceleration = 13;
    public static final double kCruiseVelocity = 13;
    // Jerk is set to a high number since jerk barely matters, poofs don't jerk anymore
    public static final double kJerk = 100;

    private static Config testConfig = new Config(Trajectory.FitMethod.HERMITE_CUBIC, Config.SAMPLES_HIGH, dt, kCruiseVelocity/2, kAcceleration/3, kJerk);
    private static Waypoint[] testPoints = new Waypoint[] {
        new Waypoint(0, 0, 0),
        new Waypoint(5, 5, 0)  
    };

    public static Waypoint[] testPoints2 = new Waypoint[] {
        new Waypoint(5, 5, Pathfinder.d2r(0)),
        new Waypoint(9, 0, Pathfinder.d2r(-90))
    };

    public static Waypoint[] backwardsPoints = new Waypoint[] {
        new Waypoint(0, 0, 0), 
        new Waypoint(-5, 5, 0)
    };
    
    public static Trajectory testTraj = Pathfinder.generate(testPoints, testConfig);
    public static Trajectory testTraj2 = Pathfinder.generate(testPoints2, testConfig);
    public static Trajectory backwardsTrajectory = Pathfinder.generate(backwardsPoints, testConfig);
}
package com.team687.constants;

import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Config;
import jaci.pathfinder.Waypoint;

import com.nerdherd.lib.drivetrain.trajectory.falconlib.Pose2D;
import com.nerdherd.lib.drivetrain.trajectory.falconlib.TrajectoryGen;
import com.nerdherd.lib.drivetrain.trajectory.falconlib.TrajectoryPoint;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.modifiers.TankModifier;
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutoConstants {

    // pathfinder constants
    public static final double dt = 0.02;
    // max speed and accel
    public static final double kAcceleration = 13;
    public static final double kCruiseVelocity = 13;
    // Jerk is set to a high number since jerk barely matters, poofs don't jerk anymore
    public static final double kJerk = 100;
    public static final double kCentripetalAcceleration = 20;

    private static double kRobotLength = 0;
    private static double kRobotWidth = 0;
    private static double kTapeLength = 18;

    private static double kRightRobotOriginX = 5.5;
    private static double kRightRobotOriginY = 9.5;
    private static double kRightFarRocketTapeX = 0; 
    private static double kRightFarRocketTapeY = 0;



    private static TrajectoryGen gen = new TrajectoryGen();
    // public static ArrayList<TrajectoryPo Arrays.asList(new Pose2D(0, 0, 0).pose, new Pose2D(0, 0, 0).pose);
    public static ArrayList<TrajectoryPoint> RightRocketPath1 = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kRightRobotOriginX, kRightRobotOriginY, 0).pose, 
        new Pose2D(kRightFarRocketTapeX, kRightFarRocketTapeY, -150).pose),
     kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    //  public static ArrayList<TrajectoryPoint> RightRocketPath2 = gen.generateTrajectory(Arrays.asList(new Pose2D), 
    //  centripetalAccel, startVelocity, endVelocity, maxVelocity, accel, reversed)
    
}
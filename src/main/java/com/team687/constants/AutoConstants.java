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

/**
 * refer to https://docs.google.com/presentation/d/191Uc-_O5Gic7hE403uKOAyL_0ZaA-xKe5i-_uURh4Z0/edit?usp=sharing
 * for auto paths
 */
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

    // Right Rocket Path One
    private static double kRightRobotOriginX = 5.5;
    private static double kRightRobotOriginY = 9.5;
    private static double kRightRocketPathOnePointTwoX = 21; 
    private static double kRightRocketPathOnePointTwoY = 2.25;

    // Right Rocket Path Two
    private static double kRightRocketPathTwoPointOneX = 0;
    private static double kRightRocketPathTwoPointOneY = 0;
    private static double kRightRocketPathTwoPointTwoX = 0;
    private static double kRightRocketPathTwoPointTwoY = 0;
    private static double kRightRocketPathTwoPointThreeX = 0;
    private static double kRightRocketPathTwoPointThreeY = 0;

    // public static ArrayList<TrajectoryPoint> 

    // Right Rocket Path Three
    private static double kRightRocketPathThreePointOneX = 0;
    private static double kRightRocketPathThreePointOneY = 0;
    private static double kRightRocketPathThreePointTwoX = 0;
    private static double kRightRocketPathThreePointTwoY = 0;
    
    // Right Rocket Path Four
    private static double kRightRocketPathFourPointOneX = 0;
    private static double kRightRocketPathFourPointOneY = 0;
    private static double kRightRocketPathFourPointTwoX = 0;
    private static double kRightRocketPathFourPointTwoY = 0;

    // Right Rocket Path Five
    private static double kRightRocketPathFivePointOneX = 0;
    private static double kRightRocketPathFivePointOneY = 0;
    private static double kRightRocketPathFivePointTwoX = 0;
    private static double kRightRocketPathFivePointTwoY = 0;

    // Right Rocket Path Six
    private static double kRightRocketPathSixPointOneX = 0;
    private static double kRightRocketPathSixPointOneY = 0;
    private static double kRightRocketPathSixPointTwoX = 0;
    private static double kRightRocketPathSixPointTwoY = 0;

    // Right Rocket Path Seven
    private static double kRightRocketPathSevenPointOneX = 0;
    private static double kRightRocketPathSevenPointOneY = 0;
    private static double kRightRocketPathSevenPointTwoX = 0;
    private static double kRightRocketPathSevenPointTwoY = 0;



    // Left Rocket Path One
    private static double kLeftRobotOriginX = 5.5;
    private static double kLeftRobotOriginY = 9.5;
    private static double kLeftRocketPathOnePointTwoX = 21; 
    private static double kLeftRocketPathOnePointTwoY = 2.25;

    // Left Rocket Path Two
    private static double kLeftRocketPathTwoPointOneX = 0;
    private static double kLeftRocketPathTwoPointOneY = 0;
    private static double kLeftRocketPathTwoPointTwoX = 0;
    private static double kLeftRocketPathTwoPointTwoY = 0;
    private static double kLeftRocketPathTwoPointThreeX = 0;
    private static double kLeftRocketPathTwoPointThreeY = 0;

    // Left Rocket Path Three
    private static double kLeftRocketPathThreePointOneX = 0;
    private static double kLeftRocketPathThreePointOneY = 0;
    private static double kLeftRocketPathThreePointTwoX = 0;
    private static double kLeftRocketPathThreePointTwoY = 0;
    
    // Left Rocket Path Four
    private static double kLeftRocketPathFourPointOneX = 0;
    private static double kLeftRocketPathFourPointOneY = 0;
    private static double kLeftRocketPathFourPointTwoX = 0;
    private static double kLeftRocketPathFourPointTwoY = 0;

    // Left Rocket Path Five
    private static double kLeftRocketPathFivePointOneX = 0;
    private static double kLeftRocketPathFivePointOneY = 0;
    private static double kLeftRocketPathFivePointTwoX = 0;
    private static double kLeftRocketPathFivePointTwoY = 0;

    // Left Rocket Path Six
    private static double kLeftRocketPathSixPointOneX = 0;
    private static double kLeftRocketPathSixPointOneY = 0;
    private static double kLeftRocketPathSixPointTwoX = 0;
    private static double kLeftRocketPathSixPointTwoY = 0;

    // Left Rocket Path Seven
    private static double kLeftRocketPathSevenPointOneX = 0;
    private static double kLeftRocketPathSevenPointOneY = 0;
    private static double kLeftRocketPathSevenPointTwoX = 0;
    private static double kLeftRocketPathSevenPointTwoY = 0;

    private static TrajectoryGen gen = new TrajectoryGen();
    public static ArrayList<TrajectoryPoint> RightRocketPath1 = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kRightRobotOriginX, kRightRobotOriginY, 0).pose, 
        new Pose2D(kRightRocketPathOnePointTwoX, kRightRocketPathOnePointTwoY, -150).pose),
     kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    //  public static ArrayList<TrajectoryPoint> RightRocketPath2 = gen.generateTrajectory(Arrays.asList(new Pose2D), 
    //  centripetalAccel, startVelocity, endVelocity, maxVelocity, accel, reversed)
    
}
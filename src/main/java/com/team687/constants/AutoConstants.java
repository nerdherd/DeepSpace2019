package com.team687.constants;

import java.util.ArrayList;
import java.util.Arrays;

import com.nerdherd.lib.drivetrain.trajectory.falconlib.Pose2D;
import com.nerdherd.lib.drivetrain.trajectory.falconlib.TrajectoryGen;
import com.nerdherd.lib.drivetrain.trajectory.falconlib.TrajectoryPoint;

/**
 * refer to https://docs.google.com/presentation/d/191Uc-_O5Gic7hE403uKOAyL_0ZaA-xKe5i-_uURh4Z0/edit?usp=sharing
 * for auto paths
 */
public class AutoConstants {

    private static TrajectoryGen gen = new TrajectoryGen();

    // pathfinder constants
    public static final double dt = 0.02;
    // max speed and accel
    public static final double kAcceleration = 15;
    public static final double kCruiseVelocity = 12;
    // Jerk is set to a high number since jerk barely matters, poofs don't jerk anymore
    public static final double kJerk = 100;
    public static final double kCentripetalAcceleration = 5;

    public static double kRobotLength = 16.5;
    private static double kRobotWidth = 0;
    private static double kTapeLength = 18;


    private static double kRightRobotOriginX = 5.75;
    private static double kRightRobotOriginY = 9.5;

    private static double kRightCargoPathOnePointTwoX = 22;
    private static double kRightCargoPathOnePointTwoY = 7.33;

    public static ArrayList<TrajectoryPoint> rightCargoShipPathOne = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kRightRobotOriginX, kRightRobotOriginY, 0).pose,
        new Pose2D(kRightCargoPathOnePointTwoX, kRightCargoPathOnePointTwoY, 90).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);


    private static double kRightRocketNearPathOnePointTwoX = 13.25;
    private static double kRightRocketNearPathOnePointTwoY = 3.5;

    public static ArrayList<TrajectoryPoint> rightRocketNearPathOne = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kRightRobotOriginX, kRightRobotOriginY, 0).pose,
        new Pose2D(kRightRocketNearPathOnePointTwoX, kRightRocketNearPathOnePointTwoY, -30).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);


    private static double kRightRocketNearPathTwoPointOneX = 17;
    private static double kRightRocketNearPathTwoPointOneY = 2.167;
    private static double kRightRocketNearPathTwoPointTwoX = 7;
    private static double kRightRocketNearPathTwoPointTwoY = 2.167;

    public static ArrayList<TrajectoryPoint> rightRocketNearPathTwo = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kRightRocketNearPathTwoPointOneX, kRightRocketNearPathTwoPointOneY, 150).pose,
        new Pose2D(kRightRocketNearPathTwoPointTwoX, kRightRocketNearPathTwoPointTwoY, 180).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

// Left paths start here
    private static double kLeftRobotOriginX = 5.75;
    private static double kLeftRobotOriginY = 17.417;

    private static double kLeftCargoPathOnePointTwoX = 22;
    private static double kLeftCargoPathOnePointTwoY = 19.5;


    public static ArrayList<TrajectoryPoint> leftCargoShipPathOne = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kLeftRobotOriginX, kLeftRobotOriginY, 0).pose,
        new Pose2D(kLeftCargoPathOnePointTwoX, kLeftCargoPathOnePointTwoY, -90).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    private static double kLeftRocketNearPathOnePointTwoX = 13.583;
    private static double kLeftRocketNearPathOnePointTwoY = 23.83;

    public static ArrayList<TrajectoryPoint> leftRocketNearPathOne = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kLeftRobotOriginX, kLeftRobotOriginY, 0).pose,
        new Pose2D(kLeftRocketNearPathOnePointTwoX, kLeftRocketNearPathOnePointTwoY, 30).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);


    private static double kLeftRocketNearPathTwoPointOneX = 17.33;
    private static double kLeftRocketNearPathTwoPointOneY = 24.79;
    private static double kLeftRocketNearPathTwoPointTwoX = 7.1;
    private static double kLeftRocketNearPathTwoPointTwoY = 24.79;

    public static ArrayList<TrajectoryPoint> leftRocketNearPathTwo = gen.generateTrajectory(
        Arrays.asList(new Pose2D(kLeftRocketNearPathTwoPointOneX, kLeftRocketNearPathTwoPointOneY, 180).pose,
        new Pose2D(kLeftRocketNearPathTwoPointTwoX, kLeftRocketNearPathTwoPointTwoY, 180).pose), 
        kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

// Old paths from when we thought the robot would be done soon
    // // Right Rocket Path One
    // private static double kRightRobotOriginX = 6.67; //5.5
    // private static double kRightRobotOriginY = 9.41; //9.5
    // private static double kRightRocketPathOnePointTwoX = 22.75;  
    // private static double kRightRocketPathOnePointTwoY = 3.5; //2.25
    // private static double kRightRocketPathOnePointThreeX = 22.75;
    // private static double kRightRocketPathOnePointThreeY = 2;

    // public static ArrayList<TrajectoryPoint> RightRocketPath1 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRobotOriginX, kRightRobotOriginY, 0).pose, 
    //     new Pose2D(kRightRocketPathOnePointTwoX, kRightRocketPathOnePointTwoY, -45).pose,
    //     new Pose2D(kRightRocketPathOnePointThreeX, kRightRocketPathOnePointThreeY, -150).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false); 

    // // Right Rocket Path Two
    // private static double kRightRocketPathTwoPointOneX = 22.75;
    // private static double kRightRocketPathTwoPointOneY = 2.0;
    // private static double kRightRocketPathTwoPointTwoX = 22.75;
    // private static double kRightRocketPathTwoPointTwoY = 5.5;
    // private static double kRightRocketPathTwoPointThreeX = 4;
    // private static double kRightRocketPathTwoPointThreeY = 4;

    // public static ArrayList<TrajectoryPoint> RightRocketPath2 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRocketPathTwoPointOneX, kRightRocketPathTwoPointOneY, 30).pose, 
    //     new Pose2D(kRightRocketPathTwoPointTwoX, kRightRocketPathTwoPointTwoY, -180).pose,
    //     new Pose2D(kRightRocketPathTwoPointThreeX, kRightRocketPathTwoPointThreeY, -180).pose),
    //  kCentripetalAcceleration , 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Right Rocket Path Three
    // private static double kRightRocketPathThreePointOneX = 0;
    // private static double kRightRocketPathThreePointOneY = 0;
    // private static double kRightRocketPathThreePointTwoX = 0;
    // private static double kRightRocketPathThreePointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> RightRocketPath3 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRocketPathThreePointOneX, kRightRocketPathThreePointOneY, 0).pose, 
    //     new Pose2D(kRightRocketPathThreePointTwoX, kRightRocketPathThreePointTwoY, -30).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);
    
    // // Right Rocket Path Four
    // private static double kRightRocketPathFourPointOneX = 0;
    // private static double kRightRocketPathFourPointOneY = 0;
    // private static double kRightRocketPathFourPointTwoX = 0;
    // private static double kRightRocketPathFourPointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> RightRocketPath4 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRocketPathFourPointOneX, kRightRocketPathFourPointOneY, 150).pose, 
    //     new Pose2D(kRightRocketPathFourPointTwoX, kRightRocketPathFourPointTwoY, 135).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Right Rocket Path Five
    // private static double kRightRocketPathFivePointOneX = 0;
    // private static double kRightRocketPathFivePointOneY = 0;
    // private static double kRightRocketPathFivePointTwoX = 0;
    // private static double kRightRocketPathFivePointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> RightRocketPath5 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRocketPathFivePointOneX, kRightRocketPathFivePointOneY, -45).pose, 
    //     new Pose2D(kRightRocketPathFivePointTwoX, kRightRocketPathFivePointTwoY, -90).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Right Rocket Path Six
    // private static double kRightRocketPathSixPointOneX = 0;
    // private static double kRightRocketPathSixPointOneY = 0;
    // private static double kRightRocketPathSixPointTwoX = 0;
    // private static double kRightRocketPathSixPointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> RightRocketPath6 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRocketPathSixPointOneX, kRightRocketPathSixPointOneY, 90).pose, 
    //     new Pose2D(kRightRocketPathSixPointTwoX, kRightRocketPathSixPointTwoY, 120).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Right Rocket Path Seven
    // private static double kRightRocketPathSevenPointOneX = 0;
    // private static double kRightRocketPathSevenPointOneY = 0;
    // private static double kRightRocketPathSevenPointTwoX = 0;
    // private static double kRightRocketPathSevenPointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> RightRocketPath7 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kRightRocketPathSevenPointOneX, kRightRocketPathSevenPointOneY, -60).pose, 
    //     new Pose2D(kRightRocketPathSevenPointTwoX, kRightRocketPathSevenPointTwoY, -90).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // //Left Rocket Paths
      
    // // Left Rocket Path One
    // private static double kLeftRobotOriginX = 5.417;
    // private static double kLeftRobotOriginY = 18;
    // private static double kLeftRocketPathOnePointTwoX = 22; 
    // private static double kLeftRocketPathOnePointTwoY = 25.25;
    // private static double kLeftRocketPathOnePointThreeX = 0;
    // private static double kLeftRocketPathOnePointThreeY = 0;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath1 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRobotOriginX, kLeftRobotOriginY, 0).pose, 
    //     new Pose2D(kLeftRocketPathOnePointTwoX, kLeftRocketPathOnePointTwoY, 45).pose, 
    //     new Pose2D(kLeftRocketPathOnePointThreeX, kLeftRocketPathOnePointThreeY, 150).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Left Rocket Path Two
    // private static double kLeftRocketPathTwoPointOneX = 22;
    // private static double kLeftRocketPathTwoPointOneY = 25.25;
    // private static double kLeftRocketPathTwoPointTwoX = 19.167;
    // private static double kLeftRocketPathTwoPointTwoY = 21.5;
    // private static double kLeftRocketPathTwoPointThreeX = 1.5;
    // private static double kLeftRocketPathTwoPointThreeY = 24.71;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath2 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRocketPathTwoPointOneX, kLeftRocketPathTwoPointOneY, -30).pose, 
    //     new Pose2D(kLeftRocketPathTwoPointTwoX, kLeftRocketPathTwoPointTwoY, -180).pose,
    //     new Pose2D(kLeftRocketPathTwoPointThreeX, kLeftRocketPathTwoPointThreeY, -180).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Left Rocket Path Three
    // private static double kLeftRocketPathThreePointOneX = 0;
    // private static double kLeftRocketPathThreePointOneY = 0;
    // private static double kLeftRocketPathThreePointTwoX = 0;
    // private static double kLeftRocketPathThreePointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath3 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRocketPathThreePointOneX, kLeftRocketPathThreePointOneY, 0).pose, 
    //     new Pose2D(kLeftRocketPathThreePointTwoX, kLeftRocketPathThreePointTwoY, 30).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);
    
    // // Left Rocket Path Four
    // private static double kLeftRocketPathFourPointOneX = 0;
    // private static double kLeftRocketPathFourPointOneY = 0;
    // private static double kLeftRocketPathFourPointTwoX = 0;
    // private static double kLeftRocketPathFourPointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath4 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRocketPathFourPointOneX, kLeftRocketPathFourPointOneY, -150).pose, 
    //     new Pose2D(kLeftRocketPathFourPointTwoX, kLeftRocketPathFourPointTwoY, -135).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Left Rocket Path Five
    // private static double kLeftRocketPathFivePointOneX = 0;
    // private static double kLeftRocketPathFivePointOneY = 0;
    // private static double kLeftRocketPathFivePointTwoX = 0;
    // private static double kLeftRocketPathFivePointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath5 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRocketPathFivePointOneX, kLeftRocketPathFivePointOneY, 45).pose, 
    //     new Pose2D(kLeftRocketPathFivePointTwoX, kLeftRocketPathFivePointTwoY, 90).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Left Rocket Path Six
    // private static double kLeftRocketPathSixPointOneX = 0;
    // private static double kLeftRocketPathSixPointOneY = 0;
    // private static double kLeftRocketPathSixPointTwoX = 0;
    // private static double kLeftRocketPathSixPointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath6 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRocketPathSixPointOneX, kLeftRocketPathSixPointOneY, 90).pose, 
    //     new Pose2D(kLeftRocketPathSixPointTwoX, kLeftRocketPathSixPointTwoY, 120).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // // Left Rocket Path Seven
    // private static double kLeftRocketPathSevenPointOneX = 0;
    // private static double kLeftRocketPathSevenPointOneY = 0;
    // private static double kLeftRocketPathSevenPointTwoX = 0;
    // private static double kLeftRocketPathSevenPointTwoY = 0;

    // public static ArrayList<TrajectoryPoint> LeftRocketPath7 = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kLeftRocketPathSevenPointOneX, kLeftRocketPathSevenPointOneY, 30).pose, 
    //     new Pose2D(kLeftRocketPathSevenPointTwoX, kRightRocketPathSevenPointTwoY, 90).pose),
    //  kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

    // public static ArrayList<TrajectoryPoint> straightLine = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(0, 0, 0).pose, 
    //     new Pose2D(10, 0, 0).pose),
    //     100, 0, 0, kCruiseVelocity, kAcceleration, false);
    
    // private static double kMidCargoPathPointOneX = 0;
    // private static double kMidCargoPathPointOneY = 0;
    // private static double kMidCargoPathPointTwoX = 0;
    // private static double kMidCargoPathPointTwoY = 0;
    
    // public static ArrayList<TrajectoryPoint> midCargoPath = gen.generateTrajectory(
    //     Arrays.asList(new Pose2D(kMidCargoPathPointOneX, kMidCargoPathPointOneY, 0).pose,
    //     new Pose2D(kMidCargoPathPointTwoX, kMidCargoPathPointTwoY, 0).pose), 
    //     kCentripetalAcceleration, 0, 0, kCruiseVelocity, kAcceleration, false);

}
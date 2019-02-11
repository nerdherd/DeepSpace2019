package com.team687.constants;



/**
 * Important constants
 */

public class Constants {    

    // Camera constants
    public final static double kHorizontalFOV = 55;
    public final static double kVerticalFOV = 42.65386; // calculated from focalLength
    public final static double kHorizontalPixels = 320;
    public final static double kVerticalPixels = 240; 
    public final static double kFocalLength = 307.35714; // focalLength = px_width / (2 * tan(FOV / 2))

    // Physical camera constants
    public final static double kCameraHorizontalMountAngle = 6; 
    public final static double kCameraMountHeight = 39;
    public final static double kCameraHorizontalOffset = 5.5;
    
    public final static double kTargetHeight = 28.75; // inches
    
    public final static double kDriveRotationDeadband = 1.75;

    public final static double kJoystickDeadband = 0.02;
    public final static double kAngleTolerance = 0.1;
    public final static double kDistanceTolerance = 0.1; 
}

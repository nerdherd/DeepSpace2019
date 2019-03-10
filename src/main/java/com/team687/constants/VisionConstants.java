package com.team687.constants;

public class VisionConstants {    
    public final static double kHorizontalFOV = 55;
    public final static double kVerticalFOV = 42.65386; // calculated from focalLength
    public final static double kHorizontalPixels = 320;
    public final static double kVerticalPixels = 240; 
    public final static double kXFocalLength = 341.3307738; // focalLength = px_width / (2 * tan(FOV / 2))
    public final static double kYFocalLength = 332.3115843;

    // Physical camera constants
    public final static double kCameraHorizontalMountAngle = -10; 
    public final static double kCameraMountHeight = 34.5;
    public final static double kCameraHorizontalOffset = -8;
    
    public final static double kTargetHeight = 28.50; // inches
    
    public final static double kDriveRotationDeadband = 1.75;
    public final static double kDetectDistance = 9;
}

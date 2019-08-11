package com.team687.constants;

public class DriveConstants {

//	TalonSRX Constants
	public static final double kLeftP = 0.0003122;
	public static final double kLeftI = 0;
	public static final double kLeftD = 0;
	public static final double kLeftF = 0.025788;

	// Free Speed Constants (on jacks)
	// public static final double kLeftF = 0.02219;
	// public static final double kRightF = 0.022855;
	// public static final double kLeftStatic = 1.2;
	// public static final double kRightStatic = 1.128;
	
	public static final double kRightP = 0.0003122;
	public static final double kRightI = 0;
	public static final double kRightD = 0;
	public static final double kRightF = 0.0302;
	
	public static final double kLeftStatic = 1.428; //1.6

	public static final double kRightStatic = 1.432;
	
	public static final int kMaxAcceleration = 40000;
	public static final int kMaxVelocity = 40000;

//	Rot PID Constants
	public static final double kDriveRotationTolerance = 0;
	public static final double kMaxRotPower = 0.8;
	public static final double kMinRotPower = 0;
	public static final double kRotationalEncoderTolerance = 200;
	
//	Drive Constants, for driving straight
	public static final double kDriveP = 0;
	public static final double kDriveTolerance = 200;
	
//	Physical Robot Constants
	public static final double kWheelDiameter = 6;
	public static final double kDrivetrainWidth = 2.108211152;
	public static final double kLeftTicksPerFoot = 25000;
	public static final double kRightTicksPerFoot = 25000;
	
//	Other Constants 
	public static final double kJoystickDeadband = 0.1;
	
	public static final double kMinDistToBezierPoint = 100; //distance to target point at bezier curve where robot changes target to nect point
//	public static BezierCurve kTestCurveStraight = new BezierCurve()
	public static final double kMaximumTurnRadius = 50;
	
	public static final double kRotD = 0;
	public static final double kRotMinPower = 0;
	public static final double kRotPMaxPower = 1;
	
	public static final double kLeftAdjustment = 1;
	public static final double kRightAdjustment = 1;

	public static final double kRotP = 0;

	public static final double kVelocityPIDPeriod = 0.01;

	public static final double kDistP = 0.0001;
	public static final double kDistMaxPower = 1;
	public static final double kDistMinPower = 0;

	// Pathfinder Constants
	public static final double kLeftVelocityP = 0;
	public static final double kLeftVelocityD = 0;
	public static final double kLeftV = 0;

	public static final double kRightVelocityP = 0;
	public static final double kRightVelocityD = 0;
	public static final double kRightV = 0;


}

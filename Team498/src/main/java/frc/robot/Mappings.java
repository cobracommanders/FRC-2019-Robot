/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


public class Mappings {
	//controller port(s)
	public static final int ControllerPort = 0;
	
	//buttons on controller
	public static final int ButtonA = 1;
	public static final int ButtonB = 2;
	public static final int ButtonX = 3;
	public static final int ButtonY = 4;
	public static final int LeftBumper = 5;
	public static final int RightBumper = 6;
	public static final int ButtonBack = 7;
	public static final int ButtonStart = 8;
	public static final int LeftJoyPress = 9;
	public static final int RightJoyPress = 10;

	//triggers and joysticks 
	public static final int LeftXAxis = 0;
	public static final int LeftYAxis = 1;
	public static final int RightXAxis = 4;
	public static final int RightYAxis = 5;
	public static final int RightTrigger = 3;
	public static final int LeftTrigger = 2;

	//motor channels
	public static final int frontLeftDriveMotorChannel = 0;
	public static final int backLeftDriveMotorChannel = 1; 
	public static final int frontRightDriveMotorChannel = 2;
	public static final int backRightDriveMotorChannel = 3;

	public static final int wristMotorChannel = 4;

	public static final int intakeLeft = 5;
	public static final int intakeRight = 6;
	//double solenoid channels
	//TODO: need to change these motor channels
	public static final int clawForwardChannel = 0;
	public static final int clawReverseChannel = 0;
}

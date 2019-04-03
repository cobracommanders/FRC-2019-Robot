/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class VisionSubsystem extends Subsystem {
  private static final int cameraDevice1 = 0;
 	private static final int cameraDevice2 = 1;

  private UsbCamera camera1;
	private int width1 = 126;
	private int height1 = 84;
	
	private UsbCamera camera2;	
	private int width2 = 141;
	private int height2 = 94;

	public void startCapture() { 
		camera1 = CameraServer.getInstance().startAutomaticCapture("camera1", cameraDevice1);
		camera1.setResolution(width1, height1);
		camera1.setFPS(30);
		
		camera2 = CameraServer.getInstance().startAutomaticCapture("camera2", cameraDevice2);
		camera2.setResolution(width2, height2);
		camera2.setFPS(30);
		
		//SmartDashboard.putString("Camera connections", String.format("Camera 1: %s\nCamera 2: %s", camera1.isConnected(), camera2.isConnected()));
	}
	
  @Override
  public void initDefaultCommand() {

  }
}

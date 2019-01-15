/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static WristSubsystem wristSubsystem = null;

  public static WristSubsystem getWristSubsystem() {
    wristSubsystem = wristSubsystem == null ? new WristSubsystem() : wristSubsystem;
    return wristSubsystem;
  }

  //TODO: Need to change motor control channel :3
  private Victor wrist = new Victor(0);
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void wristPower(double power) {
    wrist.set(power);
  }
}

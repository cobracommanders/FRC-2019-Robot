/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ManualWrist;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  private static WristSubsystem wristSubsystem = null;

  //TODO: Need to change motor control channel :3
  private WPI_VictorSPX wrist = new WPI_VictorSPX(0);

  

  public static WristSubsystem getWristSubsystem() {
    wristSubsystem = wristSubsystem == null ? new WristSubsystem() : wristSubsystem;
    return wristSubsystem;
  }

  public WristSubsystem() {
    super("WristSubsystem");
 
  }

  @Override
  public void initDefaultCommand() {
     setDefaultCommand(new ManualWrist());
  }

  public void wristPower(double power) {
    wrist.set(power);
  }

  
}

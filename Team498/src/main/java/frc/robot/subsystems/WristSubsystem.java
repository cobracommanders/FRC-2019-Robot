/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ManualWrist;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.SensorCollection;


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


  private SensorCollection encoder;

  //TODO: Need to change motor control channel :3
  private WPI_TalonSRX wrist = new WPI_TalonSRX(0);

  public WristSubsystem() {
    super("WristSubsystem");
    encoder = wrist.getSensorCollection();

  }

  @Override
  public void initDefaultCommand() {
     setDefaultCommand(new ManualWrist());
  }

  public void wristPower(double power) {
    wrist.set(power);
  }

  public int getPulseWidthVelocity() {
    return encoder.getPulseWidthVelocity();
  }

  public int getPulseWidthPosition() {
    return encoder.getPulseWidthPosition();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class RampSubsystem extends Subsystem {
  
  private static final int rampForwardChannel = 0;
  private static final int rampReverseChannel = 0;

  private DoubleSolenoid ramp;

  public RampSubsystem() {
      ramp = new DoubleSolenoid(rampForwardChannel, rampReverseChannel);
      ramp.set(Value.kOff);
  } 

  @Override
  public void initDefaultCommand() {

  }

  public void releaseRamp() {
      ramp.set(Value.kForward); //potentially is kReverse who knows lol :)
  }
}

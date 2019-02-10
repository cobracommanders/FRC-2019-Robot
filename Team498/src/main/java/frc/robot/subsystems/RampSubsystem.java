/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class RampSubsystem extends Subsystem {
  
  private static final int rampChannel = 0;

  private Solenoid ramp;

  public RampSubsystem() {
      ramp = new Solenoid(rampChannel);
      ramp.set(false);
  } 

  @Override
  public void initDefaultCommand() {

  }

  public void releaseRamp() {
      ramp.set(true);
  }
}

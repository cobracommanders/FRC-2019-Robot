/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.commands.ToggleClawCommand;

public class ClawSubsystem extends Subsystem {

  private static final int clawForwardChannel = 1;
  private static final int clawReverseChannel = 0;  

  private DoubleSolenoid claw = new DoubleSolenoid(clawForwardChannel, clawReverseChannel);

  private boolean holdingOnToHatch;

  @Override
  public void initDefaultCommand() {
  }

  public void setClaw(boolean holdingOnToHatch) {

    if (holdingOnToHatch) {
      claw.set(Value.kForward);
    } else {
      claw.set(Value.kReverse);
    }
    this.holdingOnToHatch = holdingOnToHatch;
  }
}

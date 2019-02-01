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
import frc.robot.configurations.ClawSubsystemConfiguration;
import frc.robot.commands.ToggleClaw;

/**
 * Add your docs here.
 */
public class ClawSubsystem extends Subsystem {

  public static ClawSubsystem clawSubsystem = null;

  public static ClawSubsystem getClawSubsystem() {
    clawSubsystem = clawSubsystem == null ? new ClawSubsystem() : clawSubsystem;
    return clawSubsystem;
  }

  private DoubleSolenoid claw = new DoubleSolenoid(ClawSubsystemConfiguration.clawForwardChannel, ClawSubsystemConfiguration.clawReverseChannel);

  private boolean holdingOnToHatch;


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ToggleClaw());
  }

  public void setClaw(boolean holdingOnToHatch) {

    if (holdingOnToHatch) {
      claw.set(Value.kForward);
      this.holdingOnToHatch = holdingOnToHatch;
    } else {
      claw.set(Value.kReverse);
      this.holdingOnToHatch = holdingOnToHatch;
    }
  }
}

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
import frc.robot.Mappings;
import frc.robot.commands.ToggleClaw;

/**
 * Add your docs here.
 */
public class ClawSubsystem extends Subsystem {


  private DoubleSolenoid claw = new DoubleSolenoid(Mappings.clawForwardChannel, Mappings.clawReverseChannel);

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

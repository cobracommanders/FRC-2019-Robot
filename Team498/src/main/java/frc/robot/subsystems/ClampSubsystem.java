/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ToggleClamp;

/**
 * Add your docs here.
 */
public class ClampSubsystem extends Subsystem {

  // TODO: Need to change the forward and reverse channels of double solenoid
  private DoubleSolenoid clamp = new DoubleSolenoid(0, 0);

  private boolean isClamped;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ToggleClamp());
  }

  public void setClamp(boolean isClamped) {

    if (isClamped) {
      clamp.set(Value.kForward);
      this.isClamped = isClamped;
    } else {
      clamp.set(Value.kReverse);
      this.isClamped = isClamped;
    }
  }
}

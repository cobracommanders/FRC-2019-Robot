/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ToggleClampCommand;

/**
 * Add your docs here.
 */
public class ClampSubsystem extends Subsystem {
  private static final int clamp1ForwardChannel = 0;
  private static final int clamp1ReverseChannel = 0;
  private static final int clamp2ForwardChannel = 0;
  private static final int clamp2ReverseChannel = 0;
  private static final int releaseChannel = 0;

  // TODO: Need to change the forward and reverse channels of double solenoid
  private DoubleSolenoid clamp = new DoubleSolenoid(clamp1ForwardChannel, clamp1ReverseChannel);

  private Solenoid open = new Solenoid(releaseChannel);

  public ClampSubsystem() {
    super("ClampSubsystem");
    this.clamp.set(Value.kOff);
    this.open.set(false);
  }

  private boolean isClamped;

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ToggleClampCommand());
  }

  public void setClamp(boolean isClamped) {

    if (isClamped) {
      clamp.set(Value.kForward);
    } else {
      clamp.set(Value.kReverse);
    }
    this.isClamped = isClamped;
  }

  public void releaseClamps() {
      open.set(true);
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ToggleClampCommand;
import edu.wpi.first.wpilibj.Timer;

public class ClampSubsystem extends Subsystem {
  private static final int clampForwardChannel = 4;
  private static final int clampReverseChannel = 5;
  private static final int releaseChannel = 6;

  private DoubleSolenoid clamp = new DoubleSolenoid(clampForwardChannel, clampReverseChannel);

  private Solenoid open = new Solenoid(releaseChannel);

  private Timer timer;

  private boolean matchStarted = false;

  public ClampSubsystem() {
    super("ClampSubsystem");
    this.clamp.set(Value.kOff);
    this.open.set(false);
  }

  private boolean isClamped;

  @Override
  public void initDefaultCommand() {
  }

  public void setClamp() {
    isClamped = !isClamped;
    if (isClamped) {
      clamp.set(Value.kForward);
    } else {
      clamp.set(Value.kReverse);
    }
  }

  public void releaseClamps() {
      open.set(true);
  }

  public void shutDownClamps() {
    if(matchStarted){
    clamp.set(Value.kForward);

    }
  }

  public void startClampTimer() {
    timer.reset();
    timer.start();
  }

}
*/
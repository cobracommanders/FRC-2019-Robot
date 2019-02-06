/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.ClawSubsystem;

/**
 * Add your docs here.
 */
public class ToggleClawCommand extends InstantCommand {

  private ClawSubsystem claw;
  public boolean clawUp = false;
  
  /**
   * Add your docs here.
   */
  public ToggleClawCommand() {
    super("ToggleClaw");
    requires(Robot.clawSubsystem);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.clawUp = !clawUp;

    claw.setClaw(clawUp);
  }

}

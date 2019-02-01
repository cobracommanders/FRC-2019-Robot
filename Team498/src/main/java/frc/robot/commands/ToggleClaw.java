/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.ClawSubsystem;

/**
 * Add your docs here.
 */
public class ToggleClaw extends InstantCommand {

  private ClawSubsystem claw;
  public boolean holdingOnToHatch = false;
  
  /**
   * Add your docs here.
   */
  public ToggleClaw() {
    super("ToggleClaws");
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.holdingOnToHatch = !holdingOnToHatch;

    if(holdingOnToHatch) {
      claw.setClaw(true);
    } else {
      claw.setClaw(false);
    }
  }

}

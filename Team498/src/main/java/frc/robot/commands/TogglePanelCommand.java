/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class TogglePanelCommand extends InstantCommand {
 
  private boolean intake;
  public TogglePanelCommand() {
    super("TogglePanelCommand");
    requires(Robot.panelIntake);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    intake = !intake;
    if(intake) {
      Robot.panelIntake.setGrip(true);
    } else {
      Robot.panelIntake.setGrip(false);
    }
  }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class AutoWristCommand extends InstantCommand {
  boolean isGoingUp;

  public AutoWristCommand(boolean isGoingUp) {
    super("AutoWristCommand");
    requires(Robot.wrist);
    this.isGoingUp = isGoingUp;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.wrist.setTarget(isGoingUp);

  }

}

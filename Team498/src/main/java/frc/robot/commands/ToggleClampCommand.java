/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;

public class ToggleClampCommand extends InstantCommand {

  public ToggleClampCommand() {
    super("ToggleClamp");
    requires(Robot.clamp);
  }

  @Override
  protected void initialize() {
    Robot.clamp.setClamp();
  }
}

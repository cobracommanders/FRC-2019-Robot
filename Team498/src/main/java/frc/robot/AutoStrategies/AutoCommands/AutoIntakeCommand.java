/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoStrategies.AutoCommands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class AutoIntakeCommand extends InstantCommand {
 
  private double power;

  public AutoIntakeCommand(double power) {
    super("AutoIntakeCommand");
    requires(Robot.intake);
    this.power = power;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.intake.setIntake(power, power);
  }

}

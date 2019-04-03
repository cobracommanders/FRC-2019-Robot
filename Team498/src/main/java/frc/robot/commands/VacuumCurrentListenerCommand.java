/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class VacuumCurrentListenerCommand extends TimedCommand {

  public VacuumCurrentListenerCommand(double timeout) {
    super(timeout);
    requires(Robot.vacuum);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.operatorController.setRumble(1);
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.operatorController.setRumble(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

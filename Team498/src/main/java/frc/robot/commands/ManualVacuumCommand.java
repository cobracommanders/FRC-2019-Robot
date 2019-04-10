/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//import frc.robot.commands.VacuumCurrentListenerCommand;

public class ManualVacuumCommand extends Command {
  public ManualVacuumCommand() {
    requires(Robot.vacuum);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (Robot.operatorController.buttonB.get()) {
      Robot.vacuum.setVacuumPower(1);
    } else {
      Robot.vacuum.setVacuumPower(0);
    }

    /*
     * if (Robot.vacuum.rightMotorChannelCurrent > .1 &&
     * Robot.vacuum.leftMotorChannelCurrent > .1) { new
     * VacuumCurrentListenerCommand(1); }
     */

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoStrategies.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class AutoTimedIntakeCommand extends Command {

  private Timer timer;
  private double power;

  public AutoTimedIntakeCommand(double power) {
    super("AutoTimedIntakeCommand");
    requires(Robot.intake);
    this.power = power;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    System.out.println("We got here lol");
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double t = timer.get();
    if(t < 3) {
      Robot.intake.setIntake(power, power);
    }
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

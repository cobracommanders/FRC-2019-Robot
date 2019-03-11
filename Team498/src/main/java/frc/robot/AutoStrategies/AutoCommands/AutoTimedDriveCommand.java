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

public class AutoTimedDriveCommand extends Command {
  public double movePower;
  public double time;
  public double turnPower;

  private Timer timer = new Timer();

  public AutoTimedDriveCommand(double movePower, double turnPower, double time) {
    super("AutoTimedDriveCommand");
    requires(Robot.drivetrain);

    this.movePower = movePower;
    this.time = time;
    this.turnPower = turnPower;
  }

  protected void initialize() {
    timer.start();
  }

  protected void execute() {
    Robot.drivetrain.drive(movePower, turnPower);
  }

  protected boolean isFinished() {
    return timer.get() >= time;
  }

  protected void end() {
    Robot.drivetrain.drive(0, 0);
  }

  protected void interrupted() {
    end();
  }
}
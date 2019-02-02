/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ConstantAccelerationCalculator;
import frc.robot.Robot;

public class AutomaticWristCommand extends Command {


  private ConstantAccelerationCalculator calculator = new ConstantAccelerationCalculator(.00005);

  enum Positions {
    first,
    second,
    third
  }

  Positions position = Positions.first;
  private int direction = 0; //0 = up; 1 = down
  
  public void moveNext() {

    switch (position) {

      case first:
        position = Positions.second;
        break;

      case second:
        if (direction == 0) {
          position = Positions.third;

        } else {
          position = Positions.first;
        }
        break;

      case third:
        position = Positions.second;
        direction = 1;
      break;

    }
  }

  public AutomaticWristCommand() {
    super("AutomaticWristCommand");
    requires(Robot.wrist);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
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

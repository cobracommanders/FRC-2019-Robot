/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.ConstantAccelerationCalculator;
import frc.robot.Operator;
import frc.robot.Robot;

public class ManualWristCommand extends Command {

  private Operator operator = Operator.getOperator();
  private WristSubsystem wrist;
  private ConstantAccelerationCalculator calculator = new ConstantAccelerationCalculator(.00005);


  public ManualWristCommand() {
    super("ManualWristCommand");
    requires(Robot.wristSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double power = calculator.getNextDataPoint(operator.controller.axisRightY.getAxisValue());

    wrist.wristPower(power);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    wrist.wristPower(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

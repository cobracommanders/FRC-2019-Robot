/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.ConstantAccelerationCalculator;

public class ManualDrive extends Command {

  private Operator operator = Operator.getOperator();
  private Drivetrain drivetrain;
  private ConstantAccelerationCalculator moveAcceleration = new ConstantAccelerationCalculator(0.00005);
  private ConstantAccelerationCalculator turnAcceleration = new ConstantAccelerationCalculator(0.00005);


  public ManualDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    super("RampDrive");

    requires(this.drivetrain = Drivetrain.getDrivetrain());


  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double move = moveAcceleration.getNextDataPoint(operator.controller.axisRightTrigger.getAxisValue() - operator.controller.axisRightTrigger.getAxisValue());
    double turn = moveAcceleration.getNextDataPoint(operator.controller.axisLeftX.getAxisValue());

    this.drivetrain.drive(move, turn);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivetrain.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}

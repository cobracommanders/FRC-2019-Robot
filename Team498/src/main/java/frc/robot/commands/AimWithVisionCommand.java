/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AimWithVisionCommand extends Command {
  private double Kp = -0.1;
  private double min_command = 0.05;

  private double leftSpeed;
  private double rightSpeed;

  private NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  private NetworkTableEntry tx = table.getEntry("tx");
  private double x = tx.getDouble(0.0);


  public AimWithVisionCommand() {
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.operatorController.buttonB.get()) {

      double heading_error = -x;
      double steering_adjust = 0.0;

      if (x > 1.0) {
        steering_adjust = Kp * heading_error - min_command;
      } else if (x < 1.0) {
        steering_adjust = Kp * heading_error + min_command;
      }

      leftSpeed += steering_adjust;
      rightSpeed -= steering_adjust;

      Robot.drivetrain.aimAssistDrive(leftSpeed, rightSpeed);

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

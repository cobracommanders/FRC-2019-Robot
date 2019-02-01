/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class ToggleIntake extends Command {

  private IntakeSubsystem ballIntake;
  private double leftPower;
  private double rightPower; 


  public ToggleIntake(double leftPower, double rightPower) {
    super("ManualIntake");
    this.leftPower = leftPower;
    this.rightPower = rightPower;
    requires(this.ballIntake = IntakeSubsystem.getIntakeSubsystem());
   
  }


  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if (ballIntake.getLastLeft() == leftPower) {
      ballIntake.setIntake(0, 0);
    } else {
      ballIntake.setIntake(leftPower, rightPower);
    }
  }


  @Override
  protected boolean isFinished() {
    return true;
  }

  
  @Override
  protected void end() {
  }


  @Override
  protected void interrupted() {
  }
}

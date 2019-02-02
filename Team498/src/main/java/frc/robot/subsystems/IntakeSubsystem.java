/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.configurations.IntakeSubsystemConfiguration;
import edu.wpi.first.wpilibj.Victor;


public class IntakeSubsystem extends Subsystem {


  //TODO: Need to change motor channels
  private Victor intakeLeft = new Victor(IntakeSubsystemConfiguration.intakeLeftMotorChannel);
  private Victor intakeRight = new Victor(IntakeSubsystemConfiguration.intakeRightMotorChannel);


  private double lastLeft = 0;
  private double lastRight = 0;

  @Override
  public void initDefaultCommand() {

  }


  public void setIntake(double leftPower, double rightPower) {
    intakeLeft.set(leftPower);
    intakeRight.set(rightPower);
    lastLeft = leftPower;
    lastRight = rightPower;

  }

  public double getLastLeft() {
    return lastLeft;
  }

  public double getLastRight() {
    return lastRight;
  }


}

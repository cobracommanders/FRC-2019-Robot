/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Mappings;
import edu.wpi.first.wpilibj.Victor;


public class IntakeSubsystem extends Subsystem {

 
  private Victor intakeLeft = new Victor(Mappings.intakeLeft);
  private Victor intakeRight = new Victor(Mappings.intakeRight);
  
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

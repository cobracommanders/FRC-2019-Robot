/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualVacuumCommand;

public class VacuumSubsystem extends Subsystem {
  private static final int vacuumLeftMotorChannel = 0;
  private static final int vacuumRightMotorChannel = 0;

  //change this later to different motor controller
  private Spark leftVacuum = new Spark(vacuumLeftMotorChannel);
  private Spark rightVacuum = new Spark(vacuumRightMotorChannel);

  public VacuumSubsystem() {
    super("VacuumSubsystem");
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualVacuumCommand());
  }

  public void setVacuumPower(double power) {
    leftVacuum.set(power);
    rightVacuum.set(power);
  }

  public void updateDashboard() {
    SmartDashboard.putNumber("Left Vacuum Power", leftVacuum.get());
    SmartDashboard.putNumber("Right Vacuum Power", rightVacuum.get());
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.ManualVacuumCommand;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class VacuumSubsystem extends Subsystem {
  private static final int vacuumLeftMotorChannel = 2;
  private static final int vacuumRightMotorChannel = 3;

  private Victor leftVacuum = new Victor(vacuumLeftMotorChannel);
  private Victor rightVacuum = new Victor(vacuumRightMotorChannel);

  public PowerDistributionPanel pdp = new PowerDistributionPanel();

  public double leftMotorChannelCurrent = pdp.getCurrent(12);
  public double rightMotorChannelCurrent = pdp.getCurrent(13);

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
    SmartDashboard.putNumber("Left Vacuum Current", leftMotorChannelCurrent);
    SmartDashboard.putNumber("Right Vacuum Current", rightMotorChannelCurrent);
  }
}

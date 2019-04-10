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
import edu.wpi.first.wpilibj.Spark;

public class VacuumSubsystem extends Subsystem {
  private static final int vacuumMotorChannel = 4;

  private Spark vacuum = new Spark(vacuumMotorChannel);

  public VacuumSubsystem() {
    super("VacuumSubsystem");
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualVacuumCommand());
  }

  public void setVacuumPower(double power) {
    vacuum.set(power);
  }

  public void updateDashboard() {
    SmartDashboard.putNumber("Vacuum Power", vacuum.get());
  }
}

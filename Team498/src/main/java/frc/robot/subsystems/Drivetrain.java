/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Drivetrain extends Subsystem {


  private static Drivetrain drivetrain = null;

  public static Drivetrain getDrivetrain() {
    drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
    return drivetrain;
  }


  // TODO:will need to change the motor channels.
  private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(0);
  private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(0);
  private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(0);
  private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(0);

  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ManualDrive());
  }

  public void drive(double move, double turn) {
    frontLeftDrive.set(move + turn);
    frontRightDrive.set(move - turn);
    backLeftDrive.set(move - turn);
    backRightDrive.set(move - turn);
  }

}

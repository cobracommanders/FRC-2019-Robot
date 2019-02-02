/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.commands.ManualDriveCommand;
import frc.robot.configurations.DrivetrainSubsystemConfiguration;



public class DrivetrainSubsystem extends Subsystem {


  private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(DrivetrainSubsystemConfiguration.frontLeftDriveMotorChannel);
  private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(DrivetrainSubsystemConfiguration.frontRightDriveMotorChannel);
  private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(DrivetrainSubsystemConfiguration.backLeftDriveMotorChannel);
  private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(DrivetrainSubsystemConfiguration.backRightDriveMotorChannel);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDriveCommand());
  }

  public void drive(double move, double turn) {
    drive.arcadeDrive(move, turn);
  }

}

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
import com.ctre.phoenix.motorcontrol.SensorCollection;

public class DrivetrainSubsystem extends Subsystem {
  private static final int frontLeftDriveMotorChannel = 0;
  private static final int backLeftDriveMotorChannel = 1; 
  private static final int frontRightDriveMotorChannel = 2;
  private static final int backRightDriveMotorChannel = 3;
  
  private static final double WheelDiameter = 0.1016; // 4 inch wheels. This was converted to meters
  private static final double PulsePerRevolution = 1024; // pulses per revolution
  private static final double WheelCircumference = WheelDiameter * Math.PI;
  private static final double MetersPerPulse = WheelCircumference / PulsePerRevolution;

  private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(frontLeftDriveMotorChannel);
  private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(backLeftDriveMotorChannel);
  private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(frontRightDriveMotorChannel);
  private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(backRightDriveMotorChannel);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

  private SensorCollection leftEncoder = new SensorCollection(frontLeftDrive);
  private SensorCollection rightEncoder = new SensorCollection(frontRightDrive);

  public DrivetrainSubsystem() {
    super("DrivetrainSubsystem");
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDriveCommand());
  }

  public void drive(double move, double turn) {
    drive.arcadeDrive(move, turn);
  }

  public double getPulseWidthPosition() {
    return ((leftEncoder.getPulseWidthPosition() + rightEncoder.getPulseWidthPosition()) / 2);
  }

}

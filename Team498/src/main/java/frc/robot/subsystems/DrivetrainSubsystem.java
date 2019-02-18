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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Pigeon;
import frc.robot.commands.ManualDriveCommand;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU_StickyFaults;

public class DrivetrainSubsystem extends Subsystem {

    private static final int frontLeftDriveMotorChannel = 0;
    private static final int backLeftDriveMotorChannel = 1;
    private static final int frontRightDriveMotorChannel = 2;
    private static final int backRightDriveMotorChannel = 3;

    private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(frontLeftDriveMotorChannel);
    private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(frontRightDriveMotorChannel);
    private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(backLeftDriveMotorChannel);
    private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(backRightDriveMotorChannel);

    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

    private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

    private Pigeon gyro = new Pigeon(backLeftDrive);

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualDriveCommand());
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);
    }

    public void resetEncoders() {
        frontLeftDrive.setSelectedSensorPosition(0);
        backRightDrive.setSelectedSensorPosition(0);
        // frontLeftDrive.getSensorCollection().setQuadraturePosition(0, 0); //second is timeout, don't worry about it
        // backRightDrive.getSensorCollection().setQuadraturePosition(0, 0);
    }

    public double getDistance() {
        //frontLeftDrive.getSelectedSensorPosition();
        double frontLeftDistance = frontLeftDrive.getSensorCollection().getQuadraturePosition();
        double backRightDistance = backLeftDrive.getSensorCollection().getQuadraturePosition();
        double distance = ((frontLeftDistance + backRightDistance) / 2);

        return distance;
    }

    public double getAngle() {
        return gyro.getAngle();
    }

    
    public void resetGyro() {
        gyro.resetPosition();
    }

    public void updateDashboard() {
      SmartDashboard.putNumber("Angle X", gyro.getAngle());
    }

}

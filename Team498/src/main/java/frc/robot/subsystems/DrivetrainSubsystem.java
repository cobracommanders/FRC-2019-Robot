/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Victor;
import frc.robot.Pigeon;
import frc.robot.commands.ManualDriveCommand;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class DrivetrainSubsystem extends PIDSubsystem {

    private static final int frontLeftDriveMotorChannel = 2;
    private static final int backLeftDriveMotorChannel = 3; 
    private static final int frontRightDriveMotorChannel = 0; 
    private static final int backRightDriveMotorChannel = 1; 

    private static final double WheelDiameter = 4; // 4 inch wheels.
    private static final double PulsePerRevolution = 4096;
    private static final double DistancePerPulse = (WheelDiameter * Math.PI) / (PulsePerRevolution);

    private double currentMove;

    private Talon frontLeftDrive = new Talon(frontLeftDriveMotorChannel);
    private Talon frontRightDrive = new Talon(frontRightDriveMotorChannel);
    private Talon backLeftDrive = new Talon(backLeftDriveMotorChannel);
    private Talon backRightDrive = new Talon(backRightDriveMotorChannel);

    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

    private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

    // private Pigeon gyro = new Pigeon(backLeftDrive);

    public DrivetrainSubsystem() {
        super("DrivetrainSubsystem", 0.16, 0.02, 1.1); // was .1, .01, .1

        this.getPIDController().setContinuous(false);
        this.getPIDController().setInputRange(-180, 180);
        this.getPIDController().setOutputRange(-1, 1);
        this.getPIDController().setAbsoluteTolerance(4.64); // Was 1 last year

        //frontLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        //backRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualDriveCommand());
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(-move, turn);
        this.currentMove = move;

        // Replacement code if we want PID turn in teleop
        /*
         * if(turn != 0){ if(getPIDController().isEnabled()){
         * getPIDController().disable(); }else{ drive.arcadeDrive(move, turn);
         * this.currentMove = move; }
         * 
         * }else{ if(!getPIDController().isEnabled()){ gyro.resetPosition();
         * getPIDController().setSetpoint(0); getPIDController().enable(); }else{
         * this.currentMove = move; } }
         */

    }

    public void autoDrive(double move, double turn) {
        if (turn != 0) {
            drive.arcadeDrive(move, turn);
            this.currentMove = move;
        } else {
            this.currentMove = move;
        }
    }

    public void resetEncoders() {
        //frontLeftDrive.setSelectedSensorPosition(0);
        //backRightDrive.setSelectedSensorPosition(0);
        // frontLeftDrive.getSensorCollection().setQuadraturePosition(0, 0); //second is
        // timeout, don't worry about it
        // backRightDrive.getSensorCollection().setQuadraturePosition(0, 0);
    }

    /*public double getDistance() {
        // frontLeftDrive.getSelectedSensorPosition();
        double frontLeftDistance = frontLeftDrive.getSelectedSensorPosition();
        double backRightDistance = backRightDrive.getSelectedSensorPosition();

        double distanceInPulses = ((frontLeftDistance - backRightDistance) / 2);

        double distance = distanceInPulses * DistancePerPulse;

        return distance;
    }*/

    public double getAngle() {
        // return gyro.getAngle();
        return 0;
    }

    public void resetGyro() {
        // gyro.resetPosition();
    }

    public void updateDashboard() {
        // SmartDashboard.putNumber("Angle X", gyro.getAngle());
        /*SmartDashboard.putNumber("Left Encoder", frontLeftDrive.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Right Encoder", backRightDrive.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("DriveDistance", getDistance());*/
    }

    public double returnPIDInput() {
        // return -gyro.getAngle();
        return 0;
    }

    public void usePIDOutput(double PIDOutput) {
        drive.arcadeDrive(this.currentMove, PIDOutput);
    }

}

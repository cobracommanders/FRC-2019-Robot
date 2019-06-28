/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Pigeon;
import frc.robot.commands.ManualDriveCommand;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class DrivetrainSubsystem extends PIDSubsystem {

    private static final int frontLeftDriveMotorChannel = 0;
    private static final int backLeftDriveMotorChannel = 1;
    private static final int frontRightDriveMotorChannel = 2;
    private static final int backRightDriveMotorChannel = 3;

    private static final double WheelDiameter = 4; // 4 inch wheels.
    private static final double PulsePerRevolution = 4096;
    private static final double DistancePerPulse = (WheelDiameter * Math.PI) / (PulsePerRevolution);

    private double currentMove;
    private double cap;

    private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(frontLeftDriveMotorChannel);
    private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(frontRightDriveMotorChannel);
    private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(backLeftDriveMotorChannel);
    private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(backRightDriveMotorChannel);

    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

    private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);

    private Pigeon gyro = new Pigeon(backLeftDrive);

    private boolean usingGyro = true;

    public DrivetrainSubsystem() {
        //0.16, 0.02, 1.1
        super("DrivetrainSubsystem", .1, .01, .1); // was .1, .01, .1

        this.getPIDController().setContinuous(false);
        this.getPIDController().setAbsoluteTolerance(4.65); // Was 1 last year.174
        this.getPIDController().setOutputRange(-1, 1);

        frontLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        backRightDrive.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualDriveCommand());
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);
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

    public void setCap(double cap) {
        this.cap = cap;
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
        frontLeftDrive.setSelectedSensorPosition(0);
        backRightDrive.setSelectedSensorPosition(0);


        // frontLeftDrive.getSensorCollection().setQuadraturePosition(0, 0); //second is
        // timeout, don't worry about it
        // backRightDrive.getSensorCollection().setQuadraturePosition(0, 0);
    }

    public double getDistance() {
        // frontLeftDrive.getSelectedSensorPosition();
        double frontLeftDistance = frontLeftDrive.getSelectedSensorPosition();
        double backRightDistance = backRightDrive.getSelectedSensorPosition();

        double distanceInPulses = ((frontLeftDistance - backRightDistance) / 2);

        double distance = distanceInPulses * DistancePerPulse;

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
        SmartDashboard.putNumber("Left Encoder Quad", frontLeftDrive.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Right Encoder Quad", backRightDrive.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("DriveDistance (Average SelSens)", getDistance());
        SmartDashboard.putBoolean("OnTarget", getPIDController().onTarget());
        SmartDashboard.putNumber("Error", getPIDController().getError());
    }

    public void useGyro(boolean b) {
        usingGyro = b;
        if(usingGyro) {
            this.getPIDController().setPID(.1, .01, .1);
            this.getPIDController().setInputRange(-180, 180);
        }
        else {
            this.getPIDController().setPID(0.1, 0, 0);
            this.getPIDController().setInputRange(-144, 144);
        }
    }

    public double returnPIDInput() {
        if(usingGyro) return -gyro.getAngle();
        return -getDistance();
    }

    public void usePIDOutput(double PIDOutput) {
        if(usingGyro) drive.arcadeDrive(this.currentMove, PIDOutput);
        else {
            double newTurn;
            newTurn = gyro.getAngle();
            newTurn /= -90;
            drive.arcadeDrive(cap * PIDOutput, newTurn);
        }
    }

}

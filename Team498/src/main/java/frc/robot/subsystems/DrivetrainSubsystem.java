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

public class DrivetrainSubsystem extends DoublePIDSubsystem {

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
        //First three numbers are gyro PID, second three are encoder PID
        super("DrivetrainSubsystem", .1, .01, .1, .037, 0.02, 0); // was .1, .01, .1

        this.getPIDController0().setContinuous(false);
        this.getPIDController0().setAbsoluteTolerance(.174); // Was 1 last year4.65
        this.getPIDController0().setOutputRange(-1, 1); //power
        this.getPIDController0().setInputRange(-180, 180); //degrees
        this.getPIDController1().setContinuous(false);
        this.getPIDController1().setAbsoluteTolerance(.174);
        this.getPIDController1().setOutputRange(-1, 1); //power
        this.getPIDController1().setInputRange(-144,144); //-12 feet to 12 feet

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
        SmartDashboard.putBoolean("OnTarget0", getPIDController0().onTarget());
        SmartDashboard.putNumber("Error0", getPIDController0().getError());
        SmartDashboard.putBoolean("OnTarget1", getPIDController1().onTarget());
        SmartDashboard.putNumber("Error1", getPIDController1().getError());
        SmartDashboard.putNumber("Output 0 (Gyro)", output0);
        SmartDashboard.putNumber("Output 1 (Encoders)", output1);
    }

    public double returnPIDInput0() {
        return -gyro.getAngle();
    }

    public void usePIDOutput0(double PIDOutput) {
        drive.arcadeDrive(this.currentMove, PIDOutput);
    }

    public double returnPIDInput1() {
        return -getDistance();
    }

    public void usePIDOutput1(double PIDOutput) {
        drive.arcadeDrive(PIDOutput, 0);
    }

    public void usePIDOutput(double output0, double output1) {
        //output 0 is gyro pid, output 1 is encoder pid
        drive.arcadeDrive(output1, output0);
    }
}

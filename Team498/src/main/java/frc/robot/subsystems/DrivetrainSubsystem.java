/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Pigeon;
import frc.robot.commands.ManualDriveCommand;


public class DrivetrainSubsystem extends PIDSubsystem {

    private static final int frontLeftDriveMotorChannel = 0;
    private static final int backLeftDriveMotorChannel = 1;
    private static final int frontRightDriveMotorChannel = 2;
    private static final int backRightDriveMotorChannel = 3;

    private double currentMove;

    private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(frontLeftDriveMotorChannel);
    private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(frontRightDriveMotorChannel);
    private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(backLeftDriveMotorChannel);
    private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(backRightDriveMotorChannel);

    private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
    private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

    private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);
    

    private Pigeon gyro = new Pigeon(backLeftDrive);

    public DrivetrainSubsystem() {
        super("DrivetrainSubsystem", 0.1, 0.01, 0.1);

        this.getPIDController().setContinuous(false);
        this.getPIDController().setInputRange(-180, 180);
        this.getPIDController().setOutputRange(-1, 1);
        this.getPIDController().setAbsoluteTolerance(.01); //Was 1 last year
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualDriveCommand());
    }

    public void drive(double move, double turn) {
        drive.arcadeDrive(move, turn);
        this.currentMove = move;

        //Replacement code if we want PID turn in teleop
        /*if(turn != 0){
            if(getPIDController().isEnabled()){
            getPIDController().disable();
            }else{
                drive.arcadeDrive(move, turn);
                this.currentMove = move;
            }
        
        }else{
            if(!getPIDController().isEnabled()){
                gyro.resetPosition();
                getPIDController().setSetpoint(0);
                getPIDController().enable();
            }else{
                this.currentMove = move;
            }
        }*/

    }
    public void autoDrive(double move, double turn){
        if(turn != 0){
            drive.arcadeDrive(move, turn);
            this.currentMove = move;
        }else{
            this.currentMove = move;
        }
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
      SmartDashboard.putNumber("Left Encoder", frontLeftDrive.getSensorCollection().getQuadraturePosition());
      SmartDashboard.putNumber("Right Encoder", backLeftDrive.getSensorCollection().getQuadraturePosition());
      SmartDashboard.putNumber("DriveDistance", getDistance());
    }

    public double returnPIDInput(){
        return -gyro.getAngle();
    }

    public void usePIDOutput(double PIDInput){
        drive.arcadeDrive(this.currentMove, PIDInput);
    }
    

}

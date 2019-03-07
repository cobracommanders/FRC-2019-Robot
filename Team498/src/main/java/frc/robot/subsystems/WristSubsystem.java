/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualWristCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

public class WristSubsystem extends PIDSubsystem {
    private static final int wristMotorChannel = 6;
    private static final int wristEncoderChannelA = 2;
    private static final int wristEncoderChannelB = 3;
    private static final int inLimitSwitchChannel = 0;
    private static final int outLimitSwitchChannel = 1;

    private int target = 0;

    private double distancePerPulse = 360 / (4096 * 150); // does the math to change to degrees. 1 to 150 gear ratio

    private CANSparkMax wrist = new CANSparkMax(wristMotorChannel, MotorType.kBrushed);

    private DigitalInput inLimitSwitch = new DigitalInput(inLimitSwitchChannel);
    private DigitalInput outLimitSwitch = new DigitalInput(outLimitSwitchChannel);

    private Encoder encoder = new Encoder(wristEncoderChannelA, wristEncoderChannelB);

    public WristSubsystem() {
        super("WristSubsystem", 0.1, 0.01, 0.1);
        this.encoder.setDistancePerPulse(distancePerPulse);
        this.getPIDController().setContinuous(false);
        this.getPIDController().setInputRange(0, 130);
        this.getPIDController().setOutputRange(-1, 1);
        this.getPIDController().setAbsoluteTolerance(.01); // Was 1 last year
        this.getPIDController().enable();

    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualWristCommand());
    }

    public void setTarget(boolean isGoingUp) {
        if (isGoingUp) {
            if (target > 0) {
                target--;
            }
        } else {
            if (target < 2) {
                target++;
            }
        }
        switch (target) {
        case 0:
            this.getPIDController().setSetpoint(130); // All the way in to be changed
            break;
        case 1:
            this.getPIDController().setSetpoint(45); // Shooting angle to be changed
            break;
        case 2:
            this.getPIDController().setSetpoint(0); // Intake / down angle to be changed
            break;
        }
    }

    public void wristPower(double power) {
        wrist.set(.4 * power);
    }

    public double returnPIDInput() {
        return encoder.get(); // TODO: UPDATE TO WHAT WE ACTUALLY READ TO GET THIS
    }

    public void usePIDOutput(double PIDOutput) {
        wristPower(PIDOutput);
    }

    public void updateDashboard() {
        SmartDashboard.putNumber("EncoderGetDistance", encoder.getDistance());
        SmartDashboard.putNumber("EncoderGet", encoder.get());
        SmartDashboard.putNumber("EncoderGetRate", encoder.getRate());
        SmartDashboard.putNumber("EncoderGetDistancePerPulse", encoder.getDistancePerPulse());
        SmartDashboard.putBoolean("InLimitSwitchValue", inLimitSwitch.get());
        SmartDashboard.putBoolean("OutLimitSwitchValue", outLimitSwitch.get());

    }
}

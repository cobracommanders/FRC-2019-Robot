/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ManualWristCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

public class WristSubsystem extends Subsystem {
  private static final int wristMotorChannel = 6;
  private static final int wristEncoderChannelA = 2;
  private static final int wristEncoderChannelB = 3;
  private static final int inLimitSwitchChannel = 0;
  private static final int outLimitSwitchChannel = 1; 

  private double distancePerPulse = 360 / (4096 * 150); //does the math to change to degrees. 1 to 150 gear ratio

  private CANSparkMax wrist = new CANSparkMax(wristMotorChannel, MotorType.kBrushed);

  private DigitalInput inLimitSwitch = new DigitalInput(inLimitSwitchChannel);
  private DigitalInput outLimitSwitch = new DigitalInput(outLimitSwitchChannel);

  private Encoder encoder = new Encoder(wristEncoderChannelA, wristEncoderChannelB);

  public WristSubsystem() {
      super("WristSubsystem");
      this.encoder.setDistancePerPulse(distancePerPulse);
  }
  
  @Override
  public void initDefaultCommand() {
     setDefaultCommand(new ManualWristCommand());
  }

  public void wristPower(double power) {
    wrist.set(.4 * power);
  }
  
  public void updateDashboard() {
    SmartDashboard.putNumber("EncoderValue", encoder.getDistance());
    SmartDashboard.putBoolean("InLimitSwitchValue", inLimitSwitch.get());
    SmartDashboard.putBoolean("OutLimitSwitchValue", outLimitSwitch.get());

  }
}

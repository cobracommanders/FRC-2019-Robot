/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.PursueWristTargetCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Encoder;

public class WristSubsystem extends PIDSubsystem {
  private static final int wristMotorChannel = 6;
  private static final int wristEncoderChannelA = 2;
  private static final int wristEncoderChannelB = 3;
  private static final int inLimitSwitchChannel = 0;
  private static final int outLimitSwitchChannel = 1;

  Positions currentPosition = Positions.IN;
  Positions targetPosition = Positions.IN;

  private DigitalInput inLimitSwitch = new DigitalInput(inLimitSwitchChannel);
  private DigitalInput outLimitSwitch = new DigitalInput(outLimitSwitchChannel);

  private Encoder encoder = new Encoder(wristEncoderChannelA, wristEncoderChannelB);

  private static final double wristPow = 0.2; // does this need to be final and if its final wouldn't it be static??

  // TODO: Need to run trial tests to find out these numbers
  private static final double p = 0.1;
  private static final double i = 0;
  private static final double d = 0;
  private static final double distancePerPulse = 360.0 / (4096.0 * 150.0); // Does the math to get 360 degrees out of a
                                                                           // rotation, gear ratio is 1 to 150.

  private CANSparkMax wrist = new CANSparkMax(wristMotorChannel, MotorType.kBrushed);

  public WristSubsystem() {
    super("WristSubsystem", p, i, d);

    this.encoder.setDistancePerPulse(distancePerPulse);
    this.encoder.reset();
    this.setAbsoluteTolerance(0.05);
    this.getPIDController().setContinuous(false); // this has to be false, otherwise the robot fails.
    this.getPIDController().setInputRange(0, 120); // 120 deg, IN to OUT, also have to have this. 4
    this.getPIDController().setOutputRange(-1, 1);
    this.getPIDController().enable(); // this has to be here lol. PID starts disabled
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new PursueWristTargetCommand());
  }

  private void wristPower(boolean moveOut) {
    if (moveOut)
      wrist.set(wristPow);
    else
      wrist.set(-wristPow);
  }

  public void stop() {
    wrist.set(0);
  }

  // TODO: test this to see if this is doing what we are expecting
  public void pursueTarget() {

    boolean isIn = inLimitSwitch.get();
    boolean isOut = outLimitSwitch.get();

    if (targetPosition != Positions.SHOOT) {
      // check if we hit the out angle
      if (isOut)
        currentPosition = Positions.OUT;

      // check if we hit the in angle
      if (isIn)
        currentPosition = Positions.IN;
    } else
      currentPosition = Positions.SHOOT;

    if (targetPosition == Positions.OUT && !isOut)
      wristPower(true);
    if (targetPosition == Positions.IN && !isIn)
      wristPower(false);
    if (targetPosition == Positions.SHOOT)
      this.getPIDController().setSetpoint(45);
    if (targetPosition == currentPosition && targetPosition != Positions.SHOOT)
      stop();
  }

  // TODO: Test both moveOut and moveIn methods
  public void moveOut() {
    switch (currentPosition) {
    case IN:
      targetPosition = Positions.SHOOT;
      break;

    case SHOOT:
      targetPosition = Positions.OUT;
      break;

    case OUT:
      break;
    }
  }

  public void moveIn() {
    switch (currentPosition) {
    case OUT:
      targetPosition = Positions.SHOOT;
      break;

    case SHOOT:
      targetPosition = Positions.IN;
      break;

    case IN:
      break;
    }
  }

  @Override
  public double returnPIDInput() {
    return encoder.getDistance();
  }

  @Override
  public void usePIDOutput(double value) {
    if (targetPosition == Positions.SHOOT)
      wrist.set(value);
  }

  enum Positions {
    OUT(2), SHOOT(1), IN(0);

    private final int positionCode;

    private Positions(int positionCode) {
      this.positionCode = positionCode;
    }

    public int getPositionCode() {
      return this.positionCode;
    }
  }

  public void updateDashboard() {
    SmartDashboard.putNumber("Encoder Value", encoder.getDistance());
    SmartDashboard.putNumber("Encoder X", encoder.get());
    SmartDashboard.putBoolean("In Limit Switch Value", inLimitSwitch.get());
    SmartDashboard.putBoolean("Out Limit Switch Value", outLimitSwitch.get());
    SmartDashboard.putString("Wrist Postion", currentPosition.toString());
  }
}

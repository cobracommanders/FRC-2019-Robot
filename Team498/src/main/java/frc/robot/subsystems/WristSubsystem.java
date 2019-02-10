/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.commands.PursueWristTargetCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.Encoder;

/**
 * Add your docs here.
 */
public class WristSubsystem extends PIDSubsystem {
  private static final int inLimitSwitchChannel = 0;
  private static final int outLimitSwitchChannel = 1;
  private static final int wristEncoderA = 0;
  private static final int wristEncoderB = 0;
  private static final int wristMotorChannel = 6;

  Positions currentPosition = Positions.IN;
  Positions targetPosition = Positions.IN;

  private DigitalInput inLimitSwitch = new DigitalInput(inLimitSwitchChannel);
  private DigitalInput outLimitSwitch = new DigitalInput(outLimitSwitchChannel);

  private Encoder encoder = new Encoder(wristEncoderA, wristEncoderB);

  private final double wristPow = 0.2;
  private static final double p = 5.0;
  private static final double i = 0.5;
  private static final double d = 0.1;
  private static final double distancePerPulse = 360.0 / 4096.0; // Does the math to get 360 degrees out of a rotation, haven't accounted for gear ratio

  private CANSparkMax wrist = new CANSparkMax(wristMotorChannel, MotorType.kBrushed);

  public WristSubsystem() {
    super("WristSubsystem", p, i, d);

    this.encoder.setDistancePerPulse(distancePerPulse);
    this.encoder.reset();
    this.setAbsoluteTolerance(0.05);
    this.getPIDController().setContinuous(true);
    // this.getPIDController().setInputRange(0, 120); //120 deg, IN to OUT, might
    // have to flip sign. If setContinuous is false this is needed
    this.getPIDController().setOutputRange(-1, 1);
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
      this.getPIDController().setSetpoint(25);
    if (targetPosition == currentPosition && targetPosition != Positions.SHOOT)
      stop();
  }

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
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Mappings;
import frc.robot.commands.PursueWristTargetCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {

    enum Positions {
        OUT(2),
        SHOOT(1),
        IN(0);
        
        private final int positionCode;
        private Positions(int positionCode) {
            this.positionCode = positionCode;
        }

        public int getPositionCode() {
            return this.positionCode;
        }
      }
    
    Positions currentPosition = Positions.IN;
    Positions targetPosition = Positions.IN;

    private DigitalInput inLimitSwitch = new DigitalInput(Mappings.inLimitSwitchChannel);
    private DigitalInput outLimitSwitch = new DigitalInput(Mappings.outLimitSwitchChannel);
    
    private final double wristPow = 0.8;
    
    private CANSparkMax wrist = new CANSparkMax(Mappings.wristMotorChannel, MotorType.kBrushed);

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new PursueWristTargetCommand());
    }

    public void wristPower(double power) {
        if (inLimitSwitch.get() && power > 0) {
            wrist.set(0);
        } else if (outLimitSwitch.get() && power < 0) {
            wrist.set(0);
        } else {
            wrist.set(.8 * power);
        }
    }

    public void pursueTarget() {
        //check if we hit the out angle
        if(outLimitSwitch.get()) currentPosition = Positions.OUT;

        //check if we hit the in angle
        if(inLimitSwitch.get()) currentPosition = Positions.IN;

        if (targetPosition.getPositionCode() > currentPosition.getPositionCode()) {
            wristPower(-wristPow);
        } else if (targetPosition.getPositionCode() < currentPosition.getPositionCode()) {
            wristPower(wristPow);
        } else {
            wristPower(0);
        }
        
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

}

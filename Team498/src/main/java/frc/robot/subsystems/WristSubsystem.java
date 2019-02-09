/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Mappings;
import frc.robot.commands.ManualWristCommand;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {


    private DigitalInput topLimitSwitch = new DigitalInput(Mappings.topLimitSwitchChannel);
    private DigitalInput bottomLimitSwitch = new DigitalInput(Mappings.bottomLimitSwitchChannel);

    private CANSparkMax wrist = new CANSparkMax(Mappings.wristMotorChannel, MotorType.kBrushed);

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualWristCommand());
    }

    public void wristPower(double power) {
        if (topLimitSwitch.get() && power > 0) {
            wrist.set(0);
        } else if (bottomLimitSwitch.get() && power < 0) {
            wrist.set(0);
        } else {
            wrist.set(.8 * power);
        }
    }
  
}

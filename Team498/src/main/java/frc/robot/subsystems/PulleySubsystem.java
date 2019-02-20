/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ManualPulleyCommand;
import edu.wpi.first.wpilibj.Victor;

public class PulleySubsystem extends Subsystem {

    private static final int leftPulleyMotorChannel = 0;
    private static final int rightPulleyMotorChannel = 1;

    private Victor leftPulley = new Victor(leftPulleyMotorChannel);
    private Victor rightPulley = new Victor(rightPulleyMotorChannel);

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualPulleyCommand());
    }

    public void setPulleyPower(double power) {
        leftPulley.set(-power);
        rightPulley.set(power);
    }
    
    //adds an overload
    public void setPulleyPower(double leftPower, double rightPower) {
        leftPulley.set(-leftPower);
        rightPulley.set(rightPower);
    }
}

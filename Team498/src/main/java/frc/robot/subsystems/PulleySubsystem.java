/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ManualPulleyCommand;
import edu.wpi.first.wpilibj.Spark;

public class PulleySubsystem extends Subsystem {

    private static final int pulleyMotorChannel = 5;

    private Spark pulley = new Spark(pulleyMotorChannel);

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualPulleyCommand());
    }

    public void setPulleyPower(double power) {
        pulley.set(power);
    }

}

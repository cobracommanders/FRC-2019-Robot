/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleClawCommand;
import frc.robot.commands.ReleaseRampCommand;
import frc.robot.commands.ToggleIntakeCommand;

public class Operator {

    public Operator() {
        Robot.controller2.buttonB.whenPressed(new ToggleIntakeCommand(1, 1));
        Robot.controller2.buttonX.whenPressed(new ToggleIntakeCommand(-.4, -.4));
        Robot.controller1.buttonA.whenPressed(new ToggleClawCommand());
        Robot.controller2.bothJoyPresses.whenActive(new ReleaseRampCommand());
    }

}

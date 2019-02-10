/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleClawCommand;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.ToggleClampCommand;

public class Operator {

    public Operator() {
        Robot.controller.start.whenPressed(new ToggleClampCommand());
        Robot.controller.buttonB.whenPressed(new ToggleIntakeCommand(.8, .8));
        Robot.controller.buttonX.whenPressed(new ToggleIntakeCommand(-.8, -.8));
        Robot.controller.buttonA.whenPressed(new ToggleClawCommand());
    }

}

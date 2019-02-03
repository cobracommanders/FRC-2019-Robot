/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.configurations.ControllerConfiguration;
import frc.robot.commands.ToggleClaw;
import frc.robot.commands.ToggleClamp;

public class Operator {

    //instantiate one or more controllers here
    public Controller controller = new Controller(ControllerConfiguration.ControllerPort);

    public Operator() {

        controller.buttonB.whenPressed(new ToggleIntakeCommand(.8, .8));
        controller.buttonX.whenPressed(new ToggleIntakeCommand(-.8, -.8));

        controller.buttonA.whenPressed(new ToggleClaw());
        controller.start.whenPressed(new ToggleClamp());
        
        
    }


}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.ToggleClampCommand;

public class Operator {

    //instantiate one or more controllers here
    public Controller controller = new Controller(Mappings.ControllerPort);

    public Operator() {
        controller.start.whenPressed(new ToggleClampCommand());

        controller.buttonB.whenPressed(new ToggleIntakeCommand(.8, .8));
        controller.buttonX.whenPressed(new ToggleIntakeCommand(-.8, -.8));
        
        
    }


}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleIntake;

public class Operator {

    private static Operator operator = null;
    
    public static Operator getOperator() {
        operator = operator == null ? new Operator() : operator;
        return operator;
    }

    //instantiate one or more controllers here
    public Controller controller = new Controller(Mappings.ControllerPort);

    public Operator() {

        controller.buttonB.whenPressed(new ToggleIntake(1, 1));
        controller.buttonX.whenPressed(new ToggleIntake(-1, -1));
        
    }


}

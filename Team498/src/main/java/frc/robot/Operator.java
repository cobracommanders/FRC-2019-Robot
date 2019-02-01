/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleClaw;

public class Operator {

    private static Operator operator = null;
    
    public static Operator getOperator() {
        operator = operator == null ? new Operator() : operator;
        return operator;
    }

    //instantiate one or more controllers here
    public Controller controller = new Controller(ControllerConfiguration.ControllerPort);

    public Operator() {

        controller.buttonA.whenPressed(new ToggleClaw());
        
    }


}

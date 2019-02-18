/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.PanelIntakeCommand;
import frc.robot.commands.PanelOuttakeCommand;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.ToggleSlowmodeCommand;

public class Operator {

    public Operator() {
        
        Robot.operatorController.buttonB.whenPressed(new ToggleIntakeCommand(1, 1));
        Robot.operatorController.buttonX.whenPressed(new ToggleIntakeCommand(-.4, -.4));
        Robot.driverController.rightBumper.whenPressed(new PanelIntakeCommand());
        Robot.driverController.leftBumper.whenPressed(new PanelOuttakeCommand());
        //Robot.driverController.rightBumper.whenPressed(new ToggleSlowmodeCommand());
    

    }

}

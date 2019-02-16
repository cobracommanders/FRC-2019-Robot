/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.ToggleClawCommand;
import frc.robot.commands.ToggleIntakeCommand;
import frc.robot.commands.ToggleSlowmodeCommand;
import frc.robot.commands.ReverseDriveCommand;

public class Operator {

    public Operator() {
        
        Robot.operatorController.buttonB.whenPressed(new ToggleIntakeCommand(1, 1));
        Robot.operatorController.buttonX.whenPressed(new ToggleIntakeCommand(-.4, -.4));
        Robot.driverController.buttonA.whenPressed(new ToggleClawCommand());
        Robot.driverController.rightBumper.whenPressed(new ToggleSlowmodeCommand());
        Robot.controller1.buttonY.whenPressed(new ReverseDriveCommand());
    }

}

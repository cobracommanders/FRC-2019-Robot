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
import frc.robot.commands.ToggleClampCommand;
import frc.robot.commands.ReleaseClampCommand;
import frc.robot.AutoStrategies.LeftAutoStrategy;
import frc.robot.AutoStrategies.RightAutoStrategy;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.commands.DefenseModeCommand;

public class Operator {

    public Operator() {

        Robot.driverController.back.whenPressed(new ReleaseClampCommand());
        Robot.driverController.start.whenPressed(new ToggleClampCommand());

        Robot.operatorController.buttonB.whenPressed(new ToggleIntakeCommand(1, 1));
        Robot.operatorController.buttonX.whenPressed(new ToggleIntakeCommand(-.4, -.4));

        Robot.driverController.rightBumper.whenPressed(new PanelIntakeCommand());
        Robot.driverController.leftBumper.whenPressed(new PanelOuttakeCommand());
        Robot.driverController.buttonX.whenPressed(new DefenseModeCommand());

        // Robot.driverController.rightBumper.whenPressed(new ToggleSlowmodeCommand());

        Robot.driverController.buttonA.whenPressed(new LeftAutoStrategy());
        Robot.driverController.buttonY.whenPressed(new RightAutoStrategy());

    }

}

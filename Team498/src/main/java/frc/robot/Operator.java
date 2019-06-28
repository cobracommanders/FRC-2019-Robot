/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.commands.PanelIntakeCommand;
import frc.robot.commands.PanelOuttakeCommand;
import frc.robot.commands.ToggleClampCommand;
import frc.robot.commands.ReleaseClampCommand;
import frc.robot.AutoStrategies.LeftAutoStrategy;
import frc.robot.AutoStrategies.RightAutoStrategy;
import frc.robot.AutoStrategies.AutoCommands.AutoDriveCommand;
import frc.robot.commands.DefenseModeCommand;
import frc.robot.commands.IntakeWristPositionCommand;
import frc.robot.commands.AutoWristCommand;
import frc.robot.commands.CargoShipWristPositionCommand;

public class Operator {

    public Operator() {

        Robot.operatorController.back.whenPressed(new ReleaseClampCommand());
        Robot.operatorController.start.whenPressed(new ToggleClampCommand());

        Robot.operatorController.buttonA.whenPressed(new AutoDriveCommand(0.5, 96));

        //Robot.operatorController.leftBumper.whenPressed(new AutoWristCommand(true));
        //Robot.operatorController.rightBumper.whenPressed(new AutoWristCommand(false));

        Robot.driverController.leftBumper.whenPressed(new PanelIntakeCommand());
        Robot.driverController.rightBumper.whenPressed(new PanelOuttakeCommand());

        Robot.driverController.buttonX.whenPressed(new DefenseModeCommand());

        //Robot.driverController.buttonA.whenPressed(new LeftAutoStrategy());
        //Robot.driverController.buttonY.whenPressed(new RightAutoStrategy());

        //Robot.operatorController.buttonA.whenPressed(new CargoShipWristPositionCommand());
        //Robot.operatorController.buttonY.whenPressed(new IntakeWristPositionCommand());
    }

}

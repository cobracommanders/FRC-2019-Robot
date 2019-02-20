/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class PanelIntakeCommand extends InstantCommand {

    public PanelIntakeCommand() {
        super("PanelIntakeCommand");
        requires(Robot.panelIntake);
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        Robot.panelIntake.setGrip(true);
    }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoStrategies;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutoStrategies.AutoCommands.AutoDriveCommand;
import frc.robot.AutoStrategies.AutoCommands.AutoTurnCommand;
import frc.robot.commands.PanelOuttakeCommand;

public class RightAutoStrategy extends CommandGroup {

    public RightAutoStrategy() {
        // start next to the cargo
        // 219.25 inches away from cargo ship
        // 47.88 inches to the right of the cargo ship
        addSequential(new AutoDriveCommand(.8, 100)); // 80% power, drives forward 100 inches
        addSequential(new AutoTurnCommand(-90)); // turns 90 degrees
        addSequential(new AutoDriveCommand(.8, 47.88)); // 80% power, drives forward 47.88 inches
        addSequential(new AutoTurnCommand(90)); // turns back to facing cargo ship
        addSequential(new AutoDriveCommand(.8, 119.25)); // 80% power, drives forward 119.25 inches
        addSequential(new PanelOuttakeCommand());
    }
}

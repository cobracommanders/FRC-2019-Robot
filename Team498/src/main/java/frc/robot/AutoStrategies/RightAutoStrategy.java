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
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.AutoStrategies.AutoCommands.AutoIntakeCommand;
import frc.robot.AutoStrategies.AutoCommands.AutoTimedIntakeCommand;
import frc.robot.AutoStrategies.AutoCommands.AutoOldTurnCommand;
//import frc.robot.commands.PanelOuttakeCommand;
import frc.robot.AutoStrategies.AutoCommands.AutoTimedDriveCommand;

public class RightAutoStrategy extends CommandGroup {

    public RightAutoStrategy() {
        /*
        if (DriverStation.getInstance().isAutonomous()) {
            // 123.97 inches away from the loading station
            addSequential(new AutoDriveCommand(-.8, 62)); // drive backward from cargo ship 62 inches about halfway to
                                                          // loading station
            addSequential(new AutoTurnCommand(90)); // turns right
            addSequential(new AutoDriveCommand(.8, 117.47)); // drives forward
            addSequential(new AutoTurnCommand(90)); // turns right
            addSequential(new AutoDriveCommand(.8, 61.97)); // drive to loading station
        }
        */
        addSequential(new AutoDriveCommand(-.8, 225));
        addSequential(new AutoOldTurnCommand(.8, -90));
        addSequential(new AutoTimedDriveCommand(-.8, 0, 2));
        addSequential(new AutoTimedIntakeCommand(.7));
    }
}

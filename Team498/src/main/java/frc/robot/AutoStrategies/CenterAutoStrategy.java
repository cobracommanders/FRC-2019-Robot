/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoStrategies;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.AutoStrategies.AutoCommands.AutoDriveCommand;
import frc.robot.commands.PanelOuttakeCommand;

public class CenterAutoStrategy extends CommandGroup {
  
  public CenterAutoStrategy() {
    addSequential(new AutoDriveCommand(.8, 172.25)); //80% power and 172.25 inches 
    addSequential(new PanelOuttakeCommand()); //place panels
  }
}

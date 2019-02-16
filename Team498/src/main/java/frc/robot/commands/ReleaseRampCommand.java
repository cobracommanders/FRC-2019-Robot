/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ReleaseRampCommand extends InstantCommand {

  public ReleaseRampCommand() {
    super("ReleaseRampCommand");
    requires(Robot.ramp);
  }

  @Override
  protected void initialize() {
    Robot.ramp.releaseRamp();
        try {
            this.wait((long) 5000);
        } catch (Exception e) {
            // Swallow the exception
        } 
    Robot.ramp.resetRamp();

  }

}

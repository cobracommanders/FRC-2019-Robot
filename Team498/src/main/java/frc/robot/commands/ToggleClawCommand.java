/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ToggleClawCommand extends InstantCommand {

  public boolean clawUp = false;

  public ToggleClawCommand() {
    super("ToggleClaw");
    requires(Robot.claw);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.clawUp = !clawUp;
    Robot.claw.setClaw(clawUp);

    /*
     * added a wait for 1/10 of a second. It needed to wait before settign claw to
     * off. had to use a try/catch for it to work
     */
    try {
      wait(100);
    } catch (InterruptedException e) {
    }
    Robot.claw.turnClawOff();
  }

}

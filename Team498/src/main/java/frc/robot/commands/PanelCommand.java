/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;

public class PanelCommand extends Command {

    public boolean intake = false;
    boolean finished = false;
    Timer timer;

    public PanelCommand() {
        super("PanelCommand");
        requires(Robot.panelIntake);
        timer = new Timer();
    }

    // Called once when the command executes
    @Override
    protected void initialize() {
        this.intake = !intake;
        timer.reset();
        timer.start();
    }

    @Override
    protected void execute() {
        if (intake) {
            double t = timer.get();
            if (t < 0.1)
                Robot.panelIntake.setGrip(true);
            if (timer.get() > 0.1) {
                Robot.panelIntake.turnGripOff();
                finished = true;
            }
        } else {
            double t = timer.get();
            if (t < 0.2)
                Robot.panelIntake.setGrip(false);
            if (t > 0.2 && t < 0.4) {
                Robot.panelIntake.turnGripOff();
                Robot.panelIntake.setPush(true);
            }
            if (t > 0.4 && t < 0.5)
                Robot.panelIntake.setPush(false);
            if (t > 0.5) {
                Robot.panelIntake.turnPushOff();
                finished = true;
            }
        }

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualIntakeCommand extends Command {

    public ManualIntakeCommand() {
        super("ManualIntakeCommand");
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        double power = Robot.driverController.axisRightTrigger.getAxisValue()- Robot.operatorController.axisLeftTrigger.getAxisValue();
        if (Robot.operatorController.axisRightTrigger.getAxisValue() > .1) {
            Robot.intake.setIntake(.4, .4);
        } else if (power > .1) {
            Robot.intake.setIntake(.69, .69); // Rocket is .38
        } else if (power < -.1) {
            Robot.intake.setIntake(-.5, -.5);
        } else {
            Robot.intake.setIntake(0, 0);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}

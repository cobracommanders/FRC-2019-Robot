/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleIntakeCommand extends Command {

    private double leftPower;
    private double rightPower;

    public ToggleIntakeCommand(double leftPower, double rightPower) {
        super("ToggleIntakeCommand");
        this.leftPower = leftPower;
        this.rightPower = rightPower;
        requires(Robot.intake);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if (Robot.intake.getLastLeft() == leftPower) {
            Robot.intake.setIntake(0, 0);
        } else {
            Robot.intake.setIntake(leftPower, rightPower);
        }
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}

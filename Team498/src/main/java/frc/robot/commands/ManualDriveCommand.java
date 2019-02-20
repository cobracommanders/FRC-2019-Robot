/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualDriveCommand extends Command {

    public static boolean slowMode = false;

    public ManualDriveCommand() {
        super("ManualDriveCommand");
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double move = Robot.driverController.axisLeftY.getAxisValue();
        double turn = Robot.driverController.axisRightX.getAxisValue();

        /*
         * if slowmode == true, move is 75%, otherwise normal. if slowmode == true, turn
         * is 60% otherwise turn
         */
        Robot.drivetrain.drive(slowMode ? move * 0.75 : move, slowMode ? turn * 0.6 : turn);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.drive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}

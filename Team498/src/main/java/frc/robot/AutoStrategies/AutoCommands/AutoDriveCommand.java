/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoStrategies.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoDriveCommand extends Command {

    private double moveValue;
    private double desiredDistance;

    public AutoDriveCommand(double moveValue, double desiredDistance) {
        super("AutoDriveCommand");

        requires(Robot.drivetrain);

        this.moveValue = moveValue;
        this.desiredDistance = desiredDistance;
    }

    protected void initialize() {
        Robot.drivetrain.setCap(moveValue);
        Robot.drivetrain.resetEncoders();
        Robot.drivetrain.resetGyro();
        Robot.drivetrain.setSetpoint0(0);
        Robot.drivetrain.setSetpoint1(desiredDistance);
        Robot.drivetrain.enable0();
        Robot.drivetrain.enable1();
    }

    protected void execute() {
        //Robot.drivetrain.autoDrive(moveValue, 0);
    }

    protected boolean isFinished() {
        return Robot.drivetrain.onTarget0() && Robot.drivetrain.onTarget1();
        //return Math.abs(Robot.drivetrain.getDistance()) >= Math.abs(desiredDistance);
    }

    protected void end() {
        Robot.drivetrain.autoDrive(0, 0);
        Robot.drivetrain.disable0();
        Robot.drivetrain.disable1();
    }

    protected void interrupted() {
        end();
    }
}
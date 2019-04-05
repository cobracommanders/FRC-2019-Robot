/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.AutoStrategies.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 *
 */
public class AutoOldTurnCommand extends Command {
	public double gyroGoal;
	public double turnPower;

	private Timer timer = new Timer();

	public AutoOldTurnCommand(double turnPower, double gyroGoal) {
		super("AutoOldTurnCommand");
		requires(Robot.drivetrain);

		this.gyroGoal = gyroGoal;
		this.turnPower = turnPower;
	}

	protected void initialize() {
		Robot.drivetrain.resetGyro();
	}

	protected void execute() {
		if(Robot.drivetrain.getAngle() > gyroGoal * .8) { 
			Robot.drivetrain.autoDrive(0, turnPower * .4);
		} else if(Robot.drivetrain.getAngle() > gyroGoal * .9) {
			Robot.drivetrain.autoDrive(0, turnPower * .2);
		} else {
			Robot.drivetrain.autoDrive(0, turnPower * .8);
		}
	}

	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getAngle()) >= Math.abs(gyroGoal);
	}

	protected void end() {
		Robot.drivetrain.autoDrive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
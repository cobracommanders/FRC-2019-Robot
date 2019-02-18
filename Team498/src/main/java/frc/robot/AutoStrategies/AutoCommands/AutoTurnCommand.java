package frc.robot.AutoStrategies.AutoCommands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoTurnCommand extends Command {
	public double gyroGoal;
	public double turnPower;

	public AutoTurnCommand(double turnPower, double gyroGoal) {
		super("AutoTurnCommand");
        requires(Robot.drivetrain);
        
		this.gyroGoal = gyroGoal;
		this.turnPower = turnPower;
	}

	protected void initialize() {
		Robot.drivetrain.resetGyro();
	}

	protected void execute() {
		if(Robot.drivetrain.getAngleX() > gyroGoal * .8) { 
			Robot.drivetrain.drive(0, turnPower * .5);
		} else if(Robot.drivetrain.getAngleX() > gyroGoal * .9) {
			Robot.drivetrain.drive(0, turnPower * .25);
		} else {
			Robot.drivetrain.drive(0, turnPower);
		}
	}

	protected boolean isFinished() {
		return Math.abs(Robot.drivetrain.getAngleX()) >= Math.abs(gyroGoal);
	}

	protected void end() {
		Robot.drivetrain.drive(0, 0);
	}

	protected void interrupted() {
		end();
	}
}
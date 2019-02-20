package frc.robot.AutoStrategies.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class AutoTurnCommand extends Command {
    public double gyroGoal;
    public double turnPower;
    public double tolerance = .01;
    private Timer timer;

    public AutoTurnCommand(double gyroGoal) {
        super("AutoTurnCommand");
        requires(Robot.drivetrain);

        this.gyroGoal = gyroGoal;

        timer = new Timer();

    }

    protected void initialize() {
        Robot.drivetrain.getPIDController().enable();
        Robot.drivetrain.resetGyro();
        timer.reset();
    }

    protected void execute() {
        Robot.drivetrain.getPIDController().setSetpoint(gyroGoal);
    }

    protected boolean isFinished() {
        boolean isOnTarget = Robot.drivetrain.getPIDController().onTarget();
        if (isOnTarget && timer.get() == 0) {
            timer.start();
        }
        if (isOnTarget && timer.get() > 0.5) {
            return true;
        }

        if (!isOnTarget && timer.get() > 0.5) {
            timer.stop();
            timer.reset();
        }
        return false;
    }

    protected void end() {
        Robot.drivetrain.getPIDController().disable();
    }

    protected void interrupted() {
        end();
    }
}
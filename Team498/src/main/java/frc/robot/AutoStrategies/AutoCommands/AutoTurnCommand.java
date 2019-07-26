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
        Robot.drivetrain.enable0();
        Robot.drivetrain.disable1();
        Robot.drivetrain.resetGyro();
        timer.reset();
    }

    protected void execute() {
        Robot.drivetrain.setSetpoint0(gyroGoal);
    }

    protected boolean isFinished() {
        boolean isOnTarget = Robot.drivetrain.onTarget0();
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
        Robot.drivetrain.disable0();
    }

    protected void interrupted() {
        end();
    }
}
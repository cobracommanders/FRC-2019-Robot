/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.ManualIntakeCommand;

public class IntakeSubsystem extends Subsystem {

    private static final int intakeLeftMotorChannel = 4;
    private static final int intakeRightMotorChannel = 5;

    private WPI_VictorSPX intakeLeft = new WPI_VictorSPX(intakeLeftMotorChannel);
    private WPI_VictorSPX intakeRight = new WPI_VictorSPX(intakeRightMotorChannel);

    public double lastLeft = 0;
    private double lastRight = 0;

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new ManualIntakeCommand());
    }

    public void setIntake(double leftPower, double rightPower) {
        intakeLeft.set(-leftPower);
        intakeRight.set(rightPower);
        lastLeft = -leftPower;
        lastRight = rightPower;
    }

    public double getLastLeft() {
        return lastLeft;
    }

    public double getLastRight() {
        return -lastRight;
    }
}

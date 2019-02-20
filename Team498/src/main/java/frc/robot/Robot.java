/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PanelSubsystem;
import frc.robot.subsystems.PulleySubsystem;
import frc.robot.subsystems.ClampSubsystem;
import edu.wpi.first.wpilibj.DriverStation;

public class Robot extends TimedRobot {

    Command m_autonomousCommand;
    SendableChooser<Command> m_chooser = new SendableChooser<>();

    // Controls
    public static DriverStation driverstation = DriverStation.getInstance();
  public static ClampSubsystem clamp = new ClampSubsystem();

    // instantiate one or more controllers here
    public static Controller driverController = new Controller(ControllerConfiguration.ControllerPort1);
    public static Controller operatorController = new Controller(ControllerConfiguration.ControllerPort2);

    // Subsystems
    public static DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    public static IntakeSubsystem intake = new IntakeSubsystem();
    public static WristSubsystem wrist = new WristSubsystem();
    public static PanelSubsystem panelIntake = new PanelSubsystem();
    public static PulleySubsystem pulley = new PulleySubsystem();

    public static Operator operator = new Operator();

    @Override
    public void robotInit() {
        SmartDashboard.putData("Auto mode", m_chooser);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_chooser.getSelected();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {

        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    @Override
    public void testPeriodic() {
    }

    public void updateDashboard() {
        wrist.updateDashboard();
        panelIntake.updateDashboard();
    }
}

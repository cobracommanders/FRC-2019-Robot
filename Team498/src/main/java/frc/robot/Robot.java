/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.WristSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PanelSubsystem;
import frc.robot.subsystems.PulleySubsystem;
import frc.robot.subsystems.VacuumSubsystem;
import frc.robot.subsystems.VisionSubsystem;
import frc.robot.subsystems.ClampSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.AutoStrategies.CenterAutoStrategy;
import frc.robot.AutoStrategies.LeftAutoStrategy;
import frc.robot.AutoStrategies.RightAutoStrategy;
import frc.robot.AutoStrategies.RobotStartPosition;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {

    SendableChooser<RobotStartPosition> chooserPosition = new SendableChooser<>();
    CommandGroup autonomousCommand;
    RobotStartPosition autonomousPosition;

    // Controls
    public static DriverStation driverstation = DriverStation.getInstance();

    // instantiate one or more controllers here
    public static Controller driverController = new Controller(ControllerConfiguration.ControllerPort1);
    public static Controller operatorController = new Controller(ControllerConfiguration.ControllerPort2);

    // Subsystems
    public static DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    public static IntakeSubsystem intake = new IntakeSubsystem();
    public static WristSubsystem wrist = new WristSubsystem();
    public static PanelSubsystem panelIntake = new PanelSubsystem();
    public static PulleySubsystem pulley = new PulleySubsystem();
    public static ClampSubsystem clamp = new ClampSubsystem();
    public static VisionSubsystem vision = new VisionSubsystem();
    public static VacuumSubsystem vacuum = new VacuumSubsystem();

    public static Operator operator = new Operator();
    @Override
    public void robotInit() {
        vision.startCapture();
        addAutonomousChoices();
    }

    @Override
    public void robotPeriodic() {
        updateDashboard();
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
        drivetrain.resetEncoders();
        wrist.resetEncoder();
    
        autonomousPosition = chooserPosition.getSelected();
        if (autonomousPosition == RobotStartPosition.LEFT) {
            autonomousCommand = new LeftAutoStrategy();
            autonomousCommand.start();
        } else if (autonomousPosition == RobotStartPosition.CENTER) {
            autonomousCommand = new CenterAutoStrategy();
            autonomousCommand.start();
        } else if (autonomousPosition == RobotStartPosition.RIGHT) {
            autonomousCommand = new RightAutoStrategy();
            autonomousCommand.start();
        } else if (autonomousPosition == RobotStartPosition.FULLSEND) {
        }
        
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        drivetrain.resetGyro();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboard();
        
    }

    @Override
    public void testPeriodic() {

    }

    private void addAutonomousChoices() {
        chooserPosition.addDefault("Robot in: LEFT", RobotStartPosition.LEFT);
        chooserPosition.addObject("Robot in: CENTER", RobotStartPosition.CENTER);
        chooserPosition.addObject("Robot in: RIGHT", RobotStartPosition.RIGHT);
        chooserPosition.addObject("Robot in: FULL SEND", RobotStartPosition.FULLSEND);
    }
    
    public void updateDashboard() {
        SmartDashboard.putData("Autonomous Position", chooserPosition);
        SmartDashboard.putString("Position Choice", autonomousPosition != null ? autonomousPosition.toString() : "");
        wrist.updateDashboard();
        panelIntake.updateDashboard();
        vacuum.updateDashboard();
    }
}

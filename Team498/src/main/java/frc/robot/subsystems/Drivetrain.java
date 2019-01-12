/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Drivetrain extends Subsystem {


  private static Drivetrain drivetrain = null;

  public static Drivetrain getDrivetrain() {
    drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
    return drivetrain;
  }


  // TODO: may need to change the motor controllers and will need to change the motor channels.
  private Spark frontLeftDrive = new Spark(0);
  private Spark frontRightDrive = new Spark(0);
  private Spark backLeftDrive = new Spark(0);
  private Spark backRightDrive = new Spark(0);

  private SpeedControllerGroup leftGroup = new SpeedControllerGroup(frontLeftDrive, backLeftDrive);
  private SpeedControllerGroup rightGroup = new SpeedControllerGroup(frontRightDrive, backRightDrive);

  private DifferentialDrive drive = new DifferentialDrive(leftGroup, rightGroup);



  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new ManualDrive());
  }

  public void drive(double move, double turn) {
    drive.arcadeDrive(move, turn);
  }

}

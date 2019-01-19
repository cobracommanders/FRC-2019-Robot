/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Mappings;
import frc.robot.commands.ManualDrive;



public class Drivetrain extends Subsystem {


  private static Drivetrain drivetrain = null;

  public static Drivetrain getDrivetrain() {
    drivetrain = drivetrain == null ? new Drivetrain() : drivetrain;
    return drivetrain;
  }


  // TODO:will need to change the motor channels.
  private WPI_TalonSRX frontLeftDrive = new WPI_TalonSRX(Mappings.frontLeftDriveMotorChannel);
  private WPI_TalonSRX frontRightDrive = new WPI_TalonSRX(Mappings.frontRightDriveMotorChannel);
  private WPI_TalonSRX backLeftDrive = new WPI_TalonSRX(Mappings.backLeftDriveMotorChannel);
  private WPI_TalonSRX backRightDrive = new WPI_TalonSRX(Mappings.backRightDriveMotorChannel);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ManualDrive());
  }

  public void drive(double move, double turn) {
    //arcade drive made manually due to talon SRX not able to be used in differential drive 
    frontLeftDrive.set( (move + turn) * .8 );
    frontRightDrive.set( (move - turn) * .8 );
    backLeftDrive.set( (move - turn)  * .8 );
    backRightDrive.set( (move - turn) * .8 );
  }

}

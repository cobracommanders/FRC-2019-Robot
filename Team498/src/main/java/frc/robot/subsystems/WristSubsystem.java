/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Mappings;
import frc.robot.commands.ManualWristCommand;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Add your docs here.
 */
public class WristSubsystem extends Subsystem {

  //TODO: Need to change motor control channel :3
  private WPI_VictorSPX wrist = new WPI_VictorSPX(Mappings.wristMotorChannel);


  @Override
  public void initDefaultCommand() {
     setDefaultCommand(new ManualWristCommand());
  }

  public void wristPower(double power) {
    wrist.set(power);
  }

  
}

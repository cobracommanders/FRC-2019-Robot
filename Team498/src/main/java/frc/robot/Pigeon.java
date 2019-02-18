/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.time.Instant;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

/**
 * Add your docs here.
 */
public class Pigeon {
    private PigeonIMU gyro;

    public Pigeon(int deviceNumber) {
        gyro = new PigeonIMU(deviceNumber);
    }

    public Pigeon(WPI_TalonSRX talon) {
        gyro = new PigeonIMU(talon);
    }

    public double getAngle() {
        double[] d = new double[3];
        gyro.getYawPitchRoll(d);
        return d[0];
    }

    public void resetPosition() {
        gyro.setYaw(0);
    }
}

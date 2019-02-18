/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

/**
 * Wrapper for the PigeonIMU, since it doesn't have easy access to each angle
 * and it doesn't have a reset
 */
public class Pigeon {
    private PigeonIMU gyro;
    private double[] lastState; // x[0], y[1], z[2]

    public Pigeon(int deviceNumber) {
        gyro = new PigeonIMU(deviceNumber);
        lastState = new double[3];
        /*
         * getAccumGyro uses it's double[] input as an output for the angles, since the
         * actual function returns an error code for other purposes we don't use
         */
        gyro.getAccumGyro(lastState);
    }

    public Pigeon(WPI_TalonSRX talon) {
        gyro = new PigeonIMU(talon);
        lastState = new double[3];
        gyro.getAccumGyro(lastState); // Makes it so it defaults to zero
    }

    public double getAngleX() {
        double[] d = new double[3];
        gyro.getAccumGyro(d);
        return d[0] - lastState[0];
    }

    public double getAngleY() {
        double[] d = new double[3];
        gyro.getAccumGyro(d);
        return d[1] - lastState[1];
    }

    public double getAngleZ() {
        double[] d = new double[3];
        gyro.getAccumGyro(d);
        return d[2] - lastState[2];
    }

    public void reset() {
        gyro.getRawGyro(lastState);
    }
}

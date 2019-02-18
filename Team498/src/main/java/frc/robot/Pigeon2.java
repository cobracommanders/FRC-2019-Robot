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
public class Pigeon2 {
    private PigeonIMU gyro;
    private Thread updateThread;
    private Updater updateRunnable;
    private double[] currentPosition;
    private double[] currentVelocity;
    private Instant previous;

    public Pigeon2(int deviceNumber) {
        gyro = new PigeonIMU(deviceNumber);
        currentPosition = new double[] { 0, 0, 0 };
        currentVelocity = new double[3];
        gyro.getRawGyro(currentVelocity);
        updateRunnable = new Updater();
        updateThread = new Thread(updateRunnable);
    }

    public Pigeon2(WPI_TalonSRX talon) {
        gyro = new PigeonIMU(talon);
        currentPosition = new double[] { 0, 0, 0 };
        currentVelocity = new double[3];
        gyro.getRawGyro(currentVelocity);
    }

    public double getAngleXPosition() {
        return currentPosition[0];
    }

    public double getAngleYPosition() {
        return currentPosition[1];
    }

    public double getAngleZPosition() {
        return currentPosition[2];
    }

    public double geAngleXVelocity() {
        return currentVelocity[0];
    }

    public double getAngleYVelocty() {
        return currentVelocity[1];
    }

    public double getAngleZVelocity() {
        return currentVelocity[2];
    }

    public void resetPosition() {
        currentPosition = new double[] { 0, 0, 0 };
    }

    public void update() {
        Instant now = Instant.now();
        gyro.getRawGyro(currentVelocity);
        double t = (now.getNano() / 1.0e9) - (previous.getNano() / 1.0e9);
        currentPosition[0] += currentVelocity[0] * t;
        currentPosition[1] += currentVelocity[1] * t;
        currentPosition[2] += currentVelocity[2] * t;
    }

    public void start() {
        
    }

    public void stop() {
        
    }

    private class Updater implements Runnable {

        private volatile boolean running;

        @Override
        public void run() {
            for(;;) {
            if(running)
                update();
            }
        }
    }
}

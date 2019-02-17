/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.time.Instant;

/**
 * Add your docs here.
 */
public class ConstantAccelerationCalculator3 {

    boolean init = false;

    private double current = 0;
    private double m; // Greatest motor change allowed between clock values
    private double joy1 = 0; // The last joystick value from the previous frame
    private double tolerance;
    private double sentValue;

    public ConstantAccelerationCalculator3(double m) { // M is the maximum amount the drivetrain can change per clock
                                                       // cycle (About 20ms)
        this.m = m; // an m of .1 would make it so the drivetrain can recieve full motor values
                    // after 10 clock cycles.
    }

    public double NextDataPoint(double joy) {
        if (Math.abs(joy - joy1) > m) { // If the value requested of the motors is greater than the m, then the motor
                                        // value will only change by m percent until the requested value is less than m
                                        // of the current value
            if (joy < joy1) {
                sentValue = sentValue - m;
            } else if (joy > joy1) {
                sentValue = sentValue + m;
            }
        } else {
            sentValue = joy;
        }
        joy1 = joy;
        return sentValue;
    }
}

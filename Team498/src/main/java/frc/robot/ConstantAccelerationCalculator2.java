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
public class ConstantAccelerationCalculator2 {

    boolean init = false;

    private double current = 0;
    private double c;
    private Instant prev;
    private double tolerance;


    public ConstantAccelerationCalculator2(double c, double tolerance) {
        this.c = c;
        this.tolerance = tolerance;
    }

    public double NextDataPoint(double target) {

        if(Math.abs(target - current) < tolerance) {
            current = target;
            return current;
        }
        Instant now = Instant.now();
        if (!init) {
            init = true;
            prev = now;
            return 0;
        }
        //converting from nano seconds to seconds
        double t = (now.getNano() / 1.0e9) - (prev.getNano() / 1.0e9);
        double diff = ((target - current) * c * t);
        if (Math.abs(diff) > Math.abs(target - current))
            diff = target - current;
        current += diff;

        System.out.println("Current: " + current); 
        System.out.println("Target: " + target);
        System.out.println("Diff: " + diff);
        System.out.println("Diff in time: " + t); //Should be 0.020

        prev = now;

        return current;
    }
}

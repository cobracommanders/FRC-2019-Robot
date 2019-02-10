package frc.robot;

import java.util.Date;


public class ConstantAccelerationCalculator {
	
	private double tN = 0;
	private double tN1 = 0;
	private double tN2 = 0;
	private double c = 0.02; 
	private double FtN = 0;
	private double FtN1 = 0;
	private double FtN2 = 0;
	
	public ConstantAccelerationCalculator(double targetAcceleration) {
		c = targetAcceleration / 10000;
	}
	
	public double getNextDataPoint(double targetValue) {
	    	long currentTime = new Date().getTime();
			
			tN = (double)currentTime;
			
			if (tN1 != 0 && tN2 != 0) {
				if(FtN1 < targetValue) {
					FtN = (tN-tN1) * ((c * (tN - tN2)) + ((FtN1 - FtN2)/(tN1 - tN2))) + FtN1;
					if(FtN > targetValue) {
						FtN = targetValue;
					}
				} else if (FtN1 > targetValue) {
					// The difference is the negative c
					FtN = (tN-tN1) * ((-c * (tN - tN2)) + ((FtN1 - FtN2)/(tN1 - tN2))) + FtN1;
					if(FtN < targetValue) {
						FtN = targetValue;
					}
				} else {
					FtN = targetValue;
				}
				
			} 
	
			FtN2 = FtN1;
			FtN1 = FtN;
			tN2 = tN1;
			tN1 = tN;

			return FtN;
	}
	
	public void printDebug() {
		System.out.println("c (constant acceleration value): "+ c);
		System.out.println("Difference between two points"+ (FtN1 - FtN2));
		System.out.println("FtN: " + FtN);
		System.out.println("FtN1: " + FtN1);
		System.out.println("FtN2: " + FtN2);
		System.out.println("tN: " + Math.round((tN * 1000)/1000));
		System.out.println("tN1: " + Math.round((tN1 * 1000)/1000));
		System.out.println("tN2: " + Math.round((tN2 * 1000)/1000));
	}
	
}
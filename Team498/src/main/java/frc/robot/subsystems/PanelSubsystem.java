/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
/*
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class PanelSubsystem extends Subsystem {
    private static final int gripReverseChannel = 0;
    private static final int gripForwardChannel = 1;
    private static final int pushForwardChannel = 2;
    private static final int pushReverseChannel = 3;

    public boolean pushed;

    private DoubleSolenoid grip = new DoubleSolenoid(gripForwardChannel, gripReverseChannel);
    private DoubleSolenoid push = new DoubleSolenoid(pushForwardChannel, pushReverseChannel);

    private boolean holdingOnToHatch;

    public PanelSubsystem() {
        super("PanelSubsystem");
        this.grip.set(Value.kForward);
        this.push.set(Value.kForward);
    }

    @Override
    public void initDefaultCommand() {
    }

    public void setGrip(boolean holdingOnToHatch) {
        if (holdingOnToHatch) {
            this.holdingOnToHatch = holdingOnToHatch;
            grip.set(Value.kForward);
        } else {
            this.holdingOnToHatch = holdingOnToHatch;
            grip.set(Value.kReverse);
        }

    }

    public void setPush(boolean isPushed) {
        if (isPushed) {
            this.pushed = isPushed;
            push.set(Value.kReverse);
        } else {
            this.pushed = isPushed;
            push.set(Value.kForward);
        }
    }

    public void turnGripOff() {
        grip.set(Value.kOff);
    }

    public void turnPushOff() {
        push.set(Value.kOff);
    }

    public void updateDashboard() {
        SmartDashboard.putBoolean("Push Value", pushed);
    }
}
*/
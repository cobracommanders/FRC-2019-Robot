/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.commands.PanelCommand;

public class PanelSubsystem extends Subsystem {
    private static final int gripReverseChannel = 0;
    private static final int gripForwardChannel = 1;
    private static final int pushForwardChannel = 2;
    private static final int pushReverseChannel = 3;

    private DoubleSolenoid grip = new DoubleSolenoid(gripForwardChannel, gripReverseChannel);
    private DoubleSolenoid push = new DoubleSolenoid(pushForwardChannel, pushReverseChannel);

    private boolean holdingOnToHatch;

    @Override
    public void initDefaultCommand() {
    }

    public void setGrip(boolean holdingOnToHatch) {

        if (holdingOnToHatch) {
            grip.set(Value.kForward);
        } else {
            grip.set(Value.kReverse);
        }
        this.holdingOnToHatch = holdingOnToHatch;
    }

    public void setPush(boolean isPushed) {
        if (isPushed) {
            push.set(Value.kForward);
        } else {
            push.set(Value.kReverse);
        }
        
    }
    

    public void turnGripOff() {
        grip.set(Value.kOff);
    }

    public void turnPushOff() {
        push.set(Value.kOff); 
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Controller {
    // controller
    private Joystick joystick;

    public JoystickButton buttonA;
    public JoystickButton buttonB;
    public JoystickButton buttonX;
    public JoystickButton buttonY;
    public JoystickButton leftBumper;
    public JoystickButton rightBumper;
    public JoystickButton leftJoyPress;
    public JoystickButton rightJoyPress;
    public JoystickButton start;
    public JoystickButton back;
    public DoubleButton bothJoyPresses;

    public JoystickAxis axisLeftX;
    public JoystickAxis axisRightX;
    public JoystickAxis axisLeftY;
    public JoystickAxis axisRightY;
    public JoystickAxis axisLeftTrigger;
    public JoystickAxis axisRightTrigger;

    public Controller(int port) {

        // controller
        joystick = new Joystick(port);

        // buttons
        buttonA = new JoystickButton(joystick, ControllerConfiguration.ButtonA);
        buttonB = new JoystickButton(joystick, ControllerConfiguration.ButtonB);
        buttonX = new JoystickButton(joystick, ControllerConfiguration.ButtonX);
        buttonY = new JoystickButton(joystick, ControllerConfiguration.ButtonY);
        leftBumper = new JoystickButton(joystick, ControllerConfiguration.LeftBumper);
        rightBumper = new JoystickButton(joystick, ControllerConfiguration.RightBumper);
        leftJoyPress = new JoystickButton(joystick, ControllerConfiguration.LeftJoyPress);
        rightJoyPress = new JoystickButton(joystick, ControllerConfiguration.RightJoyPress);
        start = new JoystickButton(joystick, ControllerConfiguration.ButtonStart);
        back = new JoystickButton(joystick, ControllerConfiguration.ButtonBack);
        bothJoyPresses = new DoubleButton(joystick, ControllerConfiguration.LeftJoyPress,
                ControllerConfiguration.RightJoyPress);

        // Axes
        axisLeftX = new JoystickAxis(joystick, ControllerConfiguration.LeftXAxis, 0.2);
        axisLeftY = new JoystickAxis(joystick, ControllerConfiguration.LeftYAxis, 0);
        axisRightX = new JoystickAxis(joystick, ControllerConfiguration.RightXAxis, 0);
        axisRightY = new JoystickAxis(joystick, ControllerConfiguration.RightYAxis, 0);
        axisLeftTrigger = new JoystickAxis(joystick, ControllerConfiguration.LeftTrigger, 0.2);
        axisRightTrigger = new JoystickAxis(joystick, ControllerConfiguration.RightTrigger, 0);

    }

    public class JoystickAxis {
        // tolerance fixes any issues with the joysticks
        private Joystick joystick;
        private int axis;
        private double tolerance;

        // this is for the joysticks and the triggers
        public JoystickAxis(Joystick joystick, int axis, double tolerance) {
            this.joystick = joystick;
            this.axis = axis;
            this.tolerance = tolerance;
        }

        public double getAxisValue() {
            return Helpers.normalize(joystick.getRawAxis(axis), tolerance);
        }
    }

    public class DoubleButton extends Trigger {
        private Joystick joy;
        private int button1, button2;

        public DoubleButton(Joystick joy, int button1, int button2) {
            this.joy = joy;
            this.button1 = button1;
            this.button2 = button2;
        }

        public boolean get() {
            return joy.getRawButton(button1) && joy.getRawButton(button2);
        }
    }

    public void setRumble(double value) {
        joystick.setRumble(RumbleType.kLeftRumble, value);
        joystick.setRumble(RumbleType.kRightRumble, value);
    }

    public void setRumble(double leftPower, double rightPower) {
        joystick.setRumble(RumbleType.kLeftRumble, leftPower);
        joystick.setRumble(RumbleType.kRightRumble, rightPower);
    }

}

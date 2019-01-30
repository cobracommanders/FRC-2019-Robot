/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controller {
    //controller 
    private Joystick joystick; 

    public JoystickButton buttonA;
    public JoystickButton buttonB;
    public JoystickButton buttonX;
    public JoystickButton buttonY;
    public JoystickButton leftBumper;
    public JoystickButton rightBumper;
    public JoystickButton leftJoyPress;
    public JoystickButton rightJoyPress;

    public JoystickAxis axisLeftX;
    public JoystickAxis axisRightX;
    public JoystickAxis axisLeftY;
    public JoystickAxis axisRightY;
    public JoystickAxis axisLeftTrigger;
    public JoystickAxis axisRightTrigger;

    public Controller(int port) {

        //controller
        joystick = new Joystick(port);

        //buttons 
        buttonA = new JoystickButton(joystick, ControllerConfiguration.ButtonA);
        buttonB = new JoystickButton(joystick, ControllerConfiguration.ButtonB);
        buttonX = new JoystickButton(joystick, ControllerConfiguration.ButtonX);
        buttonY = new JoystickButton(joystick, ControllerConfiguration.ButtonY);
        leftBumper = new JoystickButton(joystick, ControllerConfiguration.LeftBumper);
        rightBumper = new JoystickButton(joystick, ControllerConfiguration.RightBumper);
        leftJoyPress = new JoystickButton(joystick, ControllerConfiguration.LeftJoyPress);
        rightJoyPress = new JoystickButton(joystick, ControllerConfiguration.RightJoyPress);


        //Axes 
        axisLeftX = new JoystickAxis(joystick, ControllerConfiguration.LeftXAxis, 0.25);
        axisLeftY = new JoystickAxis(joystick, ControllerConfiguration.LeftYAxis, 0);
        axisRightX = new JoystickAxis(joystick, ControllerConfiguration.RightXAxis, 0);
        axisRightY = new JoystickAxis(joystick, ControllerConfiguration.RightYAxis, 0);
        axisLeftTrigger = new JoystickAxis(joystick, ControllerConfiguration.LeftTrigger, 0.25);
        axisRightTrigger = new JoystickAxis(joystick, ControllerConfiguration.RightTrigger, 0);

    }

    public class JoystickAxis {
        //tolerance fixes any issues with the joysticks
        private Joystick joystick; 
        private int axis;
        private double tolerance;

        //this is for the joysticks and the triggers 
        public JoystickAxis(Joystick joystick, int axis, double tolerance) {
            this.joystick = joystick;
            this.axis = axis;
            this.tolerance = tolerance;
        }

        public double getAxisValue() {
            return Helpers.normalize(joystick.getRawAxis(axis), tolerance);
        }
    }    


}

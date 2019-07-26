/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public abstract class DoublePIDSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private final PIDController m_controller;
  private final PIDController n_controller;

  private final PIDOutput m_output = this::setPIDOutput0;
  private final PIDOutput n_output = this::setPIDOutput1;

  protected double output0, output1;

  private boolean second = false;

  private final PIDSource m_source = new PIDSource() {
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
      return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
      return returnPIDInput0();
    }
  };

  private final PIDSource n_source = new PIDSource() {
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
      return PIDSourceType.kDisplacement;
    }

    @Override
    public double pidGet() {
      return returnPIDInput1();
    }
  };

  public DoublePIDSubsystem(String name, double p0, double i0, double d0, double p1, double i1, double d1) {
    super(name);
    m_controller = new PIDController(p0, i0, d0, m_source, m_output);
    n_controller = new PIDController(p1, i1, d1, n_source, n_output);
    addChild("PIDController0", m_controller);
    addChild("PIDController", n_controller);
  }
  public DoublePIDSubsystem(String name, double p0, double i0, double d0, double f0, double p1, double i1, double d1, double f1) {
    super(name);
    m_controller = new PIDController(p0, i0, d0, f0, m_source, m_output);
    n_controller = new PIDController(p1, i1, d1, f1, n_source, n_output);
    addChild("PIDController0", m_controller);
    addChild("PIDController1", n_controller);
  }
  public DoublePIDSubsystem(String name, double p0, double i0, double d0, double f0, double period0, double p1, double i1, double d1, double f1, double period1) {
    super(name);
    m_controller = new PIDController(p0, i0, d0, f0, m_source, m_output, period0);
    n_controller = new PIDController(p1, i1, d1, f1, n_source, n_output, period1);
    addChild("PIDController0", m_controller);
    addChild("PIDController1", n_controller);
  }
  public DoublePIDSubsystem(double p0, double i0, double d0, double p1, double i1, double d1) {
    m_controller = new PIDController(p0, i0, d0, m_source, m_output);
    n_controller = new PIDController(p1, i1, d1, n_source, n_output);
    addChild("PIDController0", m_controller);
    addChild("PIDController1", n_controller);
  }
  public DoublePIDSubsystem(double p0, double i0, double d0, double f0, double p1, double i1, double d1, double f1) {
    m_controller = new PIDController(p0, i0, d0, f0, m_source, m_output);
    n_controller = new PIDController(p1, i1, d1, f1, n_source, n_output);
    addChild("PIDController0", m_controller);
    addChild("PIDController1", n_controller);
  }
  public DoublePIDSubsystem(double p0, double i0, double d0, double f0, double period0, double p1, double i1, double d1, double f1, double period1) {
    m_controller = new PIDController(p0, i1, d0, f0, m_source, m_output, period0);
    n_controller = new PIDController(p1, i1, d1, f1, n_source, n_output, period1);
    addChild("PIDController0", m_controller);
    addChild("PIDController1", n_controller);
  }

  public PIDController getPIDController0() {
    return m_controller;
  }
  public PIDController getPIDController1() {
    return n_controller;
  }

  public void setSetpointRelative0(double deltaSetpoint) {
    setSetpoint0(getPosition0() + deltaSetpoint);
  }

  public void setSetpointRelative1(double deltaSetpoint) {
    setSetpoint1(getPosition1() + deltaSetpoint);
  }

  public void setSetpoint0(double setpoint) {
    m_controller.setSetpoint(setpoint);
  }

  public void setSetpoint1(double setpoint) {
    n_controller.setSetpoint(setpoint);
  }

  public double getSetpoint0() {
    return m_controller.getSetpoint();
  }

  public double getSetpoint1() {
    return n_controller.getSetpoint();
  }

  public double getPosition0() {
    return returnPIDInput0();
  }

  public double getPosition1() {
    return returnPIDInput1();
  }

  public void setInputRange0(double min, double max) {
    m_controller.setInputRange(min, max);
  }

  public void setInputRange1(double min, double max) {
    n_controller.setInputRange(min, max);
  }

  public void setOutputRange0(double min, double max) {
    m_controller.setOutputRange(min, max);
  }

  public void setOutputRange1(double min, double max) {
    n_controller.setOutputRange(min, max);
  }

  public void setAbsoluteTolerance0(double t) {
    m_controller.setAbsoluteTolerance(t);
  }

  public void setAbsoluteTolerance1(double t) {
    n_controller.setAbsoluteTolerance(t);
  }

  public void setPercentTolerance0(double p) {
    m_controller.setPercentTolerance(p);
  }

  public void setPercentTolerance1(double p) {
    n_controller.setPercentTolerance(p);
  }

  public boolean onTarget0() {
    return m_controller.onTarget();
  }

  public boolean onTarget1() {
    return n_controller.onTarget();
  }

  protected abstract double returnPIDInput0();
  protected abstract double returnPIDInput1();
  protected abstract void usePIDOutput0(double output);
  protected abstract void usePIDOutput1(double output);
  protected abstract void usePIDOutput(double output0, double output1);

  private void setPIDOutput0(double output) {
    if(n_controller.isEnabled()) {
      output0 = output;
      if(second) {
        usePIDOutput(output0, output1);
        second = false;
      }
      else
        second = true;
    }
    else {
      usePIDOutput0(output);
    }
  }

  private void setPIDOutput1(double output) {
    if(m_controller.isEnabled()) {
      output1 = output;
      if(second) {
        usePIDOutput(output0, output1);
        second = false;
      }
      else
        second = true;
    }
    else
      usePIDOutput1(output);
  }

  public void enable0() {
    m_controller.enable();
  }
  public void enable1() {
    n_controller.enable();
  }
  public void disable0() {
    m_controller.disable();
  }
  public void disable1() {
    n_controller.disable();
  }
}

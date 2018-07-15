package org.usfirst.frc.team8579.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {


    Compressor c = new Compressor(0);

    DoubleSolenoid solenoid = new DoubleSolenoid(0,1);

    public Pneumatics(){
        c.setClosedLoopControl(true);

        solenoid.set(DoubleSolenoid.Value.kOff);

    }
    public void initDefaultCommand(){
    }

    public void cylinderOut(){
        solenoid.set(DoubleSolenoid.Value.kForward);
    }

    public void cylinderIn(){
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }
}
package org.usfirst.frc.team8579.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Pneumatics extends Subsystem {


    Compressor c = new Compressor(0);

    private DoubleSolenoid solenoid1 = new DoubleSolenoid(0,1);
    private DoubleSolenoid solenoid2 = new DoubleSolenoid(2,3);

    public Pneumatics(){
        c.setClosedLoopControl(true);

        solenoid1.set(DoubleSolenoid.Value.kOff);

    }
    public void initDefaultCommand(){
    }

    public void cylinder1Out(){
        solenoid1.set(DoubleSolenoid.Value.kForward);
    }

    public void cylinder1In(){
        solenoid1.set(DoubleSolenoid.Value.kReverse);
    }

    public void cylinder2Out(){
        solenoid2.set(DoubleSolenoid.Value.kForward);
    }

    public void cylinder2In(){
        solenoid2.set(DoubleSolenoid.Value.kReverse);
    }
}
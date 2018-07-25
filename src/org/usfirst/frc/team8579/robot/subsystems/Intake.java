package org.usfirst.frc.team8579.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team8579.robot.commands.StopIntake;

import static org.usfirst.frc.team8579.robot.RobotMap.arm;
import static org.usfirst.frc.team8579.robot.RobotMap.intakeLeft;
import static org.usfirst.frc.team8579.robot.RobotMap.intakeRight;

public class Intake extends Subsystem {


    Compressor c = new Compressor(0);

    private DoubleSolenoid clamper = new DoubleSolenoid(0,1);
    //private DoubleSolenoid solenoid2 = new DoubleSolenoid(2,3);

    //Intake motors
    private VictorSP leftIntake = new VictorSP(intakeLeft);
    private VictorSP rightintake = new VictorSP(intakeRight);

    //arm motor (PG188)
    private Spark armMotor = new Spark(arm);



    public Intake(){
        c.setClosedLoopControl(true);

        clamper.set(DoubleSolenoid.Value.kOff);

    }
    public void initDefaultCommand(){
        setDefaultCommand(new StopIntake());
    }

    public void clamp(){
        clamper.set(DoubleSolenoid.Value.kForward);
    }

    public void letGo(){
        clamper.set(DoubleSolenoid.Value.kReverse);
    }

    public void suckIn(){
        leftIntake.set(0.4);
        rightintake.set(-0.4);
    }

    public void spitOut(){
        leftIntake.set(-0.6);
        rightintake.set(0.6);
    }

    public void stopSpin(){
        leftIntake.set(0);
        rightintake.set(0);
    }

    public void slowSpin(){
        leftIntake.set(0.25);
        rightintake.set(-0.25);
    }

    public void intakeUp(){
        armMotor.set(-0.8);
    }
    public void intakeDown(){
        armMotor.set(0.8);
    }

    public void shootCubez(){
        leftIntake.set(-1);
        rightintake.set(1);
    }

    public void stop(){
        armMotor.set(0);
        slowSpin();
    }

//    public void cylinder2Out(){
//        solenoid2.set(DoubleSolenoid.Value.kForward);
//    }
//
//    public void cylinder2In(){
//        solenoid2.set(DoubleSolenoid.Value.kReverse);
//    }
}
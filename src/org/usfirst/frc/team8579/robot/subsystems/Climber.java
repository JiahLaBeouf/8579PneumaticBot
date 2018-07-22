package org.usfirst.frc.team8579.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team8579.robot.RobotMap;
import org.usfirst.frc.team8579.robot.commands.StopClimber;

public class Climber extends Subsystem {


    //motors
    private Spark climberArm = new Spark(RobotMap.climberArm);

    private Spark tBOne = new Spark(RobotMap.climber1);
    private Spark tBTwo = new Spark(RobotMap.climber2);
    private SpeedControllerGroup climberTB = new SpeedControllerGroup(tBOne, tBTwo);

    public Climber(){

    }

    public void initDefaultCommand(){
        setDefaultCommand(new StopClimber());
    }

    /**
     * This method is for raising the climber arm to place the hook
     */
    public void armUp(){
        climberArm.set(-0.7);
    }

    public void armDown(){
        climberArm.set(0.5);
    }

    public void stopArm(){climberArm.stopMotor();}

    public void climbUp(){
        climberTB.set(1);
    }

    public void stopClimb(){climberTB.stopMotor();}

}

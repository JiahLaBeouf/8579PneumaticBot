/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team8579.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.usfirst.frc.team8579.robot.commands.DriveWithJoystick;

import static org.usfirst.frc.team8579.robot.RobotMap.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	//Left side
	private VictorSP left0 = new VictorSP(leftF);
	private VictorSP left1 = new VictorSP(leftB);
	private SpeedControllerGroup leftSide = new SpeedControllerGroup(left0,left1);

	//right side
	private Spark right8 = new Spark(rightF);
	private VictorSP right9 = new VictorSP(rightB);
	private SpeedControllerGroup rightSide = new SpeedControllerGroup(right8,right9);

	private DifferentialDrive robotDrive = new DifferentialDrive(leftSide,rightSide);

	public Drivetrain(){
		//in future init camera, init encoder, etc.
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystick());
	}

	/**
	 * Ardace drive method within the drivetrain class for use by the DriveWithJoystick method
	 * @param stick
	 */
	public void arcadeDrive(Joystick stick){
		double stickY = -stick.getRawAxis(5);

		if (-0.3<=stickY && stickY<=0.3){
			stickY=stickY*0.8;
		}
		else if (-0.1<=stickY && stickY<=0.1){
			stickY=0;
		}
		else{}

		double stickX = stick.getRawAxis(2);

		if (-0.3<=stickX && stickX<=0.3){
			stickX=stickX*0.8;
		}
		else if (-0.1<=stickX && stickX<=0.1){
			stickX=0;
		}
		else{}



		robotDrive.arcadeDrive(stickY,stickX);

		//leftSide.set(-stick.getY());
		//rightSide.set(stick.getRawAxis(5));
	}
}


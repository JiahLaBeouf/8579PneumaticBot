/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team8579.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;



    //Fill out when time allows
    //ten motors total, one double solenoid

    /**
     * DRIVETRAIN SUBSYSTEM
     * Left toughbox in 0,1
     * Right toughbox in 8,9
     */
    public static int leftF = 0;
    public static int leftB = 1;

    public static int rightF = 8;
    public static int rightB = 9;

    /**
     * INTAKE SUBSYSTEM
     * PG arm motor in 5
     * 775 pros in 6 & 7
     */
    public static int arm = 5;

    public static int intakeLeft = 6;
    public static int intakeRight = 7;

    /**
     * CLIMBER SUBSYSTEM
     * toughbox in 2,3
     * arm in 4
     */
    public static int climberArm = 4;

    public static int climber1 = 2;
    public static int climber2 = 3;




}

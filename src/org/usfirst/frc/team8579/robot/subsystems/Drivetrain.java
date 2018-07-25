/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team8579.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team8579.robot.commands.DriveWithJoystick;

import java.util.logging.Logger;

import static org.usfirst.frc.team8579.robot.RobotMap.*;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

    private Logger logger = Logger.getLogger( this.getClass().getName());

    private double distancePerPulse = 0.2493639169;

    //Left side
	private VictorSP left0 = new VictorSP(leftF);
	private VictorSP left1 = new VictorSP(leftB);
	private SpeedControllerGroup leftSide = new SpeedControllerGroup(left0,left1);

	//right side
	private Spark right8 = new Spark(rightF);
	private VictorSP right9 = new VictorSP(rightB);
	private SpeedControllerGroup rightSide = new SpeedControllerGroup(right8,right9);

	private DifferentialDrive robotDrive = new DifferentialDrive(leftSide,rightSide);


    //gyro
    private ADXRS450_Gyro gyro = null;

    //encoder
    private Encoder drivetrainEncoder = null;

    private UsbCamera camera = null;  // it will remain null if we have no camera plugged into the USB ports

	public Drivetrain(){
		//in future init camera, init encoder, etc.

        try {
            camera = CameraServer.getInstance().startAutomaticCapture();
        } catch (Exception e) {
            logger.info("Camera not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Camera Installed", false);
        }

        try {
            gyro = new ADXRS450_Gyro();
            SmartDashboard.putBoolean("Gyro Installed", true);

            //calibrates and resets the gyro to 0
            gyro.reset(); // Reset the angle the gyro is pointing towards to 0
            gyro.calibrate(); //Takes a long time, will have to test if necessary
        }
        catch (Exception e)
        {
            logger.info("Gyro not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Gyro Installed", false);
        }

        try{
            drivetrainEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
            SmartDashboard.putBoolean("Drivetrain encoder installed", true);

            drivetrainEncoder.setMaxPeriod(.1);
            drivetrainEncoder.setMinRate(10);
            drivetrainEncoder.setDistancePerPulse(0.2493639169);
            drivetrainEncoder.setReverseDirection(true);
            drivetrainEncoder.setSamplesToAverage(7);


            drivetrainEncoder.reset();
        } catch (Exception e){
            System.out.println("Encoder not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Drivetrain encoder installed", false);
        }
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



	//THIS IS ALL FROM THE OLD ROBOT CODE

    public void setPower(double leftPower, double rightPower){
	    leftSide.set(leftPower);
	    rightSide.set(-rightPower);
    }

    /**
     * Gets the current gyro angle
     */
    public double getGyroAngle(){
        //publishStats();
        double gyroAngle = 0;

        //error handling for if there is no gyro
        if (gyro != null){
            gyroAngle = gyro.getAngle();
        }
        return gyroAngle;
    }

    /**
     * Gets the moderated gyro angle (makes values between -360 and 360)
     * @return
     */
    public double getModGyroAngle(){
        double gyroModAngle = getGyroAngle() % 360;
        SmartDashboard.putNumber("getModGyroAngle", gyroModAngle);

        return gyroModAngle;
    }

    /**
     * resets the gyro
     */
    public void resetGyro(){
        try {
            gyro.reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Follows an angle off the gyro at a driver defined speed
     * @param power
     * @param gyroTarget
     */
    public void followGyro(double power, double gyroTarget)
    {
        //proportionally drives in the direction of a gyro heading, turning to face the right direction
        double currentGyroAngle = getGyroAngle() % 360;
        double gyroPowerAdjustment = 0;
        double gyroGain = 0.05;


        //Calculates how much to turn based on the current heading and the target heading
        gyroPowerAdjustment = currentGyroAngle - (gyroTarget % 360);
        gyroPowerAdjustment = gyroPowerAdjustment * gyroGain;

        double gyroMotorPowerLeft = power - gyroPowerAdjustment;
        double gyroMotorPowerRight = -power - gyroPowerAdjustment;

        //Makes the motors move
        leftSide.set(gyroMotorPowerLeft);
        rightSide.set(gyroMotorPowerRight);
    }

    /**
     * This method makes the robot stop on the spot.
     */
    public void hardStop(){

        // lets get the current gyro angle and encoder value.  We want to return to this spot
        long encoderPosition = drivetrainEncoder.getRaw();
        double gyroAtStop = gyro.getAngle();



        logger.info("hardStop: initial (leftToughbox:right) = (" + leftSide.get() + ":" + rightSide.get() + ")");
        double leftStopPower;
        double rightStopPower;

        if (leftSide.get()>0){
            leftStopPower = -0.1;

        }
        else{
            leftStopPower = 0.1;
        }

        if (leftSide.get() > 0){
            rightStopPower = -0.1;
        }
        else{
            rightStopPower = 0.1;
        }
//        leftStopPower = leftToughbox.get() * -1.5;
//        rightStopPower = rightToughbox.get() * -1.5;

        long beginTimneHardStop = System.currentTimeMillis();
        while (System.currentTimeMillis()-beginTimneHardStop < 25) {

            setPower(leftStopPower, rightStopPower);
            logger.fine("hardStop:current (leftToughbox:right) = (" + leftSide.get() + ":" + rightSide.get() + ")");
        }
        setPower(0,0);
        logger.info("hardStop:end (leftToughbox:right) = (" + leftSide.get() + ":" + rightSide.get() + ")");
        logger.info("hardStop finished");

    }

    public void turn(double targetAngle, boolean left)
    {
        logger.info("turn [" + targetAngle + ":" + left + "]");
        double turnPower = 0.34;
        double slowTurnPower = 0.25;

        SmartDashboard.putNumber("turn.targetAngle", targetAngle);
        SmartDashboard.putBoolean("turn.left", left);

        // reset gyro sensor to zero
        gyro.reset();   // do not calibrate as this will stop the world and make the gyro crazy

        long startTime = System.currentTimeMillis();
        long timeTaken = 0;

        while ( (Math.abs(getModGyroAngle()) < (Math.abs(targetAngle-10))) && (timeTaken < 5000) )
        {
            if (left)
            {
                setPower(-turnPower, turnPower);
            }
            else
            {
                // must want to turn right
                setPower(turnPower, -turnPower);
            }

            timeTaken = System.currentTimeMillis() - startTime;
        }

        hardStop();

        logger.info("Gyro turn finished");

    }

    /**
     * Drive the robot straight following the gyro for the given distance (based on the drive
     * train encoder reading).
     *
     * @param distance
     * @param power
     */
    public void driveStraightUsingEncoderGyro(double distance, double power){
        logger.info("driveStraight [distance:power][" + distance + ":" + power + "]");
        double gyroTarget = getGyroAngle();

        double targetPulses;
        targetPulses = (distance / distancePerPulse);

        // reset encoder so we can measure till we get to the target distance
        resetEncoder();

        SmartDashboard.putNumber("Target Pulses", targetPulses);
        double slowDownTarget = targetPulses*0.01;
        //double buffer = 0.1*targetPulses;

        long startTime = System.currentTimeMillis();
        long timeTaken = 0;

        while ((Math.abs(drivetrainEncoder.getRaw())<(targetPulses-slowDownTarget)) && (timeTaken < 5000) ){
            followGyro(power, gyroTarget);
            //setPower(power,power);
            SmartDashboard.putNumber("Encoder distance", drivetrainEncoder.getDistance());

            timeTaken = System.currentTimeMillis() - startTime;
        }
    }

    public void resetEncoder(){
        drivetrainEncoder.reset();
    }

    public void publishDrivetrainToSD(){
        SmartDashboard.putNumber("gyro value",getModGyroAngle());
        SmartDashboard.putNumber("encoder pulses",drivetrainEncoder.getRaw());
    }


}


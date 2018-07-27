package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team8579.robot.Robot;

import static org.usfirst.frc.team8579.robot.Robot.drivetrain;
import static org.usfirst.frc.team8579.robot.Robot.intake;

public class PerformRightMiddle extends Command{

    public PerformRightMiddle(){
        requires(intake);
        requires(drivetrain);
    }

    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {


        boolean commandFinished;

        intake.clamp();
        drivetrain.driveStraightUsingEncoderGyro(170,0.7);
        drivetrain.hardStop();

        long timer = System.currentTimeMillis();
        while (System.currentTimeMillis()-timer < 1000) {//was 1300

            drivetrain.setPower(0.3,0.3);
        }

        long timer1 = System.currentTimeMillis();

        while (System.currentTimeMillis()-timer1 < 200) {//was 1300

            intake.intakeDown();
        }
        intake.stop();
        long spitTimer = System.currentTimeMillis();
        while (System.currentTimeMillis()-spitTimer < 2000) {//was 1300

            intake.spitOut(0.6);
        }

        commandFinished = true;
        SmartDashboard.putBoolean("command finished",commandFinished);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}

package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
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

        intake.clamp();
        drivetrain.driveStraightUsingEncoderGyro(200,0.7);
        drivetrain.hardStop();

        long timer1 = System.currentTimeMillis();
        while (System.currentTimeMillis()-timer1 < 1000) {//was 1300

            intake.intakeDown();
        }
        intake.stop();
        intake.spitOut();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
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

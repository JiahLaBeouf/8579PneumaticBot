package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team8579.robot.Robot.drivetrain;
import static org.usfirst.frc.team8579.robot.Robot.intake;

public class PerformRightSide extends Command {

    public PerformRightSide(){
        requires(intake);
        requires(drivetrain);

    }
    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        drivetrain.driveStraightUsingEncoderGyro(170,0.7);
        drivetrain.hardStop();
        drivetrain.turn(90,true);

        long timer2 = System.currentTimeMillis();
        while (System.currentTimeMillis()-timer2 < 3000) {//was 1300

            drivetrain.setPower(0.4,0.4);
        }
        drivetrain.hardStop();
        intake.shootCubez();
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

package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import static org.usfirst.frc.team8579.robot.Robot.drivetrain;

public class PerformDriveStraight extends Command {

    public PerformDriveStraight(){
        requires(drivetrain);
    }

    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        drivetrain.driveStraightUsingEncoderGyro(300,0.7);
        drivetrain.hardStop();
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

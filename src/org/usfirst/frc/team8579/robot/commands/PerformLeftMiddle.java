package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team8579.robot.Robot;

public class PerformLeftMiddle extends Command {

    public PerformLeftMiddle(){
        requires(Robot.intake);

        requires(Robot.drivetrain);
    }

    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

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

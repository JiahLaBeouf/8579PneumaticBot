package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team8579.robot.Robot;

public class ReadGameData extends Command {

    public ReadGameData(){


    }

    @Override
    protected void initialize() {


    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        boolean switchIsRight = true;

        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();

        if (gameData.length() > 0 ) {
            if (gameData.charAt(0) == 'L') {
                 switchIsRight= false;
            } else {
                 switchIsRight= true;
            }

        }
        else {
            // no game data???, assume both switch and scale are to the left

        }

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

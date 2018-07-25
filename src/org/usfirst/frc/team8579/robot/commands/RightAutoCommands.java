package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAutoCommands extends CommandGroup{

    public RightAutoCommands(){


        //read game data

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

        if (switchIsRight){
            addSequential(new PerformRightSide());
        }
        else{
            addSequential(new PerformDriveStraight());
        }
    }
}
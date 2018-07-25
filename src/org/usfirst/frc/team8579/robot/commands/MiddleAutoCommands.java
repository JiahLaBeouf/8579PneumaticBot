package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class MiddleAutoCommands extends CommandGroup{

    public MiddleAutoCommands(){


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
            addSequential(new PerformRightMiddle());
        }
        else{
            addSequential(new PerformLeftMiddle());
        }
    }
}
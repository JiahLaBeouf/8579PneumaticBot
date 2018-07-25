package org.usfirst.frc.team8579.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team8579.robot.Robot.intake;

public class MiddleAutoCommands extends CommandGroup{

    public MiddleAutoCommands(){


        //read game data

        boolean switchIsRight = true;

        String gameData;
        gameData = DriverStation.getInstance().getGameSpecificMessage();

        SmartDashboard.putString("Game data read",gameData);

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
        SmartDashboard.putBoolean("IsSwitchRight",switchIsRight);

        if (switchIsRight==true){
            addSequential(new PerformRightMiddle(),10000);
        }
        else{
            addSequential(new PerformLeftMiddle(),12000);

            //intake.spitOut(0.3);
        }
        Scheduler.getInstance().removeAll();
    }
}
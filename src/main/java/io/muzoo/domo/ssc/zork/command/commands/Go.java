package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;

public class Go extends Command {
    @Override
    public void run(Game gameState, String argument) {
        if(argument.equals("")){
            System.out.println("Please input a direction");
            gameState.getMap().printAvailablePath();
        }
        else{
            String direction = argument.toLowerCase();
            if(gameState.getMap().getNeighbor(direction) != null){
                gameState.getMap().setPlayerIsHere(false); //Set previous room inHere to false
                gameState.setMap(gameState.getMap().getNeighbor(direction));
                gameState.getMap().setPlayerIsHere(true); //Set current room inHere to true
                gameState.getPlayer().roomHeal();
                System.out.println("You are currently in " + gameState.getMap().getRoomName());
                System.out.println(gameState.getMap().getRoomDescription());
                System.out.println("---------------");
                gameState.getMap().printAvailablePath();
            }
            else{
                System.out.println("Unknown direction, please input proper direction");
                gameState.getMap().printAvailablePath();
            }
        }
    }
}

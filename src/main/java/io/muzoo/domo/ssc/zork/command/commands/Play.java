package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.MapFilePathing;
import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.map.LoadMapFromTextFile;

public class Play extends Command {
    MapFilePathing gettingMap = new MapFilePathing();
    @Override
    public void run(Game gameState, String argument) {
        if(argument.equals("")){
            System.out.println("Please input the map name");
        }
        else if(gettingMap.getMap(argument) == null){
            System.out.println("Map not found");
        }
        else{
            gameState.setMap((new LoadMapFromTextFile(gettingMap.getMap(argument))).getRoom());
            gameState.setInGameState(true);
            gameState.setPlayer(new Player()); //Preventing loot farming by going in and exiting
            System.out.println("Map: " + argument + " loaded successfully");
            System.out.println("You are currently in " + gameState.getMap().getRoomName());
            System.out.println(gameState.getMap().getRoomDescription());
            gameState.getMap().printAvailablePath();
        }
    }
}

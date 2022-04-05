package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.map.Room;

public class Info extends Command {
    @Override
    public void run(Game gameState, String argument) {
        Room currentRoom = gameState.getMap();
        System.out.println("You are currently in " + currentRoom.getRoomName());
        System.out.println(currentRoom.getRoomDescription());
        System.out.println("---------------");
        gameState.getMap().printAvailablePath();
        System.out.println("---------------");
        gameState.getPlayer().myStatsInfo();
        if(currentRoom.getMonsterInRoom() != null){
            System.out.println("---------------");
            System.out.println("There's someone in here!");
            currentRoom.getMonsterInRoom().statsInfo();
        }
        if(currentRoom.getItemInRoom() != null){
            System.out.println("---------------");
            System.out.println(currentRoom.getItemInRoom().getItemName() + " lies in this room");
        }

    }
}

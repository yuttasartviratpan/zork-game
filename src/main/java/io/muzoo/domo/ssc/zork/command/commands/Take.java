package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.item.Item;

public class Take extends Command {

    @Override
    public void run(Game gameState, String argument) {
        Item item = gameState.getMap().getItemInRoom();
        if(item != null){
            gameState.getPlayer().pickUpItem(item);
            System.out.println("Taken " + item.getItemName());
            gameState.getMap().setItemInRoom(null);
        }
        else{
            System.out.println("There is no item to take");
        }
    }
}

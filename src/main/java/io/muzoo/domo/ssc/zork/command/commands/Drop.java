package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.item.Item;

public class Drop extends Command {
    @Override
    public void run(Game gameState, String argument) {
        Item item = Item.stringNameToItemType(argument);
        if(item != null){
            gameState.getPlayer().dropItem(item);
        }
    }
}

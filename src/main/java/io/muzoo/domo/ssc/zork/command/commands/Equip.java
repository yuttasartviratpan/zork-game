package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.item.ItemType;

public class Equip extends Command {
    @Override
    public void run(Game gameState, String argument) {
        Item item = Item.stringNameToItemType(argument);
        if(item != null){
            if(item.getItemType() == ItemType.WEAPON){
                gameState.getPlayer().equipWeapon(item);
            }
            else{
                System.out.println("You cannot equip that");
            }
        }
        else{
            System.out.println("No such item exist here");
        }
    }
}

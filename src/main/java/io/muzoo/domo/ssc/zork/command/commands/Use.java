package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.command.ItemUseAction;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.item.ItemType;

import java.util.Random;

public class Use extends Command {

    @Override
    public void run(Game gameState, String argument) {
        Item item = Item.stringNameToItemType(argument);
        if(item != null){
            if(item.getItemType() == ItemType.CONSUMABLE){
                ItemUseAction action = gameState.getPlayer().useItem(item);
                if(action == ItemUseAction.USE_POTION){
                    gameState.getPlayer().incrementCurrentHP(item.getItemValue());
                    System.out.println("You used healing potion. Your wounds began to heal");
                }
                else if(action == ItemUseAction.USE_THROWABLE){
                    if(gameState.getMap().getMonsterInRoom() != null){
                        Random random = new Random();
                        System.out.println("You used " + item.getItemName() + " on the enemy, keeping you distance");
                        int number = random.nextInt(2);
                        if(number == 1){
                            System.out.println("It hits the enemy!");
                            gameState.getMap().getMonsterInRoom().decrementMonsterHP(item.getItemValue());
                        }
                        else{
                            System.out.println("But it misses...");
                        }
                    }
                    else{
                        System.out.println("You used " + item.getItemName() + " on nothing...");
                    }
                }
                else{
                    System.out.println("You do not own any of that item");
                }
            }
            else{
                System.out.println("Cannot use that item");
            }
        }
        else{
            System.out.println("No such item exist here");
        }
    }
}

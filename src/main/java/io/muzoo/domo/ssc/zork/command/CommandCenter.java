package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.item.usable.KeyItem;
import io.muzoo.domo.ssc.zork.map.AvailableMap;
import io.muzoo.domo.ssc.zork.map.ZorkMap;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CommandCenter {

    public void checkCommand(CommandType commandType, Game gameState, String arguments) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(commandType == null){
            System.out.println("Unknown command or no command entered. Use \"help\" to view all available command");
        }
        else if(commandType.getInGameOnly() != gameState.getInGameState() && gameState.getInGameState()){
            System.out.println("You can't use this command while you're in a map");
        }
        else if(commandType.getInGameOnly() != gameState.getInGameState()){
            System.out.println("You can't use this command while you're outside a map");
        }
        else{
            commandType.getCommandClass().getDeclaredConstructor().newInstance().run(gameState, arguments);
        }
    }


}

class CommandInfo extends Command{

    @Override
    public void run(Game gameState, String argument){
        gameState.getMap().getPlayer().myStatsInfo();
        if(gameState.getMap().getMonsterInCurrentRoom() != null){
            if(!gameState.getMap().getMonsterInCurrentRoom().checkIfDead()){
                System.out.println("----------");
                System.out.println("There is a monster in a room");
                gameState.getMap().getMonsterInCurrentRoom().StatsInfo();
            }
        }
        if(gameState.getMap().getItemInCurrentRoom() != null){
            System.out.println("----------");
            if(gameState.getMap().getItemInCurrentRoom().getItemClass().equals(ItemUsable.class)){
                if(gameState.getMap().getItemInCurrentRoom().getClass().equals(KeyItem.class)){
                    System.out.println("There's something on the ground");
                }
                else{
                    System.out.println("There is a consumable item in this room");
                    gameState.getMap().getItemInCurrentRoom().getInfo();
                }
            }
            else if(gameState.getMap().getItemInCurrentRoom().getItemClass().equals(ItemWeapon.class)){
                System.out.println("There is a weapon in this room");
                gameState.getMap().getItemInCurrentRoom().getInfo();
            }

        }
        System.out.println("----------");
        gameState.getMap().getCurrentRoom().getPathAvailable();

    }
}

class CommandInventory extends Command{

    List<ItemWeapon> weaponInventory;
    Map<ItemUsable, Integer> usableInventory;

    @Override
    public void run(Game gameState, String argument){
        if(argument.equalsIgnoreCase("weapon") || argument.equalsIgnoreCase("weapons")){
            weaponInventory = gameState.getMap().getPlayer().checkInventoryWeapon();
            if(weaponInventory.isEmpty()){
                System.out.println("You don't have any stored weapon");
            }
            else{
                System.out.println("You owned: ");
                for(ItemWeapon item : weaponInventory){
                    System.out.println(" - " + item.getName());
                }
            }
        }
        else if(argument.equalsIgnoreCase("item") || argument.equalsIgnoreCase("items")){
            usableInventory = gameState.getMap().getPlayer().checkInventoryConsumable();
            if(usableInventory.isEmpty()){
                System.out.println("You don't have any items");
            }
            else{
                System.out.println("You have: ");
                for(ItemUsable item : usableInventory.keySet()){
                    System.out.println(usableInventory.get(item) + " of " + item.getName());
                }
            }
        }
        else{
            System.out.println("That type of object doesn't exist here");
        }
    }

}

class CommandTake extends Command{

    @Override
    public void run(Game gameState, String argument){
        if(gameState.getMap().getItemInCurrentRoom() != null){
            gameState.getMap().getPlayer().pickUpConsumable((ItemUsable) gameState.getMap().getItemInCurrentRoom());
        }
        else{
            System.out.println("There is no item in this room");
        }
    }
}

class CommandUse extends Command{

    ItemUsable itemUsable;

    @Override
    public void run(Game gameState, String argument){
        if(searchItemUsableUsingString(gameState.getMap().getPlayer().checkInventoryConsumable().keySet(), argument)){
            switch (itemUsable.getUsableType()){
                case HEALTH_POTION:
                    gameState.getMap().getPlayer().usingItem(itemUsable);
                    System.out.println("Your wounds started to heal");
                    break;

                case THROWABLE_WEAPON:
                    Monster monster = gameState.getMap().getMonsterInCurrentRoom();
                    if(monster == null){
                        System.out.println("There is no monster here, you don't need to use it");
                    }
                    else{
                        gameState.getMap().getPlayer().usingItem(itemUsable);
                        monster.setCurrentHP(monster.getCurrentHP() - itemUsable.use());
                        if(monster.checkIfDead()){
                            monster.setDead();
                        }
                    }
                    break;

                default:
                    System.out.println("You can't use it here. Just keep it safe");
                    break;
            }
        }
        else{
            System.out.println("Item not found, use \"inventory item\" to see what items you have ");
        }
    }

    private boolean searchItemUsableUsingString(Set<ItemUsable> itemUsableSet, String itemName){
        for(ItemUsable item : itemUsableSet){
            if(item.getName().equalsIgnoreCase(itemName.toLowerCase())){
                itemUsable = item;
                return true;
            }
        }
        return false;
    }
}

class CommandDrop extends Command{

    @Override
    public void run(Game gameState, String argument){
        if(gameState.getMap().getPlayer().checkForItem(argument) != null){
            gameState.getMap().getPlayer().dropItem(gameState.getMap().getPlayer().checkForItem(argument));
        }
        else{
            System.out.println("Item not found");
        }
    }
}

class CommandAttack extends Command{

    @Override
    public void run(Game gameState, String argument) {
        Player player = gameState.getMap().getPlayer();
        Monster monster = gameState.getMap().getMonsterInCurrentRoom();
        System.out.println("You attacked the monster!");
        monster.setCurrentHP(monster.getCurrentHP() - player.attackDamage());
        if(monster.checkIfDead()){
            monster.setDead();
        }
        if(player.checkIfDead()){
            player.setDead();
        }
    }

}

class CommandGo extends Command{

    @Override
    public void run(Game gameState, String argument){
        if(gameState.getMap().getCurrentRoom().getRoom(argument) == null){
            System.out.println("That path doesn't exist");
        }
        else{
            System.out.println("Moving to: " + gameState.getMap().getCurrentRoom().getRoom(argument).getRoomName());
            gameState.getMap().getCurrentRoom().getRoom(argument).getRoomInfo();
            gameState.getMap().moving(gameState.getMap().getCurrentRoom(), gameState.getMap().getCurrentRoom().getRoom(argument));
            gameState.getMap().getPlayer().nextRoomHeal(10);
        }
    }
}

class CommandMap extends Command{

    @Override
    public void run(Game gameState, String argument){
        gameState.getMap().mapping();
    }
}

class CommandAutoPilot extends Command{

    @Override
    public void run(Game gameState, String argument) {

    }

}

class CommandPlay extends Command{

    Map<String, ZorkMap> availableMap = (new AvailableMap()).getAllMap();


    @Override
    public void run(Game gameState, String argument){
        if(availableMap.get(argument.toLowerCase()) == null){
            System.out.println("There is no map with that name");

        }
        else{
            System.out.println("Map: " + argument + " loaded");
            gameState.setMap(availableMap.get(argument.toLowerCase()));
            gameState.setInGameState(true);
        }
    }
}

class CommandExit extends Command{

    @Override
    public void run(Game gameState, String argument) {
        System.out.println("Terminating the program, see you later?");
        gameState.setGameRunning(false);
    }


}

class CommandQuit extends Command{

    @Override
    public void run(Game gameState, String argument) {
        System.out.println("Returning to main menu");
        gameState.setMap(null);
        gameState.setInGameState(false);
    }

}

class CommandHelp extends Command{

    @Override
    public void run(Game gameState, String argument){
        System.out.println("The commands are:");
        CommandType.helpCommandDescriptionPrinting();
    }
}

class CommandLoad extends Command{

    @Override
    public void run(Game gameState, String argument) {

    }

}

class CommandSave extends Command{

    @Override
    public void run(Game gameState, String argument) {

    }
}

class CommandEquip extends Command{

    @Override
    public void run(Game gameState, String argument){
        gameState.getMap().getPlayer().equipWeapon(argument);
    }
}

class CommandInspect extends Command{

    @Override
    public void run(Game gameState, String argument){
        gameState.getMap().getPlayer().equipWeapon(argument);
    }
}

//Inspect is broken, ofc
//Item are used (at least the print is there) when using item you dont have (zero, but entity exist in HashMap).
//
// To implement:
// - Item in the room should not be consumable only. (Also, more than 1 item if possible)
// - Turn based system? (Current you could do when you attack, the monster also attack you simultaneously)
//   - This is such that throwing knife can be use to attack, but will not consume a turn.
// - Item/Weapon name should be change to include <space>. Also when compared, it should ignore case, but display title case.

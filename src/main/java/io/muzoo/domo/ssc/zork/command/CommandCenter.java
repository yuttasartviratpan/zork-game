package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.item.ItemUsable;
import io.muzoo.domo.ssc.zork.item.ItemWeapon;
import io.muzoo.domo.ssc.zork.item.usable.KeyItem;
import io.muzoo.domo.ssc.zork.map.AvailableMap;
import io.muzoo.domo.ssc.zork.map.ZorkMap;

import java.util.*;

public class CommandCenter {
    ZorkMap map;
    Player player;
    boolean isInGame;

    public CommandCenter(ZorkMap map, Player player, boolean isInGame){
        this.map = map;
        this.player = player;
        this.isInGame = isInGame;
    }


    public boolean checkCommand(CommandList commandType, List<String> arguments){
        switch (commandType){
            case INFO:
                if(isInGame){
                    new CommandInfo(map, player).run();
                }
                else{
                    System.out.println("You cannot use this command while you're outside the map");
                }
                return true;

            case MAP:
                if(isInGame){
                    new CommandMap(map).run();
                }
                else{
                    System.out.println("You cannot use this command while you're outside the map");
                }
                return true;

            case TAKE:
                return true;

            case USE:
                if(isInGame){
                    if(arguments.isEmpty()){
                        System.out.println("Please specify the item you want to use");
                    }
                    else{
                        new CommandUse(map, player, arguments.get(0)).run();
                    }
                }
                else{
                    System.out.println("You cannot use this command while you're outside the map");
                }
                return true;

            case DROP:
                return true;

            case ATTACK:
                return true;

            case GO:
                if(isInGame){
                    if(arguments.isEmpty()){
                        System.out.println("Where are we going, tell me");
                    }
                    else{
                        (new CommandGo(map, player, arguments.get(0))).run();
                    }
                }
                else{
                    System.out.println("You cannot use this command while you're not in the map");
                }
                return true;

            case HELP:
                Map<String, String> allCommand = (new AllCommandList()).getAllCommandInfo();
                Map<String, Integer> numOfArguments = (new AllCommandList()).getAllNumberOfArgumentInCommand();
                System.out.println("The available commands are: ");
                for(String i : allCommand.keySet()){
                    if(numOfArguments.get(i) > 0){
                        System.out.print(" - " + i);
                        commandArgumentCheck(i);
                        System.out.print(" : " + allCommand.get(i));
                        System.out.print("\n");
                    }
                    else{
                        System.out.print(" - " + i + " : " + allCommand.get(i));
                        System.out.print("\n");
                    }


                }
                return true;

            case QUIT:
                if(isInGame){
                    System.out.println("Exiting the map");
                    isInGame = false;
                }
                else{
                    System.out.println("You are not in any map, use play <map-name> first");
                }
                return true;

            case PLAY:
                Map<String, ZorkMap> allMap = new AvailableMap().getAllMap();
                if(!isInGame){
                    if(arguments.isEmpty()){
                        System.out.println("Please specify the map name you would like to play");
                        System.out.println("The available map are: ");
                        for(String mapName : allMap.keySet()){
                            System.out.println(" - " + mapName);
                        }
                    }
                    else{
                        map = (new CommandPlay(map, arguments.get(0)).run());
                        isInGame = true;
                    }
                }
                else{
                    System.out.println("Quit this map first before you can play other map");
                }
                return true;

            case LOAD:
                return true;

            case SAVE:
                return true;

            case EXIT:
                if(isInGame){
                    System.out.println("You're currently in a map, use quit command first");
                    return true;
                }
                else{
                    System.out.println("Exiting the program. See your later?");
                    return false;
                }

            case EQUIP:
                return true;

            case STATS:
                return true;

            case INVENTORY:
                if(isInGame){
                    if(arguments.isEmpty()){
                        System.out.println("Please enter whether to check item or weapon by typing");
                        System.out.println("\"inventory weapon\" or \"inventory item\"");
                    }
                    else{
                        new CommandInventory(player, arguments.get(0)).run();
                    }
                }
                else{
                    System.out.println("This command can only be use while in a map");
                }
                return true;

            default:
                System.out.println("Unknown command. Use \"help\" to view the list of available command.");
                return true;

        }
    }

    private void commandArgumentCheck(String command){
        if(command.equalsIgnoreCase("go")){
            System.out.print(" <direction>");
        }
        if(command.equalsIgnoreCase("autopilot")){
            System.out.print(" <file>");
        }
        if(command.equalsIgnoreCase("play")){
            System.out.print(" <map-name>");
        }
        if(command.equalsIgnoreCase("load")){
            System.out.print(" <save-point-name>");
        }
        if(command.equalsIgnoreCase("save")){
            System.out.print(" <save-point-name>");
        }
        if(command.equalsIgnoreCase("equip")){
            System.out.print(" <weapon-name>");
        }
        if(command.equalsIgnoreCase("drop")){
            System.out.print(" <item-name>");
        }
        if(command.equalsIgnoreCase("use")){
            System.out.print(" <item-name>");
        }
    }


}

class CommandInfo extends Command{
    ZorkMap map;
    Player player;

    public CommandInfo(ZorkMap map, Player player){
        this.map = map;
        this.player = player;
    }

    public void run(){
        player.myStatsInfo();
        if(map.getMonsterInCurrentRoom() != null){
            System.out.println("----------");
            System.out.println("There is a monster in a room");
            map.getMonsterInCurrentRoom().StatsInfo();
        }
        if(map.getItemInCurrentRoom() != null){
            System.out.println("----------");
            if(map.getItemInCurrentRoom().getItemClass().equals(ItemUsable.class)){
                if(map.getItemInCurrentRoom().getClass().equals(KeyItem.class)){
                    System.out.println("There's something on the ground");
                }
                else{
                    System.out.println("There is a consumable item in this room");
                    map.getItemInCurrentRoom().getInfo();
                }
            }
            else if(map.getItemInCurrentRoom().getItemClass().equals(ItemWeapon.class)){
                System.out.println("There is a weapon in this room");
                map.getItemInCurrentRoom().getInfo();
            }

        }
        System.out.println("----------");
        map.getCurrentRoom().getPathAvailable();

    }
}

class CommandInventory extends Command{
    Player player;
    String argument;
    List<ItemWeapon> weaponInventory;
    Map<ItemUsable, Integer> usableInventory;
    public CommandInventory(Player player, String argument){
        this.player = player;
        this.argument = argument;
    }

    public void run(){
        if(argument.equalsIgnoreCase("weapon") || argument.equalsIgnoreCase("weapons")){
            weaponInventory = player.checkInventoryWeapon();
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
            usableInventory = player.checkInventoryConsumable();
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

class CommandTake{

}

class CommandUse{
    ZorkMap map;
    Player player;
    String parameter;
    ItemUsable itemUsable;
    public CommandUse(ZorkMap map, Player player, String parameter){
        this.map = map;
        this.player = player;
        this.parameter = parameter;
    }

    public void run(){
        if(searchItemUsableUsingString(player.checkInventoryConsumable().keySet(), parameter)){
            switch (itemUsable.getUsableType()){
                case HEALTH_POTION:
                    player.usingItem(itemUsable);
                    System.out.println("Your wounds started to heal");
                    break;

                case THROWABLE_WEAPON:
                    Monster monster = map.getMonsterInCurrentRoom();
                    if(monster == null){
                        System.out.println("There is no monster here, you don't need to use it");
                    }
                    else{
                        player.usingItem(itemUsable);
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

class CommandDrop{

}

class CommandAttack{

}

class CommandGo extends Command{
    String parameter;
    public CommandGo(ZorkMap map, Player player, String parameter){
        this.map = map;
        this.player = player;
        this.parameter = parameter;
    }

    public void run(){
        if(map.getCurrentRoom().getRoom(parameter) == null){
            System.out.println("That path doesn't exist");
        }
        else{
            System.out.println("Moving to: " + map.getCurrentRoom().getRoom(parameter).getRoomName());
            map.getCurrentRoom().getRoom(parameter).getRoomInfo();
            map.moving(map.getCurrentRoom(), map.getCurrentRoom().getRoom(parameter));
            player.nextRoomHeal(10);
        }
    }
}

class CommandMap extends Command{
    public CommandMap(ZorkMap map){
        this.map = map;
    }

    public void run(){
        map.mapping();
    }
}

class CommandAutoPilot{

}

class CommandPlay extends Command{
    String parameter;
    Map<String, ZorkMap> availableMap = (new AvailableMap()).getAllMap();
    public CommandPlay(ZorkMap map, String parameter){
        this.map = map;
        this.parameter = parameter;

    }

    public ZorkMap run(){
        if(availableMap.get(parameter.toLowerCase()) == null){
            System.out.println("There is no map with that name");
            return null;
        }
        else{
            System.out.println("Map: " + parameter + " loaded");
            return availableMap.get(parameter.toLowerCase());
        }
    }
}

class CommandLoad{

}

class CommandSave{

}

class CommandEquip extends Command{

}

class CommandStats extends Command{

}

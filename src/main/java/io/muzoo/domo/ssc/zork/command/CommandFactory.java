package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.map.AvailableMap;
import io.muzoo.domo.ssc.zork.map.ZorkMap;
import io.muzoo.domo.ssc.zork.map.map1.MapOne;

import java.util.List;
import java.util.Map;

public class CommandFactory {
    ZorkMap map;
    Player player;
    ParserAndProcessor parser;
    boolean isInGame;

    public CommandFactory(ZorkMap map, Player player, boolean isInGame){
        this.map = map;
        this.player = player;
        this.isInGame = isInGame;
    }


    public boolean checkCommand(CommandList commandType, List<String> arguments){
        switch (commandType){
            case INFO:
                return true;
            case TAKE:
                return true;
            case USE:
                return true;
            case DROP:
                return true;
            case ATTACK:
                return true;
            case GO:
                if(isInGame){
                    if(arguments.size() == 0){
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
                    System.out.print(" - " + i + " : " + allCommand.get(i));
                    if(numOfArguments.get(i) > 0){
                        if(i.equalsIgnoreCase("go")){
                            System.out.print(" <direction>");
                        }
                        if(i.equalsIgnoreCase("autopilot")){
                            System.out.print(" <file>");
                        }
                        if(i.equalsIgnoreCase("play")){
                            System.out.print(" <map-name>");
                        }
                        if(i.equalsIgnoreCase("load")){
                            System.out.print(" <save-point-name>");
                        }
                        if(i.equalsIgnoreCase("save")){
                            System.out.print(" <save-point-name>");
                        }
                        if(i.equalsIgnoreCase("equip")){
                            System.out.print(" <weapon-name>");
                        }
                        if(i.equalsIgnoreCase("drop")){
                            System.out.print(" <item-name>");
                        }
                        if(i.equalsIgnoreCase("use")){
                            System.out.print(" <item-name>");
                        }
                        System.out.print("\n");
                    }
                    else{
                        System.out.print("\n");
                    }

                }
                return true;
            case QUIT:
                isInGame = false;
                return true;
            case PLAY:
                if(!isInGame){
                    if(arguments.size() == 0){

                    }
                    map = (new CommandPlay(map, arguments.get(0)).run());
                    isInGame = true;
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
                    System.out.println("You're currently in game, use quit command first");
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
                return true;
            default:
                System.out.println("Unknown command. Use \"help\" to view the list of available command.");
                return true;

        }
    }

}

class CommandInfo{

}

class CommandTake{

}

class CommandUse{

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

class CommandMap{

}

class CommandAutoPilot{

}

class CommandQuit{

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

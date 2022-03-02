package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.map.map1.MapOneRule;

import java.util.List;

public class CommandFactory {
    MapOneRule map;
    Player player;
    ParserAndProcessor parser;
    boolean isInGame;

    public CommandFactory(MapOneRule map, Player player){
        this.map = map;
        this.player = player;
        this.isInGame = false;
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
                (new CommandGo(map, player, isInGame, arguments.get(0))).run();
                return true;
            case HELP:
                return true;
            case QUIT:
                return true;
            case PLAY:
                return true;
            case LOAD:
                return true;
            case SAVE:
                return true;
            case EXIT:
                return false;
            case EQUIP:
                return true;
            case STATS:
                return true;
            default:
                System.out.println("Unknown command. Use \"help\" to view the list of available command.");
                return true;

        }
    }

}
/*
case EMPTY:
                System.out.println("No command has been entered. Use \"help\" to view the list of available command.");
                return true;

            case INFO:
                //Run command info
                System.out.println("Here is your info");
                return true;

            case TAKE:
                //Run command take
                System.out.println("This is your take: ");
                checkArgument();
                return true;

            case USE:
                //Run command use
                System.out.println("This is your use: ");
                checkArgument();
                return true;

            case DROP:
                //Run command drop
                System.out.println("This is your drop: ");
                checkArgument();
                return true;

            case ATTACK:
                //Run command attack
                System.out.println("This is your attack: ");
                checkArgument();
                return true;

            case GO:
                //Run command go
                System.out.println("This is your go: ");
                checkArgument();
                return true;

            case MAP:
                //Run command map
                System.out.println("Here's your map");
                return true;

            case AUTOPILOT:
                //Run command auto
                System.out.println("Autopiloting using: ");
                checkArgument();
                return true;

            case HELP:
                //Run command help
                System.out.println("There is no help right now");
                return true;

            case QUIT:
                //Run command quit
                System.out.println("Quitting the game");
                return false;

            case PLAY:
                //Run command play
                System.out.println("Here's your play: ");
                checkArgument();
                return true;

            case LOAD:
                //Run command load
                System.out.println("Here's your load: ");
                checkArgument();
                return true;

            case SAVE:
                //Run command save
                System.out.println("Here's your save: ");
                checkArgument();
                return true;

            case EXIT:
                //Run command exit
                System.out.println("Okay. Aborting.");
                return false;

            default:
                System.out.println("Unknown command. Use \"help\" to view the list of available command.");
                return true;

        }
 */

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
    boolean forInGame = true;
    String parameter;
    public CommandGo(MapOneRule map, Player player, boolean gameStatus, String parameter){
        this.map = map;
        this.player = player;
        currentGameStatus = true; //gameStatus
        this.parameter = parameter;
    }

    public boolean run(){
        if(forInGame == currentGameStatus){
            if(map.getCurrentRoom().getRoom(parameter) == null){
                System.out.println("That path doesn't exist");
            }
            else{
                System.out.println("Moving to: " + map.getCurrentRoom().getRoom(parameter).getRoomName());
                map.getCurrentRoom().getRoom(parameter).getRoomInfo();
                map.moving(map.getCurrentRoom(), map.getCurrentRoom().getRoom(parameter));
            }

        }
        else{
            System.out.println("Cannot be use outside the map");
        }

        return false;
    }
}

class CommandMap{

}

class CommandAutoPilot{

}

class CommandHelp{

}

class CommandQuit{

}

class CommandPlay{

}

class CommandLoad{

}

class CommandSave{

}

class CommandExit{

}

class CommandEquip{

}

class CommandStats{

}

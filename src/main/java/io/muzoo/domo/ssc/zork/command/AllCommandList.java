package io.muzoo.domo.ssc.zork.command;

import java.util.HashMap;
import java.util.Map;

public class AllCommandList {
    Map<String, CommandType> allCommand = new HashMap<>();
    Map<String, String> allCommandInfo = new HashMap<>();
    Map<String, Integer> numberOfArgumentInCommand = new HashMap<>();
    public AllCommandList(){
        allCommand.put("info", CommandType.INFO);
        allCommand.put("take", CommandType.TAKE);
        allCommand.put("use", CommandType.USE);
        allCommand.put("drop", CommandType.DROP);
        allCommand.put("attack", CommandType.ATTACK);
        allCommand.put("go", CommandType.GO);
        allCommand.put("map", CommandType.MAP);
        allCommand.put("autopilot", CommandType.AUTOPILOT);
        allCommand.put("help", CommandType.HELP);
        allCommand.put("quit", CommandType.QUIT);
        allCommand.put("play", CommandType.PLAY);
        allCommand.put("load", CommandType.LOAD);
        allCommand.put("save", CommandType.SAVE);
        allCommand.put("exit", CommandType.EXIT);
        allCommand.put("equip", CommandType.EQUIP);
        allCommand.put("inventory", CommandType.INVENTORY);
        allCommand.put("inspect", CommandType.INSPECT);

        numberOfArgumentInCommand.put("info", 0);
        numberOfArgumentInCommand.put("take", 1);
        numberOfArgumentInCommand.put("use", 1);
        numberOfArgumentInCommand.put("drop", 1);
        numberOfArgumentInCommand.put("attack", 1);
        numberOfArgumentInCommand.put("go", 1);
        numberOfArgumentInCommand.put("map", 0);
        numberOfArgumentInCommand.put("autopilot", 1);
        numberOfArgumentInCommand.put("help", 0);
        numberOfArgumentInCommand.put("quit", 0);
        numberOfArgumentInCommand.put("play", 1);
        numberOfArgumentInCommand.put("load", 1);
        numberOfArgumentInCommand.put("save", 1);
        numberOfArgumentInCommand.put("exit", 0);
        numberOfArgumentInCommand.put("equip", 1);
        numberOfArgumentInCommand.put("inventory", 1);
        numberOfArgumentInCommand.put("inspect", 1);

        allCommandInfo.put("info", "Get the information of the room, monsters and items (if any in the room)");
        allCommandInfo.put("take", "Take all items that are in the room you're in");
        allCommandInfo.put("use", "Use the specify item");
        allCommandInfo.put("drop", "Drop the specify item");
        allCommandInfo.put("attack", "Attack the monster currently in the room");
        allCommandInfo.put("go", "Head towards the direction specified");
        allCommandInfo.put("map", "Look at the map layout");
        allCommandInfo.put("autopilot", "Read a script that plays the game for you");
        allCommandInfo.put("help", "Print all commands available");
        allCommandInfo.put("quit", "Quit playing the map you're currently in");
        allCommandInfo.put("play", "Play in the map specified");
        allCommandInfo.put("load", "Load your save point specified");
        allCommandInfo.put("save", "Save your progression as specified name");
        allCommandInfo.put("exit", "Terminate the game");
        allCommandInfo.put("equip", "Equip the specified weapon");
        allCommandInfo.put("inventory", "Check what items you have");
        allCommandInfo.put("inspect", "Inspect specific item in your inventory");
    }

    public Map<String, Integer> getAllNumberOfArgumentInCommand(){
        return numberOfArgumentInCommand;
    }

    public Map<String, CommandType> getAllCommandList(){
        return allCommand;
    }

    public Map<String, String> getAllCommandInfo(){
        return allCommandInfo;
    }

}

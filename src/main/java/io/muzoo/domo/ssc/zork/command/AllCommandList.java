package io.muzoo.domo.ssc.zork.command;

import java.util.HashMap;
import java.util.Map;

public class AllCommandList {
    Map<String, CommandList> allCommand = new HashMap<>();
    Map<String, String> allCommandInfo = new HashMap<>();
    Map<String, Integer> numberOfArgumentInCommand = new HashMap<>();
    public AllCommandList(){
        allCommand.put("info", CommandList.INFO);
        allCommand.put("take", CommandList.TAKE);
        allCommand.put("use", CommandList.USE);
        allCommand.put("drop", CommandList.DROP);
        allCommand.put("attack", CommandList.ATTACK);
        allCommand.put("go", CommandList.GO);
        allCommand.put("map", CommandList.MAP);
        allCommand.put("autopilot", CommandList.AUTOPILOT);
        allCommand.put("help", CommandList.HELP);
        allCommand.put("quit", CommandList.QUIT);
        allCommand.put("play", CommandList.PLAY);
        allCommand.put("load", CommandList.LOAD);
        allCommand.put("save", CommandList.SAVE);
        allCommand.put("exit", CommandList.EXIT);
        allCommand.put("equip", CommandList.EQUIP);
        allCommand.put("stats", CommandList.STATS);
        allCommand.put("inventory", CommandList.INVENTORY);

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
        numberOfArgumentInCommand.put("stats", 0);
        numberOfArgumentInCommand.put("inventory", 1);

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
        allCommandInfo.put("stats", "Get the current status about yourself");
        allCommandInfo.put("inventory", "Check what items you have");
    }

    public Map<String, Integer> getAllNumberOfArgumentInCommand(){
        return numberOfArgumentInCommand;
    }

    public Map<String, CommandList> getAllCommandList(){
        return allCommand;
    }

    public Map<String, String> getAllCommandInfo(){
        return allCommandInfo;
    }

}

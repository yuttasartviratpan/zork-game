package io.muzoo.domo.ssc.zork.command;

public enum CommandType {
//    INFO, TAKE, USE, DROP, ATTACK, GO, MAP, AUTOPILOT, HELP, QUIT, PLAY, LOAD, SAVE, EXIT, EQUIP,
//    INVENTORY, INSPECT, NOTFOUND, EMPTY, INCOMPLETE

    //CommandType(string.lowercase, description, argumentReq)
    INFO(CommandInfo.class, "info", ": Get the information of the room, monsters and items (if any in the room)", 0),
    TAKE(CommandTake.class,"take", "<item name> : Take an item specified that is in the room, use \"take all\" to take all items in the room", 1),
    USE(CommandUse.class,"use", "<item name> : Use the specify item", 1),
    DROP(CommandDrop.class,"drop", "<item name> : Drop a specified item that you have in inventory", 1),
    ATTACK(CommandAttack.class,"attack", ": Attack the monster currently in the room", 0),
    GO(CommandGo.class,"go", "<direction (NEWS)> : Move to the next location by giving specific direction", 1),
    MAP(CommandMap.class,"map", ": Print out the structure of the current map you're playing", 0),
    AUTOPILOT(CommandAutoPilot.class,"autopilot", "<file name> : Play this game using the file containing commands for this game", 1),
    HELP(CommandHelp.class,"help", ": Get all the commands available for this game", 0),
    QUIT(CommandQuit.class,"quit", ": Exit from the current map you're playing in. Warning: The progress will not be save " +
            "automatically. Use \"save <save name>\" to save the current state of the game", 0),
    PLAY(CommandPlay.class,"play", "<map name> : Start the game in a map of specified choice", 1),
    LOAD(CommandLoad.class,"load", "<save name> : Continue the game from the saved progress from specified save name", 1),
    SAVE(CommandSave.class,"save", "<save name> Record the current progress as the specified name", 1),
    EXIT(CommandExit.class,"exit", ": Terminate the program instance", 0),
    EQUIP(CommandEquip.class,"equip", "<weapon name> : Armed yourself with specified weapon", 1),
    INVENTORY(CommandInventory.class,"inventory", "<item type> View the current item you owned. Use \"inventory all\" " +
            "to view all types of item", 1),
    INSPECT(CommandInspect.class,"inspect", "<item name> Analyse the specified item, to get information from it", 1);

    private Class<? extends Command> commandClass;
    private String commandString;
    private String description;
    private int argumentReq;

    CommandType(Class<? extends Command> commandClass, String commandString, String description, int argumentReq){
        this.commandClass = commandClass;
        this.commandString = commandString;
        this.description = description;
        this.argumentReq = argumentReq;
    }

    public static boolean checkCommandString(String commandStringIn){
        for(CommandType commandType : CommandType.values()){
            if(commandType.commandString.equalsIgnoreCase(commandStringIn)){
                return true;
            }
        }
        return false;
    }

    public Class<? extends Command> getCommandClass(){
        return commandClass;

        //I presume that it will never reach null because in other class, it will check first whether
        //The command exist or not
    }

    public static CommandType getCommandType(String commandStringIn){
        for(CommandType commandType : CommandType.values()){
            if(commandType.commandString.equalsIgnoreCase(commandStringIn)){
                return commandType;
            }
        }
        return null;

        //I presume that it will never reach null because in other class, it will check first whether
        //The command exist or not
    }

    public String getCommandString(){
        return commandString;
    }

}

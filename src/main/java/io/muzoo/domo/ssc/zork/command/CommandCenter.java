package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.Game;

import java.lang.reflect.InvocationTargetException;

public class CommandCenter {
    public void checkCommand(CommandType commandType, Game gameState, String arguments) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(commandType == null) {
            System.out.println("Unknown command or no command entered. Use \"help\" to view all available command");
        } else if (commandType.getInGameOnly() != gameState.getInGameState() && gameState.getInGameState()) {
            System.out.println("You can't use this command while you're in a map");
        } else if (commandType.getInGameOnly() != gameState.getInGameState()) {
            System.out.println("You can't use this command while you're outside a map");
        } else {
            commandType.getCommandClass().getDeclaredConstructor().newInstance().run(gameState, arguments);
        }
    }
}

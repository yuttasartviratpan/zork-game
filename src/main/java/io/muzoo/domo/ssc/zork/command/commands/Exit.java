package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.map.Room;

public class Exit extends Command {
    @Override
    public void run(Game gameState, String argument) {
        System.out.println("Terminating the program");
        gameState.setGameRunning(false);
    }
}

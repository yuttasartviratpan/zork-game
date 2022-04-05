package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;

public class Quit extends Command {
    @Override
    public void run(Game gameState, String argument) {
        gameState.setMap(null);
        gameState.setInGameState(false);
        System.out.println("Exited the map successfully");
    }
}

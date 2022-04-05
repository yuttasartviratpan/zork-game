package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;

public class Inventory extends Command {
    @Override
    public void run(Game gameState, String argument) {
        gameState.getPlayer().checkInventory();
    }
}

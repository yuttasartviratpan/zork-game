package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.command.CommandType;

public class Help extends Command {
    @Override
    public void run(Game gameState, String argument) {
        System.out.println("Here are the command lists: ");
        CommandType.helpCommandDescriptionPrinting();
    }
}

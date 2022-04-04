package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.Game;



public abstract class Command {
    //Get the state of the game whether the user is in game or at the start of the game.
    //The game start when the command "play" has been called

    public abstract void run(Game gameState, String argument);


}


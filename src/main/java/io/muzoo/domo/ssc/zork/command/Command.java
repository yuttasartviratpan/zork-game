package io.muzoo.domo.ssc.zork.command;

import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.map.map1.MapOneRule;

public abstract class Command {
    //Get the state of the game whether the user is in game or at the start of the game.
    //The game start when the command "play" has been called
    MapOneRule map;
    Player player;
    ParserAndProcessor parser;
    boolean currentGameStatus;






}

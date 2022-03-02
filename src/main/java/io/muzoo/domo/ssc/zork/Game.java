package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.command.CommandFactory;
import io.muzoo.domo.ssc.zork.command.CommandList;
import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;
import io.muzoo.domo.ssc.zork.map.ZorkMap;
import io.muzoo.domo.ssc.zork.map.map1.MapOne;

import java.util.List;

public class Game {
    ZorkMap map;
    Player player;
    CommandFactory command;
    ParserAndProcessor parser;
    boolean inGame = false; //Is the player in the Map, playing the game.
    CommandList commandList;
    List<String> arguments;


    public Game(){
        map = null;
        player = new Player();
        parser = new ParserAndProcessor();
        command = new CommandFactory(map, player, inGame);
    }

    public void runGame(){
        boolean running = true;
        System.out.println("Welcome to this god awful game. Try saying some command!");
        ParserAndProcessor game_instance = new ParserAndProcessor();
        while(running){
            game_instance.run();
            commandList = game_instance.getCommand();
            arguments = game_instance.getArguments();

            running = command.checkCommand(commandList, arguments);
        }
    }
}

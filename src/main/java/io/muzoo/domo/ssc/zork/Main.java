package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.command.CommandList;
import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        System.out.println("Welcome to this god awful game. Try saying some command!");
        ParserAndProcessor game_instance = new ParserAndProcessor();
        while(running){ //While still running or Player_character is not gameOver or game objective is not over;
            running = game_instance.run();
        }
    }
}

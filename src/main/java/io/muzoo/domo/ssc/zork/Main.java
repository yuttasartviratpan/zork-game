package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;

public class Main {
    public static void main(String[] args) {
//        boolean running = true;
//        System.out.println("Welcome to this god awful game. Try saying some command!");
//        ParserAndProcessor game_instance = new ParserAndProcessor();
//        while(running){ //While still running or Player_character is not gameOver or game objective is not over;
//            game_instance.run();
//            System.out.println("You entered: \"" + game_instance.getCommandString() + "\" command");
//            game_instance.checkArgument();
//            System.out.println();
//            if(game_instance.getCommandString().equals("exit")){
//                running = false;
//            }
//
//        }

//        MapOneRule test = new MapOneRule();
//        test.mapping();
        Game gameInstance = new Game();
        gameInstance.runGame();
    }
}

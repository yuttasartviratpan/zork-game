package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.command.CommandCenter;
import io.muzoo.domo.ssc.zork.command.CommandType;
import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;
import io.muzoo.domo.ssc.zork.map.ZorkMap;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class Game {
    ZorkMap map;
    Player player;
    CommandCenter command;
    ParserAndProcessor parser;
    boolean inGame = false;
    boolean gameRunning = true;
    CommandType commandType;
    String arguments;


    public Game(){
        map = null;
        player = new Player();
        parser = new ParserAndProcessor();
        command = new CommandCenter();
    }

    public boolean getInGameState(){
        return inGame;
    }

    public void setInGameState(boolean state){
        inGame = state;
    }

    public Player getPlayer(){
        return player;
    }

    public ZorkMap getMap(){
        return map;
    }

    public void setMap(ZorkMap theMap){
        map = theMap;
    }

    public void setGameRunning(boolean state){
        gameRunning = state;
    }

    public void runGame(){
        System.out.println("Welcome to this god awful game. Try saying some command!");
        ParserAndProcessor game_instance = new ParserAndProcessor();
        while(gameRunning){
            game_instance.run();
            commandType = game_instance.getCommandType();
            arguments = game_instance.getArguments();
            try{
                command.checkCommand(commandType, this, arguments);
            }catch (NoSuchMethodException e){
                System.out.println("No such method");
                e.printStackTrace();
            }catch (InvocationTargetException e){
                System.out.println("Invocation target exception");
                e.printStackTrace();
            }catch (InstantiationException e){
                System.out.println("Instantiation exception");
                e.printStackTrace();
            }catch (IllegalAccessException e){
                System.out.println("Illegal Access Exception");
                e.printStackTrace();
            }
        }
    }
}

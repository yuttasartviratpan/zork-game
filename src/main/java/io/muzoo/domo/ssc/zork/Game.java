package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.command.CommandCenter;
import io.muzoo.domo.ssc.zork.command.CommandType;
import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;
import io.muzoo.domo.ssc.zork.command.commands.Play;
import io.muzoo.domo.ssc.zork.map.Room;

import java.lang.reflect.InvocationTargetException;

public class Game {
    private ParserAndProcessor parser;
    private boolean inGame = false;
    private boolean gameRunning = true;
    private CommandType commandType;
    private CommandCenter commandCenter = new CommandCenter();
    private String arguments;
    private Room map;
    private Player player;


    public Game(){
        parser = new ParserAndProcessor();
        map = null;
        player = new Player();
    }

    public void setMap(Room map){
        this.map = map;
    }

    public Room getMap(){
        return map;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public boolean getInGameState(){
        return inGame;
    }

    public void setInGameState(boolean state){
        inGame = state;
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
                commandCenter.checkCommand(commandType, this, arguments);
                if(map != null){
                    boolean monsterKilled = map.checkIfMonsterDead();
                    if(monsterKilled){
                        System.out.println("You feel stronger...");
                        player.incrementAtk(2);
                    }
                }
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

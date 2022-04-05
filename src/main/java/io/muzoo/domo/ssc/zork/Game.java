package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.character.MonsterType;
import io.muzoo.domo.ssc.zork.character.Player;
import io.muzoo.domo.ssc.zork.command.CommandCenter;
import io.muzoo.domo.ssc.zork.command.CommandType;
import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;
import io.muzoo.domo.ssc.zork.item.Item;
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
    private Integer objectiveAmount = -1;
    private boolean onceReminder = true;
    private String startingRoom;
    private String mapName;

    public Game(){
        parser = new ParserAndProcessor();
        map = null;
        player = new Player();
    }

    public void setMap(Room map){
        this.map = map;
    }

    public void setMapName(String name){
        mapName = name;
    }

    public String getMapName(){
        return mapName;
    }

    public void setMapPlay(Room map){
        this.map = map;
        startingRoom = map.getRoomName();
    }

    public void setObjectiveAmount(Integer objectiveAmount){
        this.objectiveAmount = objectiveAmount;
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
                    if(map.getMonsterInRoom() != null){
                        if(map.getMonsterInRoom().getMonsterType() == MonsterType.BOSS){
                            Item dropItem = map.getMonsterInRoom().getMonsterDrop();
                            boolean monsterKilled = map.checkIfMonsterDead();
                            if(monsterKilled){
                                System.out.println("You feel accomplished");
                                System.out.println("You acquired " + dropItem.getItemName() + "!!");
                                player.pickUpItem(dropItem);
                                player.incrementAtk(100);
                            }
                        }
                        else{
                            boolean monsterKilled = map.checkIfMonsterDead();
                            if(monsterKilled){
                                System.out.println("You feel stronger...");
                                player.incrementAtk(2);
                            }
                        }
                    }
                    if(player.getCurrentHP() <= 0){
                        System.out.println("You died, game over");
                        gameRunning = false;
                    }
                    if(player.getCollectedKeyItem() == objectiveAmount && !map.getRoomName().equals(startingRoom) && onceReminder){
                        System.out.println("You have collected all key items, let's get out of here. Get back to where you started");
                        onceReminder = false;
                    }
                    else if(player.getCollectedKeyItem() == objectiveAmount && map.getRoomName().equals(startingRoom)){
                        System.out.println("Congratulation. You won");
                        gameRunning = false;
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

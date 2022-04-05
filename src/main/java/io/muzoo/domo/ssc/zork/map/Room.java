package io.muzoo.domo.ssc.zork.map;

//import io.muzoo.domo.ssc.zork.character.Monster;
//import io.muzoo.domo.ssc.zork.item.Item;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.item.Item;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private String roomName;
    private String roomDescription;
    private int floorNum;
    private Map<String, Room> neighbor = new HashMap<>();
    private boolean playerIsHere;
    private Item itemInRoom = null;
    private Monster monsterInRoom = null;


    public Room(String roomName, String roomDescription, int floorNum){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
        this.floorNum = floorNum;
    }

    public void setNeighbor(Room north, Room east, Room west, Room south, Room up, Room down){
        if(north != null){
            neighbor.put("north", north);
        }
        if(east != null){
            neighbor.put("east", east);
        }
        if(west != null){
            neighbor.put("west", west);
        }
        if(south != null){
            neighbor.put("south", south);
        }
        if(up != null){
            neighbor.put("up", up);
        }
        if(down != null){
            neighbor.put("down", down);
        }

    }

    public Room getNeighbor(String direction){
        if(neighbor.containsKey(direction.toLowerCase())){
            return neighbor.get(direction);
        }
        else{
            return null;
        }
    }

    public String getRoomName(){
        return roomName;
    }

    public String getRoomDescription() {return roomDescription;}

    public Monster getMonsterInRoom(){
        return monsterInRoom;
    }

    public Item getItemInRoom(){
        return itemInRoom;
    }

    public void setMonsterInRoom(Monster monsterInRoom){
        this.monsterInRoom = monsterInRoom;
    }

    public void setItemInRoom(Item itemInRoom){
        this.itemInRoom = itemInRoom;
    }

    public void setPlayerIsHere(boolean playerIsHere){
        this.playerIsHere = playerIsHere;
    }

    public void printAvailablePath(){
        System.out.println("The available paths are: ");
        for(String direction : neighbor.keySet()){
            System.out.println(direction.toUpperCase() + ": " + neighbor.get(direction).getRoomName());
        }
    }

}

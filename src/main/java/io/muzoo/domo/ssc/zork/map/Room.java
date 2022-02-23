package io.muzoo.domo.ssc.zork.map;

import java.util.HashMap;
import java.util.Map;

public abstract class Room {
    String roomName;
    String roomDescription;
    boolean isPlayerCurrentlyIn;
    Map<String, Room> nextRoom;

    public void getRoomInfo() {
        System.out.println(roomDescription);
        if(nextRoom.isEmpty()){
            System.out.println("There are no doors, this room is a dead end");
        }
        else{
            System.out.print("The available paths are:");
            for(Map.Entry<String, Room> possibleDoor : nextRoom.entrySet()){
                System.out.print(" " + possibleDoor.getKey());
            }
            System.out.print(".\n");
        }
    }


    public void setNextRoom(Room northRoom, Room southRoom, Room westRoom, Room eastRoom) {
        nextRoom.put("north", northRoom);
        nextRoom.put("south", southRoom);
        nextRoom.put("west", westRoom);
        nextRoom.put("east", eastRoom);
    }

    public void setPlayerIsInRoom(boolean isInRoom) {
        isPlayerCurrentlyIn = isInRoom;
    }

    public boolean isPlayerInTheRoom() {
        return isPlayerCurrentlyIn;
    }

}

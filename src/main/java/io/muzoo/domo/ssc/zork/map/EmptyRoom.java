package io.muzoo.domo.ssc.zork.map;

import java.util.Map;

public class EmptyRoom implements Room{
    String roomDescription;
    boolean isPlayerCurrentlyIn;

    EmptyRoom(){
        roomDescription = "This is an empty room.";
    }


    @Override
    public void getRoomInfo() {
        System.out.println(roomDescription);
        if(door.isEmpty()){
            System.out.println("There are no doors, this room is a dead end");
        }
        else{
            System.out.print("The available paths are:");
            for(Map.Entry<String, Room> possibleDoor : door.entrySet()){
                System.out.print(" " + possibleDoor.getKey());
            }
            System.out.print(".\n");
        }
    }

    @Override
    public void setNextRoom(String direction, Room room) {
        door.put(direction, room);
    }

    @Override
    public void setPlayerIsInRoom(boolean isInRoom) {
        isPlayerCurrentlyIn = isInRoom;
    }

    @Override
    public boolean isPlayerInTheRoom() {
        return isPlayerCurrentlyIn;
    }
}

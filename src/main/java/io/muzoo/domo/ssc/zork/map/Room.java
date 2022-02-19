package io.muzoo.domo.ssc.zork.map;

import java.util.HashMap;
import java.util.Map;

public interface Room {

    boolean isPlayerCurrentlyIn = false; //During the play, is the player currently in this room right now
    Map<String, Room> door = new HashMap<>();


    public void getRoomInfo();


    void setNextRoom(String direction, Room room);
    void setPlayerIsInRoom(boolean isInRoom);
    boolean isPlayerInTheRoom();


}

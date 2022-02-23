package io.muzoo.domo.ssc.zork.map;

import java.util.Map;

public class EmptyRoom extends Room{
    String roomName;
    String roomDescription;
    boolean isPlayerCurrentlyIn;

    public EmptyRoom(String roomName, String roomDescription){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }


}

package io.muzoo.domo.ssc.zork.map;

import java.util.HashMap;
import java.util.Map;

public interface Room {
    boolean monsterExist = false;
    boolean itemExist = false;
    Map<String, Room> door = new HashMap<>();


    public void getRoomInfo();

    void setMonster();
    void setItem();
    void setNextRoom(String direction, Room room);

}

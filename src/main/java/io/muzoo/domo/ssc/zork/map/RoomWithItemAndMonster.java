package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.item.Item;

public class RoomWithItemAndMonster extends Room{


    public RoomWithItemAndMonster(String roomName, String roomDescription){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    public void setItemAndMonster(Item item, Monster monster){
        this.item = item;
        this.monster = monster;
    }

}

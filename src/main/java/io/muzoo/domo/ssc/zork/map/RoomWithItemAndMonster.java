package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.item.Item;

public class RoomWithItemAndMonster extends Room{
    String roomName;
    String roomDescription;
    boolean isPlayerCurrentlyIn;
    Monster monster;
    Item item;

    public RoomWithItemAndMonster(String roomName, String roomDescription){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    public void setItemAndMonster(Item item, Monster monster){
        this.item = item;
        this.monster = monster;
    }

    public Monster getMonster(){
        return monster;
    }

    public Item getItem(){
        return item;
    }
}

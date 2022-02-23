package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.item.Item;

public class RoomWithItem extends Room{
    String roomName;
    String roomDescription;
    boolean isPlayerCurrentlyIn;
    Item item;

    public RoomWithItem(String roomName, String roomDescription){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public Item getItem(){
        return item;
    }
}

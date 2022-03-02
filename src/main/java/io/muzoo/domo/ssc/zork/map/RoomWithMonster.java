package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;

public class RoomWithMonster extends Room{
    Monster monster;

    public RoomWithMonster(String roomName, String roomDescription){
        this.roomName = roomName;
        this.roomDescription = roomDescription;
    }

    public void setMonster(Monster monster){
        this.monster = monster;
    }

    public Monster getMonster(){
        return monster;
    }




}

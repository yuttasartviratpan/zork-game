package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;

public class RoomWithMonster implements Room{
    Monster monster;

    @Override
    public void getRoomInfo() {

    }

    @Override
    public void setNextRoom(String direction, Room room) {

    }

    @Override
    public void setPlayerIsInRoom(boolean isInRoom) {

    }

    @Override
    public boolean isPlayerInTheRoom() {
        return false;
    }

    private void setMonster(Monster monster){
        this.monster = monster;
    }




}

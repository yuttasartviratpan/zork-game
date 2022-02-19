package io.muzoo.domo.ssc.zork.map;

public class EmptyRoom implements Room{
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
}

package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.map.map1.MapOneGeneration;

import java.util.List;

public interface ZorkMap {
    public Room getCurrentRoom();
    public void moving(Room leaveRoom, Room goToRoom);
    public void mapping();

    public Monster getMonsterInCurrentRoom();
    public Item getItemInCurrentRoom();
}

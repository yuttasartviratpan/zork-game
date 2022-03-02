package io.muzoo.domo.ssc.zork.map.map1;

import io.muzoo.domo.ssc.zork.map.Room;
import io.muzoo.domo.ssc.zork.map.ZorkMap;

import java.util.List;

public class MapOne implements ZorkMap {
    //Responsible for managing the victory/defeat condition of the player based on the map
    //Ex: Collect all 15 object in this map to win. Or maybe defeat all monster to win, etc.

    MapOneGeneration map = new MapOneGeneration();
    Room currentRoom;

    public MapOne(){
        map.generator();
        currentRoom = map.startBlock;
    }

    @Override
    public Room getCurrentRoom(){
        return currentRoom;
    }

    @Override
    public void moving(Room leaveRoom, Room goToRoom){
        map.playerLeavesRoom(leaveRoom);
        map.playerMoveIntoRoom(goToRoom);
        currentRoom = goToRoom;
    }

    @Override
    public void mapping(){
        System.out.println("Key/Legacy:");
        System.out.println(" X = You're here");
        System.out.println(" | = North/South path available");
        System.out.println(" - = West/East path available");
        System.out.println(" 0 = Connects to other floor (ladder)");
        System.out.println();
        System.out.println("First floor: ");
        mapRender(map.firstFloorRoom);
        System.out.println("###############");
        System.out.println("Second floor: ");
        mapRender(map.secondFloorRoom);
    }

    private void mapRender(List<Room> floor){
        int topPart = 0;
        int middlePart = 0;
        int bottomPart = 0;
        while(topPart != 5){
            for(int i = 0; i < 3; i++){
                if(floor.get(i)==null){
                    System.out.print("     ");
                }
                else{
                    floor.get(i).drawMapAt(topPart);
                }
            }
            System.out.print("\n");
            topPart += 1;
        }
        while(middlePart != 5){
            for(int i = 3; i < 6; i++){
                if(floor.get(i)==null){
                    System.out.print("     ");
                }
                else{
                    floor.get(i).drawMapAt(middlePart);
                }
            }
            System.out.print("\n");
            middlePart += 1;
        }
        while(bottomPart != 5){
            for(int i = 6; i < 9; i++){
                if(floor.get(i)==null){
                    System.out.print("     ");
                }
                else{
                    floor.get(i).drawMapAt(bottomPart);
                }
            }
            System.out.print("\n");
            bottomPart += 1;
        }
    }
}

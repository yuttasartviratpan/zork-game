package io.muzoo.domo.ssc.zork.map;

import java.util.*;

public abstract class Room {
    String roomName;
    String roomDescription;
    boolean isPlayerCurrentlyIn;
    String connectsToElevatedRoomDirection = null;
    Map<String, Room> nextRoom = new HashMap<>();

    public void getRoomInfo() {
        System.out.println(roomDescription);
        if(nextRoom.isEmpty()){
            System.out.println("There are no doors, this room is a dead end");
        }
        else{
            System.out.print("The available paths are:");
            for(Map.Entry<String, Room> possibleDoor : nextRoom.entrySet()){
                System.out.print(" " + possibleDoor.getKey());
            }
            System.out.print(".\n");
        }
    }

    public void setConnectsToElevatedRoom(String direction) {
        connectsToElevatedRoomDirection = direction;
    }

    private boolean isConnectedToElevatedRoomAtThatDirection(String direction){
        if(connectsToElevatedRoomDirection == null){
            return false;
        }
        else return connectsToElevatedRoomDirection.equalsIgnoreCase(direction);
    }

    public Room getRoom(String direction){
        String newDirection = direction.toLowerCase();
        return nextRoom.get(newDirection);
    }

    public String getRoomName(){
        return roomName;
    }

    public void setNextRoom(Room northRoom, Room southRoom, Room westRoom, Room eastRoom) {
        nextRoom.put("north", northRoom);
        nextRoom.put("south", southRoom);
        nextRoom.put("west", westRoom);
        nextRoom.put("east", eastRoom);
    }

    public void setPlayerIsInRoom(boolean isInRoom) {
        isPlayerCurrentlyIn = isInRoom;
    }

    public boolean isPlayerInTheRoom() {
        return isPlayerCurrentlyIn;
    }

    public void drawMapAt(int index) {
        List<List<String>> mapping = drawMap();
        for(String i : mapping.get(index)){
            System.out.print(i);
        }
    }

    //A really stupidly memory inefficient way of drawing map
    private List<List<String>> drawMap(){ //In total, each room should take 5 by 5 space

        List<List<String>> mapRoom = new ArrayList<>(); //
        List<String> structure = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(i == 0) {
                    if(j == 2 && nextRoom.get("north") != null &&
                            isConnectedToElevatedRoomAtThatDirection("north")) {
                        structure.add("0");
                    }
                    else if(j == 2 && nextRoom.get("north") != null){
                        structure.add("|");
                    }
                    else {
                        structure.add(" ");
                    }
                }
                else if(i == 1 || i == 3) {
                    if(j == 0 || j == 4) {
                        structure.add(" ");
                    }
                    else {
                        structure.add("-");
                    }
                }
                else if(i == 2) {
                    if(j == 0){
                        if(nextRoom.get("west") != null &&
                                isConnectedToElevatedRoomAtThatDirection("west")){
                            structure.add("0");
                        }
                        else if((nextRoom.get("west") != null)){
                            structure.add("-");
                        }
                        else{
                            structure.add(" ");
                        }
                    }
                    if(j == 1 || j == 3){
                        structure.add("|");
                    }
                    if(j == 2){
                        if(isPlayerCurrentlyIn){
                            structure.add("X");
                        }
                        else{
                            structure.add(" ");
                        }
                    }
                    if(j == 4){
                        if(nextRoom.get("east") != null &&
                                isConnectedToElevatedRoomAtThatDirection("east")){
                            structure.add("0");
                        }
                        else if(nextRoom.get("east") != null){
                            structure.add("-");
                        }
                        else{
                            structure.add(" ");
                        }
                    }
                }
                else {
                    if(j == 2 && nextRoom.get("south") != null &&
                            isConnectedToElevatedRoomAtThatDirection("south")) {
                        structure.add("0");
                    }
                    else if(j == 2 && nextRoom.get("south") != null){
                        structure.add("|");
                    }
                    else {
                        structure.add(" ");
                    }
                }
            }
            mapRoom.add(structure);
            structure = new ArrayList<>();
        }
        return mapRoom;
    }


}

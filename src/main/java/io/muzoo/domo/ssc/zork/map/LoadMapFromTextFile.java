package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.character.MonsterType;
import io.muzoo.domo.ssc.zork.item.Item;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LoadMapFromTextFile {
    File newFile;
    Map<String, Room> roomStorage = new HashMap<>();

    public LoadMapFromTextFile(String filepath){
        //Just use absolute path as an input
        newFile = new File(filepath);
    }

    private Item randomItem(){
        Random random = new Random();
        int result = random.nextInt(101);
        if(result >= 0 && result <= 49){
            return null;
        }
        else if(result >= 50 && result <= 69){
            return Item.HEALTH_POTION;
        }
        else if(result >= 70 && result <= 89){
            return Item.THROWING_KNIFE;
        }
        else if(result >= 90 && result <= 93){
            return Item.WOODEN_CLUB;
        }
        else if(result >= 94 && result <= 96){
            return Item.FRYING_PAN;
        }
        else if(result >= 97 && result <= 99){
            return Item.SHORT_SWORD;
        }
        else{
            return Item.INSTAKILL_SWORD;
        }
    }

    private Monster randomMonster(){
        Random random = new Random();
        int result = random.nextInt(101);
        if(result >= 0 && result <= 49){
            return null;
        }
        else if(result >= 50 && result <= 69){
            return new Monster(MonsterType.SLIME);
        }
        else if(result >= 70 && result <= 89){
            return new Monster(MonsterType.SNAKE);
        }
        else if(result >= 90 && result <= 99){
            return new Monster(MonsterType.SNAKE);
        }
        else{
            return new Monster(MonsterType.WOLF);
        }
    }

    private Room roomGet(String roomName){
        return roomStorage.get(roomName);
    }

    private void parsingData(){
        try(BufferedReader br = new BufferedReader(new FileReader(newFile))){
            String text;
            boolean mapMode = true; //True is map-mode, false is neighbor mode
            while((text = br.readLine()) != null){
                if(text.equals(".map")){
                    mapMode = true;
                    continue;
                }
                if(text.equals(".neighbor")){
                    mapMode = false;
                    continue;
                }
                if(mapMode){
                    //trimmed String: RoomName\.RoomDescription\.RoomFloor
                    String[] parsedText = text.split("\\\\.");
                    Room newRoom = new Room(parsedText[0], parsedText[1], Integer.parseInt(parsedText[2]));
                    //TODO: SetItem (Randomly?) And SetMonster (Randomly?)
                    newRoom.setItemInRoom(randomItem());
                    newRoom.setMonsterInRoom(randomMonster());
                    roomStorage.put(parsedText[0], newRoom);
                }
                else{
                    //trimmed String: RoomName\.NorthRoomName\.EastRoomName\.WestRoomName\.SouthRoomName\.UpRoomName\.DownRoomName
                    String[] parsedText = text.split("\\\\.");
                    for(int i = 0; i<parsedText.length; i++){
                        if(parsedText[i].equals("NULL")){
                            parsedText[i] = null;
                        }
                    }
                    Room room = roomStorage.get(parsedText[0]);
                    room.setNeighbor(roomGet(parsedText[1]), roomGet(parsedText[2]), roomGet(parsedText[3]), roomGet(parsedText[4]),
                            roomGet(parsedText[5]), roomGet(parsedText[6]));
                    roomStorage.put(parsedText[0], room);
                }

            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Map<String, Room> getRooms(){
        parsingData();
        return roomStorage;
    }

}

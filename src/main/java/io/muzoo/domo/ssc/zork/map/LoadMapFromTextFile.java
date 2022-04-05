package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.character.MonsterType;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.item.ItemType;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class LoadMapFromTextFile {
    File newFile;
    Map<String, Room> roomStorage = new HashMap<>();
    String startingRoom;
    Integer objectiveAmount = 0;

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
            int mode = 0; //0 = mapMode, 1 = neighborMode, 2 = startingMode
            while((text = br.readLine()) != null){
                text = text.trim(); //May cause problem.
                if(text.equals(".map")){
                    mode = 1;
                    continue;
                }
                if(text.equals(".neighbor")){
                    mode = 2;
                    continue;
                }
                if(text.equals(".starting")){
                    mode = 3;
                    continue;
                }
                if(text.equals(".setCustomItemAndMonster")){
                    mode = 4;
                    continue;
                }
                if(text.equals(".collectObjective")){
                    mode = 5;
                    continue;
                }
                if(mode == 1){
                    //trimmed String: RoomName\.RoomDescription\.RoomFloor
                    String[] parsedText = text.split("\\\\.");
                    Room newRoom = new Room(parsedText[0], parsedText[1], Integer.parseInt(parsedText[2]));
                    //TODO: SetItem (Randomly?) And SetMonster (Randomly?)
                    newRoom.setItemInRoom(randomItem());
                    newRoom.setMonsterInRoom(randomMonster());
                    roomStorage.put(parsedText[0], newRoom);
                }
                else if(mode == 2){
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
                else if(mode == 3){
                    startingRoom = text;
                }
                else if(mode == 4){
                    String[] parsedText = text.split("\\\\.");
                    Monster monster;
                    Item item;
                    if(parsedText[0].equalsIgnoreCase("monster")){
                        switch (parsedText[1]){
                            case "BOSS":
                                try{
                                    Item dropItem = Item.KEY_ITEM2;
                                    dropItem.setItemName(parsedText[3]);
                                    dropItem.setItemDescription(parsedText[4]);
                                    MonsterType boss = MonsterType.BOSS;
                                    boss.setMonsterDrop(dropItem);
                                    customMonsterSetting(boss, parsedText[2]);
                                } catch (IndexOutOfBoundsException e){
                                    throw new RuntimeException("BossDropUnspecifiedException");
                                }
                                break;
                            case "SLIME":
                                customMonsterSetting(MonsterType.SLIME, parsedText[2]);
                                break;
                            case "SNAKE":
                                customMonsterSetting(MonsterType.SNAKE, parsedText[2]);
                                break;
                            case "WOLF":
                                customMonsterSetting(MonsterType.WOLF, parsedText[2]);
                                break;
                            default:
                                throw new RuntimeException("MapFileSyntaxErrorException");
                        }
                    }
                    else{
                        switch (parsedText[1]){
                            case "WOODEN_CLUB":
                                customItemSetting(Item.WOODEN_CLUB, parsedText[2]);
                                break;
                            case "FRYING_PAN":
                                customItemSetting(Item.FRYING_PAN, parsedText[2]);
                                break;
                            case "SHORT_SWORD":
                                customItemSetting(Item.SHORT_SWORD, parsedText[2]);
                                break;
                            case "INSTAKILL_SWORD":
                                customItemSetting(Item.INSTAKILL_SWORD, parsedText[2]);
                                break;
                            case "HEALTH_POTION":
                                customItemSetting(Item.HEALTH_POTION, parsedText[2]);
                                break;
                            case "THROWING_KNIFE":
                                customItemSetting(Item.THROWING_KNIFE, parsedText[2]);
                                break;
                            case "KEY_ITEM":
                                Item keyItem = Item.KEY_ITEM;
                                keyItem.setItemName(parsedText[3]);
                                keyItem.setItemDescription(parsedText[4]);
                                customItemSetting(keyItem, parsedText[2]);
                                break;
                            default:
                                throw new RuntimeException("MapFileSyntaxErrorException");
                        }
                    }
                }
                else{
                    objectiveAmount = Integer.parseInt(text);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private void customMonsterSetting(MonsterType monsterType, String room){
        if(roomStorage.containsKey(room)){
            roomStorage.get(room).setMonsterInRoom(new Monster(monsterType));
        }
    }

    private void customItemSetting(Item item, String room){
        if(roomStorage.containsKey(room)){
            roomStorage.get(room).setItemInRoom(item);
        }
    }


    public List<Object> getRoom(){
        parsingData();
        //Return the starting room, where you want player to start
        return List.of(objectiveAmount, roomStorage.get(startingRoom));
    }



}

package io.muzoo.domo.ssc.zork.map.map1;

import io.muzoo.domo.ssc.zork.map.*;

import java.util.ArrayList;
import java.util.List;

//Maybe do this as a list, do that you can draw a map with it
public class MapOneGeneration {
    protected List<Room> firstFloorRoom = new ArrayList<>(9);
    protected List<Room> secondFloorRoom = new ArrayList<>(9);

    //Room name
    String start = "The gate";
    String westGarden = "The west garden";
    String eastGarden = "The east garden";
    String mainHall = "The main hall";
    String livingRoom = "The living room";
    String diningRoom = "The dining room";
    String kitchen = "The kitchen";
    String storageRoom = "The kitchen's storage room";
    String secondFloor = "The ladder to second floor";
    //---2nd floor
    String westHallway = "The west hallway";
    String eastHallway = "The east hallway";
    String northHallway = "The start north hallway";
    String endNorthHallway = "The end north hallway";
    String scottRoom = "Scott's room";
    String mayaRoom = "Maya's room";
    String rohanRoom = "Rohan's room";
    String abenaRoom = "Abena's room";

    //Room description
    String desStart = "The big iron gates that separates the abandoned house from the road. This is where you start";
    String desWestGarden = "A garden which seems to have variety of vegetables and fruits, sadly they're all withered";
    String desEastGarden = "A garden which suits to be a place for tea time talk. Now there's only a ruined marble tables";
    String desMainHall = "A big hall that welcomes guests, at least that's what it's suppose to be for";
    String desLivingRoom = "A large living room, it's so spacious that you look like a child compared to it";
    String desDiningRoom = "What seems to be a dining area, you almost can't tell if there isn't a broken dishes around";
    String desKitchen = "Kitchen, filled with moss and strange rotten smell";
    String desStorageRoom = "Kitchen storage room, your nose might give up if you stay here for too long";
    String desSecondFloor = "A wide ladder that leads to second floor, are you sure it's stable?";
    //---2nd floor
    String desWestHallway = "The west hallway of second floor";
    String desEastHallway = "The east hallway, of second floor";
    String desNorthHallway = "The first-half section of north hallway, of second floor";
    String desEndNorthHallway = "The second-half section of north hallway, of second floor";
    String desScottRoom = "A room. There is a name plate saying \"Scott\"";
    String desMayaRoom = "A room. There is a name plate saying \"Maya\"";
    String desRohanRoom = "A room. There is a name plate saying \"Rohan\"";
    String desAbenaRoom = "A room. There is a name plate saying \"Abena\"";

    //Room generation
    EmptyRoom startBlock;
    RoomWithItem westGardenBlock;
    EmptyRoom eastGardenBlock;
    RoomWithMonster mainHallBlock;
    RoomWithItemAndMonster livingRoomBlock;
    RoomWithMonster diningRoomBlock;
    RoomWithMonster kitchenRoomBlock;
    RoomWithItem storageRoomBlock;
    EmptyRoom secondFloorBlock;
    //2nd floor
    EmptyRoom westHallwayBlock;
    RoomWithItem eastHallwayBlock;
    EmptyRoom northHallwayBlock;
    RoomWithMonster endNorthHallwayBlock;
    RoomWithItemAndMonster scottRoomBlock;
    RoomWithItemAndMonster mayaRoomBlock;
    RoomWithItemAndMonster rohanRoomBlock;
    RoomWithItemAndMonster abenaRoomBlock;

    public MapOneGeneration(){
        //Room generation
        startBlock = new EmptyRoom(start, desStart);
        westGardenBlock = new RoomWithItem(westGarden, desWestGarden);
        eastGardenBlock = new EmptyRoom(eastGarden, desEastGarden);
        mainHallBlock = new RoomWithMonster(mainHall, desMainHall);
        livingRoomBlock = new RoomWithItemAndMonster(livingRoom, desLivingRoom);
        diningRoomBlock = new RoomWithMonster(diningRoom, desDiningRoom);
        kitchenRoomBlock = new RoomWithMonster(kitchen, desKitchen);
        storageRoomBlock = new RoomWithItem(storageRoom, desStorageRoom);
        secondFloorBlock = new EmptyRoom(secondFloor, desSecondFloor);
        //2nd floor
        westHallwayBlock = new EmptyRoom(westHallway, desWestHallway);
        eastHallwayBlock = new RoomWithItem(eastHallway, desEastHallway);
        northHallwayBlock = new EmptyRoom(northHallway, desNorthHallway);
        endNorthHallwayBlock = new RoomWithMonster(endNorthHallway, desEndNorthHallway);
        scottRoomBlock = new RoomWithItemAndMonster(scottRoom, desScottRoom);
        mayaRoomBlock = new RoomWithItemAndMonster(mayaRoom, desMayaRoom);
        rohanRoomBlock = new RoomWithItemAndMonster(rohanRoom, desRohanRoom);
        abenaRoomBlock = new RoomWithItemAndMonster(abenaRoom, desAbenaRoom);

        startBlock.setPlayerIsInRoom(true);
    }

    public void generator(){
        roomConnection();

        //FirstFloor
        firstFloorRoom.add(0, kitchenRoomBlock);
        firstFloorRoom.add(1, storageRoomBlock);
        firstFloorRoom.add(2, null);
        firstFloorRoom.add(3, diningRoomBlock); //3
        firstFloorRoom.add(4, mainHallBlock);
        firstFloorRoom.add(5, livingRoomBlock);
        firstFloorRoom.add(6, westGardenBlock);
        firstFloorRoom.add(7, startBlock);
        firstFloorRoom.add(8, eastGardenBlock);

        //SecondFloor
        secondFloorRoom.add(0, mayaRoomBlock);
        secondFloorRoom.add(1, endNorthHallwayBlock);
        secondFloorRoom.add(2, rohanRoomBlock);
        secondFloorRoom.add(3, scottRoomBlock);
        secondFloorRoom.add(4, northHallwayBlock);
        secondFloorRoom.add(5, abenaRoomBlock);
        secondFloorRoom.add(6, westHallwayBlock);
        secondFloorRoom.add(7, secondFloorBlock);
        secondFloorRoom.add(8, eastHallwayBlock);

        //itemAndMonsterSpawning();
    }

    //Room connection
    private void roomConnection(){
        startBlock.setNextRoom(mainHallBlock, null, westGardenBlock, eastGardenBlock);
        westGardenBlock.setNextRoom(null, null, null, startBlock);
        eastGardenBlock.setNextRoom(null, null, startBlock, null);
        mainHallBlock.setNextRoom(secondFloorBlock, startBlock, diningRoomBlock, livingRoomBlock);
        mainHallBlock.setConnectsToElevatedRoom("north");
        livingRoomBlock.setNextRoom(null, null, mainHallBlock, null);
        diningRoomBlock.setNextRoom(kitchenRoomBlock, null, null, mainHallBlock);
        kitchenRoomBlock.setNextRoom(null, diningRoomBlock, null, storageRoomBlock);
        storageRoomBlock.setNextRoom(null, null, kitchenRoomBlock, null);
        secondFloorBlock.setNextRoom(northHallwayBlock, mainHallBlock, westHallwayBlock, eastHallwayBlock);
        secondFloorBlock.setConnectsToElevatedRoom("south");
        //2nd floor
        westHallwayBlock.setNextRoom(scottRoomBlock, null, null, secondFloorBlock);
        eastHallwayBlock.setNextRoom(abenaRoomBlock, null, secondFloorBlock, null);
        northHallwayBlock.setNextRoom(endNorthHallwayBlock, secondFloorBlock, scottRoomBlock, abenaRoomBlock);
        endNorthHallwayBlock.setNextRoom(null, northHallwayBlock, mayaRoomBlock, rohanRoomBlock);
        scottRoomBlock.setNextRoom(null, westHallwayBlock, null, northHallwayBlock);
        mayaRoomBlock.setNextRoom(null, null, null, endNorthHallwayBlock);
        rohanRoomBlock.setNextRoom(null, null, endNorthHallwayBlock, null);
        abenaRoomBlock.setNextRoom(null, eastHallwayBlock, northHallwayBlock, null);
    }

    /*
    private void itemAndMonsterSpawning(){
        //Room with item
        westGardenBlock.setItem();
        storageRoomBlock.setItem();
        eastHallwayBlock.setItem();

        //Room with monster
        mainHallBlock.setMonster((new Monster()));
        diningRoomBlock.setMonster((new Monster()));
        kitchenRoomBlock.setMonster((new Monster()));
        endNorthHallwayBlock.setMonster((new Monster()));

        //Room with both
        livingRoomBlock.setItemAndMonster(, (new Monster()));
        scottRoomBlock.setItemAndMonster(, (new Monster()));
        mayaRoomBlock.setItemAndMonster(, (new Monster()));
        rohanRoomBlock.setItemAndMonster(, (new Monster()));
        abenaRoomBlock.setItemAndMonster(, (new Monster()));
    }
    */

    public void playerMoveIntoRoom(Room room){
        room.setPlayerIsInRoom(true);
    }

    public void playerLeavesRoom(Room room){
        room.setPlayerIsInRoom(false);
    }

}

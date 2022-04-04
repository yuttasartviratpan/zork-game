package io.muzoo.domo.ssc.zork;

//import io.muzoo.domo.ssc.zork.command.ParserAndProcessor;
import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.character.MonsterType;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.map.LoadMapFromTextFile;
import io.muzoo.domo.ssc.zork.map.Room;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Room> test = new LoadMapFromTextFile("/home/pong/zork/roomFile/map1.txt").getRooms();
        for(String roomName : test.keySet()){
            if(test.get(roomName).getItemInRoom() != null){
                System.out.println(test.get(roomName).getItemInRoom().getItemName());
            }
            else{
                System.out.println("No item");
            }
        }
//        Monster a = new Monster(MonsterType.SLIME);
//        Monster b = new Monster(MonsterType.SLIME);
//        //a.statsInfo();
//        //b.statsInfo();
//        for(int i = 0; i < 20; i++){
//            System.out.println(Item.randomItem());
//        }
        //System.out.println(test.get("Main Lobby").getRoomName());
        //System.out.println(test.get("Main Lobby").getRoomDescription());
        //test.get("Main Lobby").printAvailablePath();
        //Game gameInstance = new Game();
        //gameInstance.runGame();
    }
}

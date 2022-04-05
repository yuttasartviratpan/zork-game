package io.muzoo.domo.ssc.zork;

import java.util.HashMap;
import java.util.Map;

//Edit this to be your absolute path, just to make sure
public class MapFilePathing {
    Map<String, String> pathToFile = new HashMap<>();

    public MapFilePathing(){
        pathToFile.put("map1", "/home/pong/zork/roomFile/map1.txt");
    }

    public String getMap(String mapName){
        return pathToFile.getOrDefault(mapName, null);
    }

}

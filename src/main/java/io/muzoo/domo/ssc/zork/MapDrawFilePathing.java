package io.muzoo.domo.ssc.zork;

import java.util.HashMap;
import java.util.Map;

public class MapDrawFilePathing {
    Map<String, String> pathToFile = new HashMap<>();

    public MapDrawFilePathing(){
        pathToFile.put("map1", "/home/pong/zork/roomFile/map1_map.txt");
    }

    public String getMap(String mapName){
        return pathToFile.getOrDefault(mapName, null);
    }
}

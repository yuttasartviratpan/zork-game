package io.muzoo.domo.ssc.zork.map;
import io.muzoo.domo.ssc.zork.map.map1.MapOne;
import java.util.HashMap;
import java.util.Map;


public class AvailableMap {
    Map<String, ZorkMap> allMap = new HashMap<>();

    public AvailableMap(){
        allMap.put("map-one",new MapOne());
    }

    public Map<String, ZorkMap> getAllMap(){
        return allMap;
    }
}

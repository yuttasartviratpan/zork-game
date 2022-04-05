package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.MapDrawFilePathing;
import io.muzoo.domo.ssc.zork.command.Command;
import io.muzoo.domo.ssc.zork.map.MapRenderer;

public class Map extends Command {
    @Override
    public void run(Game gameState, String argument) {
        String mapPath = (new MapDrawFilePathing()).getMap(gameState.getMapName());
        if(mapPath != null){
            MapRenderer mapRenderer = new MapRenderer(mapPath, gameState.getMap().getFloorNum());
            String floorLevel;
            if(gameState.getMap().getFloorNum() == -1){
                floorLevel = "underground";
            }
            else if(gameState.getMap().getFloorNum() == 1){
                floorLevel = "2nd";
            }
            else{
                floorLevel = "ground";
            }
            System.out.println("You are currently at: " + gameState.getMap().getRoomName() + ", on the " + floorLevel + " floor");
            mapRenderer.parsingData();
        }
        else{
            System.out.println("Cannot output mapping of this map");
        }
    }
}

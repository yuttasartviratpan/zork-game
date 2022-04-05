package io.muzoo.domo.ssc.zork.map;

import io.muzoo.domo.ssc.zork.character.Monster;
import io.muzoo.domo.ssc.zork.character.MonsterType;
import io.muzoo.domo.ssc.zork.item.Item;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapRenderer {
    File newFile;
    int floor;

    public MapRenderer(String filepath, int floor){
        //Just use absolute path as an input
        newFile = new File(filepath);
        this.floor = floor;
    }

    private boolean stringParseIntoIntAble(String string){
        try {
            Integer integer = Integer.parseInt(string);
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public void parsingData(){
        try(BufferedReader br = new BufferedReader(new FileReader(newFile))){
            String text;
            boolean drawer = false;
            while((text = br.readLine()) != null){
                if(stringParseIntoIntAble(text)){
                    if(text.equals(String.valueOf(floor))){
                        drawer = true;
                        continue;
                    }
                    else{
                        drawer = false;
                        continue;
                    }
                }
                if(drawer){
                    System.out.println(text);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}

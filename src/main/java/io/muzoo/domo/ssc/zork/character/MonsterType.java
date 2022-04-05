package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.Item;

import java.util.Random;

public enum MonsterType {
    SLIME("slime", "A pool of substance that looks alive, it erodes your skin", null),
    SNAKE("snake", "A limbless, carnivorous reptiles. It doesn't look like it's poisonous", null),
    WOLF("wolf", "A canis lupus. Beware if it's ferocity", null),
    BOSS("boss", "A bandit that searching for valuables. He is armed to the teeth, be careful.", Item.KEY_ITEM);


    private String monsterName;
    private String monsterDescription;
    private Item monsterDrop;

    MonsterType(String monsterName, String monsterDescription, Item monsterDrop){
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
        this.monsterDrop = monsterDrop;
    }

    public String getMonsterName(){
        return monsterName;
    }

    public String getMonsterDescription(){
        return monsterDescription;
    }

    public void setMonsterDrop(Item item){
        this.monsterDrop = item;
    }

    public Item getMonsterDrop(){
        return monsterDrop;
    }

}

package io.muzoo.domo.ssc.zork.character;

import java.util.Random;

public enum MonsterType {
    SLIME("slime", "A pool of substance that looks alive, it erodes your skin"),
    SNAKE("snake", "A limbless, carnivorous reptiles. It doesn't look like it's poisonous"),
    WOLF("wolf", "A canis lupus. Beware if it's ferocity"),
    BOSS("boss", "A bandit that searching for valuables. He is armed to the teeth, be careful.");

    /*
        MaxHP value for monsters:
            - Slime: 1-10
            - (Non-venomous) Snakes: 10 - 20
            - Wolves: 20 - 30
            - [Boss] Thief: 100
     */

    /*
        Atk value for monsters:
            - Slime: 1-5
            - (Non-venomous) Snakes: 5-10
            - Wolves: 10-15
            - [Boss] Thief: 20
     */


    private String monsterName;
    private String monsterDescription;

    MonsterType(String monsterName, String monsterDescription){
        this.monsterName = monsterName;
        this.monsterDescription = monsterDescription;
    }

    public String getMonsterName(){
        return monsterName;
    }

    public String getMonsterDescription(){
        return monsterDescription;
    }


}

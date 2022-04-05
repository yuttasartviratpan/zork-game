package io.muzoo.domo.ssc.zork.character;

//import io.muzoo.domo.ssc.zork.item.ItemWeapon;
import java.util.Random;
public class Monster{
    Random randomizer = new Random();
    boolean isDead = false;
    String monsterName;
    String monsterDescription;
    int monsterAtk;
    int monsterMaxHP;
    int currentHP;

    private int hpRandomizer(MonsterType monsterType){
        if(monsterType == MonsterType.SLIME){
            return randomizer.nextInt(10) + 1;
        }
        else if(monsterType == MonsterType.SNAKE){
            return randomizer.nextInt(11) + 10;
        }
        else if(monsterType == MonsterType.WOLF){
            return randomizer.nextInt(11) + 20;
        }
        else{
            return 100;
        }
    }

    private int atkRandomizer(MonsterType monsterType){
        if(monsterType == MonsterType.SLIME){
            return randomizer.nextInt(5) + 1;
        }
        else if(monsterType == MonsterType.SNAKE){
            return randomizer.nextInt(6) + 5;
        }
        else if(monsterType == MonsterType.WOLF){
            return randomizer.nextInt(6) + 10;
        }
        else{
            return 20;
        }
    }

    public Monster(MonsterType monsterType){
        monsterName = monsterType.getMonsterName();
        monsterDescription = monsterType.getMonsterDescription();
        monsterMaxHP = hpRandomizer(monsterType);
        currentHP = monsterMaxHP;
        monsterAtk = atkRandomizer(monsterType);
    }

    public int getMonsterAtk(){
        return monsterAtk;
    }

    public int getMonsterHP(){
        return monsterMaxHP;
    }

    public String getMonsterName(){
        return monsterName;
    }

    public void setDead(){
        System.out.println("The monster has been defeated");
        isDead = true;
    }


    public void statsInfo(){
        System.out.println(monsterName + "'s status: ");
        System.out.println(" - HP: " + currentHP);
        System.out.println(" - attack power: " + monsterAtk);

    }

}

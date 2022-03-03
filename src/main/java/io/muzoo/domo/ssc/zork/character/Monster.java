package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemWeapon;
import java.util.Random;
public class Monster extends Stats{
    Random randomizer = new Random();
    boolean isDead = false;
    boolean aggro = false;

    public Monster(){
        maxHP = randomizer.nextInt(401) + 100; //Random from 100-500 inclusive
        currentHP = maxHP;
        attackPower = randomizer.nextInt(10) + 1; //Random from 1-10 inclusive
        weaponOnHand = null;
    }

    public void setAggro(boolean state){
        aggro = state;
    }

    public boolean getAggro(){
        return aggro;
    }

    public void setDead(){
        System.out.println("The monster has been defeated");
        isDead = true;
    }

    public void monsterPowerDescription(){
        int attackPotential = attackDamage();
        if(attackPotential >= 1 && attackPotential <= 10){
            System.out.println("This one seems weak, but don't let your guard down.");
        }
        else if(attackPotential >= 11 && attackPotential <= 30){
            System.out.println("This one seems well-rounded. It will not be easy.");
        }
        else{
            System.out.println("This one is strong. Play safe.");
        }
    }

    public void StatsInfo(){
        System.out.println("Monster status: ");
        System.out.println(" - HP: " + currentHP);
        if(weaponOnHand != null){
            System.out.println("The monster currently equipping: " + weaponOnHand.getName());
            System.out.println(" - attack power: " + attackPower + " (base) + " + weaponOnHand.getAttackPower()
                    + " (" + weaponOnHand.getName() + ") ");
        }
        else{
            System.out.println(" - attack power: " + attackPower + " (base)");
        }

    }

}

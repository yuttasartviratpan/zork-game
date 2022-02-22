package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemWeapon;
import java.util.Random;
public class Monster implements Stats{
    Random randomizer = new Random();
    int maxHP;
    int currentHP = maxHP;
    int attackPower = randomizer.nextInt(10) + 1; //Random from 1-10 inclusive
    ItemWeapon weaponOnHand;

    Monster(){
        maxHP = randomizer.nextInt(401) + 100; //Random from 100-500 inclusive
    }

    @Override
    public void setCurrentHP(int hp) {
        currentHP = hp;
    }

    @Override
    public void setMaxHP(int hp) {
        maxHP = hp;
    }

    @Override
    public void setAttackPower(int powerLevel) {
        attackPower = powerLevel;
    }

    @Override
    public int attackDamage() {
        int attackPotential;
        if(weaponOnHand == null){
            if(attackPower <= 2){
                attackPotential = (new Random()).nextInt(attackPower) + 1;
            }
            else{
                attackPotential = (new Random()).nextInt(5) + attackPower-2;
            }
        }
        else{
            attackPotential = (new Random()).nextInt(5) + attackPower; //attackPower + weaponOnHand.attack -2 or smth
        }
        return attackPotential;
    }

    @Override
    public void equipWeapon(ItemWeapon weapon) {
        weaponOnHand = weapon;
    }

    @Override
    public boolean checkIfDead() {
        if(currentHP <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void setDead(){
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

}

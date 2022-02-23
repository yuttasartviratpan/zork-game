package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemWeapon;

import java.util.Random;

public abstract class Stats {
    int currentHP;
    int maxHP;
    int attackPower; //Base attack power
    ItemWeapon weaponOnHand;

    public void setCurrentHP(int hp) {
        currentHP = hp;
    }

    public void setMaxHP(int hp) {
        maxHP = hp;
    }

    public void setAttackPower(int powerLevel) {
        attackPower = powerLevel;
    }

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

    public void equipWeapon(ItemWeapon weapon) {
        weaponOnHand = weapon;
    }

    public boolean checkIfDead() {
        if(currentHP <= 0){
            return true;
        }
        else{
            return false;
        }
    }

}

package io.muzoo.domo.ssc.zork.character;

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

    public int getCurrentHP(){
        return currentHP;
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
            attackPotential = (new Random()).nextInt(5) + attackPower
                    + weaponOnHand.getAttackPower() - 2;
        }
        return attackPotential;
    }

    public void attacking(Stats entity){
        entity.currentHP -= this.attackDamage();
        if(entity.currentHP < 0){
            setDead();
        }
    }

    public abstract void setDead();

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

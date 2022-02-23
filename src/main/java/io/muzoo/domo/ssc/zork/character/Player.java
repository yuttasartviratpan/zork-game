package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemWeapon;

public class Player extends Stats{
    boolean gameOver;

    public Player(){
        maxHP = 100;
        currentHP = maxHP;
        attackPower = 10;
        weaponOnHand = null;
        gameOver = false;
    }


    public void setDead(){
        gameOver = true;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public void increaseAttack(int attack){
        attackPower += attack;
    }

    public void nextRoomHeal(int hp){
        if(currentHP + hp > maxHP){
            currentHP = maxHP;
        }
        else{
            currentHP += hp;
        }
    }


}

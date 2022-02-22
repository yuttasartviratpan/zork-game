package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemWeapon;

public class Player implements Stats{

    @Override
    public void setCurrentHP(int hp) {

    }

    @Override
    public void setMaxHP(int hp) {

    }

    @Override
    public void setAttackPower(int powerLevel) {

    }

    @Override
    public int attackDamage() {
        return 0;
    }

    @Override
    public void equipWeapon(ItemWeapon weapon) {

    }

    @Override
    public boolean checkIfDead() {
        return true;
    }

    @Override
    public void setDead(){
        //GameOver
    }
}

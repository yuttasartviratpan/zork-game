package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemWeapon;

public interface Stats {
    int currentHP = 100;
    int maxHP = 100;
    int attackPower = 10; //Base attack power
    ItemWeapon weaponOnHand = null;

    void setCurrentHP(int hp);
    void setMaxHP(int hp);
    void setAttackPower(int powerLevel);
    int attackDamage();
    void equipWeapon(ItemWeapon weapon);
    boolean checkIfDead();
    void setDead();

}

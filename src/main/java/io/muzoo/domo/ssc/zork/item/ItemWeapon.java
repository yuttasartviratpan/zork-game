package io.muzoo.domo.ssc.zork.item;

public abstract class ItemWeapon{
    protected String weaponName;
    protected String weaponDescription;
    protected int attackPower;

    public String getName(){
        return weaponName;
    }

    public String getDescription(){
        return weaponDescription;
    }

    public int getAttackPower(){
        return attackPower;
    }

    public boolean equals(ItemWeapon otherWeapon){
        return this.weaponName.equalsIgnoreCase(otherWeapon.weaponName) &&
                this.weaponDescription.equalsIgnoreCase(otherWeapon.weaponDescription) &&
                this.attackPower == otherWeapon.attackPower;
    }
}

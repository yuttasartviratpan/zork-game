package io.muzoo.domo.ssc.zork.item;

public abstract class ItemWeapon extends Item{
    protected String weaponName;
    protected String weaponDescription;
    protected int attackPower;

    public String getName(){
        return weaponName;
    }

    @Override
    public Class getItemClass(){
        return this.getClass().getSuperclass();
    }

    @Override
    public void getInfo(){
        System.out.println("Weapon name: " + weaponName);
        System.out.println("Weapon description: " + weaponDescription);
        System.out.println("Weapon power: " + attackPower + " damage");
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

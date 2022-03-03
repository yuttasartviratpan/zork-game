package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemUsable;
import io.muzoo.domo.ssc.zork.item.ItemUsableType;
import io.muzoo.domo.ssc.zork.item.ItemWeapon;
import io.muzoo.domo.ssc.zork.item.usable.HealthPotion;
import io.muzoo.domo.ssc.zork.item.weapon.FryingPan;
import io.muzoo.domo.ssc.zork.item.weapon.ShortSword;
import io.muzoo.domo.ssc.zork.item.weapon.WoodenClub;

import java.util.*;


public class Player extends Stats{
    boolean gameOver;
    Map<ItemUsable, Integer> consumableInventory;
    List<ItemWeapon> weaponInventory;

    public Player(){
        maxHP = 100;
        currentHP = maxHP;
        attackPower = 10;
        weaponOnHand = null;
        gameOver = false;
        consumableInventory = new HashMap<>();
        weaponInventory = new ArrayList<>();
        //For testing
        weaponInventory.add(new WoodenClub());
        weaponInventory.add(new FryingPan());
        weaponInventory.add(new ShortSword());
    }

    public void equipWeapon(String weapon){
        boolean found = false;
        ItemWeapon weaponToEquip = null;
        for(ItemWeapon arms : weaponInventory){
            if(arms.getName().equalsIgnoreCase(weapon)){
                found = true;
                weaponToEquip = arms;
            }
        }
        if(found){
            weaponOnHand = weaponToEquip;
            System.out.println("Weapon equipped");
        }
        else{
            System.out.println("Weapon not found, please check in inventory");
        }
    }

    public void dropItem(ItemUsable item){
        if(item.getUsableType() == ItemUsableType.KEY_ITEM){
            System.out.println("You just can't drop it");
        }
        else{
            consumableInventory.put(item, consumableInventory.get(item)-1);
        }
    }

    public ItemUsable checkForItem(String wantedItem){
        for(ItemUsable item : consumableInventory.keySet()){
            if(item.getName().equalsIgnoreCase(wantedItem)){
                return item;
            }
        }
        return null;
    }


    public void myStatsInfo(){
        System.out.println("Your current status: ");
        System.out.println(" - HP: " + currentHP);
        System.out.println(" - Max HP: " + maxHP);
        System.out.println();
        if(weaponOnHand != null){
            System.out.println("Currently equipping: " + weaponOnHand.getName());
            System.out.println(" - attack power: " + attackPower + " (base) + " + weaponOnHand.getAttackPower()
            + " (" + weaponOnHand.getName() + ") ");
        }
        else{
            System.out.println("You're fighting barehanded");
            System.out.println(" - attack power: " + attackPower + " (base)");
        }

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

    public void usingItem(ItemUsable item){
        if(consumableInventory.containsKey(item)) {
            int newValue = consumableInventory.get(item) - 1;
            if (item.getUsableType() == ItemUsableType.HEALTH_POTION) {
                if (newValue == 0) {
                    consumableInventory.remove(item);
                } else {
                    consumableInventory.put(item, newValue);
                }

                if (currentHP + item.use() > maxHP) {
                    currentHP = maxHP;
                } else {
                    currentHP += item.use();
                }
            } else {
                if (newValue == 0) {
                    consumableInventory.remove(item);
                } else {
                    consumableInventory.put(item, newValue);
                }
            }
        }
    }

    public Map<ItemUsable, Integer> checkInventoryConsumable(){
        return consumableInventory;
    }

    public List<ItemWeapon> checkInventoryWeapon(){
        return weaponInventory;
    }

    public void pickUpConsumable(ItemUsable item){
        System.out.println("You've picked up " + item.getName());
        if(consumableInventory.containsKey(item)){
            int value = consumableInventory.get(item);
            consumableInventory.put(item, value+1);
        }
        else{
            consumableInventory.put(item, 1);
        }
    }

    public void pickUpWeapon(ItemWeapon weapon){
        boolean haveWeapon = false;
        if(weaponInventory.contains(weapon)){
            for(ItemWeapon item : weaponInventory){
                if(weapon.equals(item)){
                    System.out.println("You already have this weapon");
                    haveWeapon = true;
                    break;
                }
            }
            if(!haveWeapon){
                System.out.println("You have pickup " + weapon.getName() + ".");
                weaponInventory.add(weapon);
            }
        }
        else{
            System.out.println("You have pickup " + weapon.getName() + ".");
            weaponInventory.add(weapon);
        }
    }

}

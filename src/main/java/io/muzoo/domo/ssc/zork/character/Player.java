package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.item.ItemUsable;
import io.muzoo.domo.ssc.zork.item.ItemUsableType;
import io.muzoo.domo.ssc.zork.item.ItemWeapon;
import io.muzoo.domo.ssc.zork.item.usable.HealthPotion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    }


    public void myStatsInfo(){
        System.out.println("Your current status: ");
        System.out.println(" - HP: " + currentHP);
        System.out.println(" - Max HP: " + maxHP);

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
        if(consumableInventory.containsKey(item)){

            switch (item.getUsableType()){
                case HEALTH_POTION:
                    HealthPotion healthPot = (HealthPotion) item;
                    if(currentHP + healthPot.use() > maxHP){
                        currentHP = maxHP;
                    }
                    else{
                        currentHP += healthPot.use();
                    }
                    break;
                case KEY_ITEM:
                    System.out.println("Just keep this safe, and survive");
                    break;
                case THROWABLE_WEAPON:
                    break; //Come back and figure how to use this later
            }
            int newValue = consumableInventory.get(item) - 1;
            if(newValue == 0){
                consumableInventory.remove(item);
            }
            else{
                consumableInventory.put(item, newValue);
            }
        }
        else{
            System.out.println("You don't own that item");
        }
    }

    public void checkInventoryConsumable(){
        for(ItemUsable item : consumableInventory.keySet()){

        }
    }

    public void checkInventoryWeapon(){
        if(weaponInventory.isEmpty()){
            System.out.println("You don't have any stored weapon");
        }
        else{
            System.out.println("You owned: ");
            for(ItemWeapon item : weaponInventory){
                System.out.println(" - " + item.getName());
            }
        }
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

package io.muzoo.domo.ssc.zork.character;

import io.muzoo.domo.ssc.zork.command.ItemUseAction;
import io.muzoo.domo.ssc.zork.item.Item;
import io.muzoo.domo.ssc.zork.item.ItemType;

import java.util.*;


public class Player{
    boolean gameOver;
    Map<Item, Integer> consumableInventory;
    List<Item> weaponInventory;
    List<Item> keyItemInventory;
    int currentHP;
    int maxHP;
    int attackPower; //Base attack power
    Item weaponOnHand;

    public Player(){
        maxHP = 100;
        currentHP = maxHP;
        attackPower = 10;
        weaponOnHand = null;
        gameOver = false;
        consumableInventory = new HashMap<>();
        consumableInventory.put(Item.THROWING_KNIFE, 99);
        weaponInventory = new ArrayList<>();
        keyItemInventory = new ArrayList<>();
    }

    public void setCurrentHP(int hp) {
        currentHP = hp;
    }

    public void setMaxHP(int hp) {
        maxHP = hp;
    }

    public void incrementAtk(int atk){
        attackPower += atk;
    }

    public void roomHeal(){
        if(currentHP + 5 > maxHP){
            currentHP = maxHP;
        }
        else{
            currentHP += 5;
        }
    }

    public void incrementCurrentHP(int hp){
        currentHP += hp;
        if(currentHP > maxHP){
            currentHP = maxHP;
        }
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
                    + weaponOnHand.getItemValue() - 2;
        }
        return attackPotential;
    }

    public void myStatsInfo(){
        System.out.println("Your current status: ");
        System.out.println(" - HP: " + currentHP);
        System.out.println(" - Max HP: " + maxHP);
        System.out.println();
        if(weaponOnHand != null){
            System.out.println("Currently equipping: " + weaponOnHand.getItemName());
            System.out.println(" - attack power: " + attackPower + " (base) + " + weaponOnHand.getItemValue()
            + " (" + weaponOnHand.getItemName() + ") ");
        }
        else{
            System.out.println("You're fighting barehanded");
            System.out.println(" - attack power: " + attackPower + " (base)");
        }
    }

    public void pickUpItem(Item item){
        if(item.getItemType() == ItemType.WEAPON){
            weaponInventory.add(item);
        }
        else if(item.getItemType() == ItemType.CONSUMABLE){
            if(!consumableInventory.containsKey(item)){
                consumableInventory.put(item, 1);
            }
            else{
                consumableInventory.put(item, consumableInventory.get(item) + 1);
            }
        }
        else{
            keyItemInventory.add(item);
        }
    }

    public ItemUseAction useItem(Item item){
        if(item.getItemType() == ItemType.CONSUMABLE){
            if(!consumableInventory.containsKey(item)){
                return ItemUseAction.NO_ACTION;
            }
            else{
                consumableInventory.put(item, consumableInventory.get(item) - 1);
                if(consumableInventory.get(item) == 0){
                    consumableInventory.remove(item);
                }
                if(item == Item.THROWING_KNIFE){
                    ItemUseAction useThrowable = ItemUseAction.USE_THROWABLE;
                    useThrowable.setActionValue(Item.THROWING_KNIFE.getItemValue());
                    return useThrowable;
                }
                else{
                    ItemUseAction usePotion = ItemUseAction.USE_POTION;
                    usePotion.setActionValue(Item.HEALTH_POTION.getItemValue());
                    return usePotion;
                }
            }
        }
        else if(item.getItemType() == ItemType.KEY){
            System.out.println("You cannot use it here, just keep it safe");
            return ItemUseAction.NO_ACTION;
        }
        else{
            System.out.println("Incorrect operation, please input proper item");
            return ItemUseAction.NO_ACTION;
        }
    }


    public void checkInventory(){
        if(!weaponInventory.isEmpty()){
            System.out.println("The weapons you have are: ");
            for(Item weapon : weaponInventory){
                System.out.println(" - " + weapon.getItemName());
            }
        }
        if(!consumableInventory.isEmpty()){
            System.out.println("The consumables you owned are: ");
            for(Item consumable : consumableInventory.keySet()){
                System.out.println(" - "  + consumable.getItemName() + ": " + consumableInventory.get(consumable) + " ea");
            }
        }
        if(!keyItemInventory.isEmpty()){
            System.out.println("The key item you recovered are: ");
            for(Item key : keyItemInventory){
                System.out.println(" - " + key.getItemName());
            }
        }
        if(weaponInventory.isEmpty() && consumableInventory.isEmpty() && keyItemInventory.isEmpty()){
            System.out.println("You have nothing on you");
        }
    }

    public void dropItem(Item item){
        if(item.getItemType() == ItemType.WEAPON){
            if(weaponInventory.contains(item)){
                weaponInventory.remove(item);
            }
            else{
                System.out.println("You cannot drop what you don't own");
            }
        }
        else if(item.getItemType() == ItemType.CONSUMABLE){
            if(consumableInventory.containsKey(item)){
                if(consumableInventory.get(item) > 1){
                    consumableInventory.put(item, consumableInventory.get(item) - 1);
                }
                else if(consumableInventory.get(item) == 1){
                    consumableInventory.remove(item);
                }
                else{
                    System.out.println("You cannot drop what you don't own");
                }
            }
        }
        else{
            System.out.println("Let's not drop this");
        }
    }

    public void equipWeapon(Item weapon){
        if(weaponOnHand == null){
            if(weaponInventory.contains(weapon)){
                weaponInventory.remove(weapon);
                weaponOnHand = weapon;
                System.out.println("Successfully equipped " + weapon.getItemName());
            }
            else{
                System.out.println("You do not own that weapon");
            }
        }
        else{
            if(weaponInventory.contains(weapon)){
                weaponInventory.add(weaponOnHand);
                weaponOnHand = weapon;
                weaponInventory.remove(weapon);
                System.out.println("Successfully equipped " + weapon.getItemName());
            }
            else{
                System.out.println("You do not own that weapon");
            }
        }
    }


    public int getCollectedKeyItem(){
        return keyItemInventory.size();
    }

}

package io.muzoo.domo.ssc.zork.item;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Item {
    WOODEN_CLUB("Wooden club", "A blunt and heavy wooden club", 20, ItemType.WEAPON),
    FRYING_PAN("Frying pan", "A hard stainless steel frying pan", 10, ItemType.WEAPON),
    SHORT_SWORD("Short sword", "A sharp short iron sword. There is a tiger face engraved on the handle", 30, ItemType.WEAPON),
    INSTAKILL_SWORD("God sword", "A sword created by the god themselves. Why do you even have this?", Integer.MAX_VALUE, ItemType.WEAPON),
    HEALTH_POTION("Healing potion", "A strange pot of medicine, it smells bitterly, but it has a healing properties", 20, ItemType.CONSUMABLE),
    THROWING_KNIFE("Throwing knives", "A set of small knives, should be thrown instead", 50, ItemType.CONSUMABLE),
    KEY_ITEM("<Insert key item name here>", "<Inset key item description here>", null, ItemType.KEY);

    String itemName;
    String itemDescription;
    Integer value;
    ItemType itemType;
    Item(String itemName, String itemDescription, Integer value, ItemType itemType){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.value = value;
        this.itemType = itemType;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public void setItemDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }

    public void setItemNameAndDescription(String itemName, String itemDescription){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    private static final List<Item> VALUES =
            Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Item randomItem()  {
        return VALUES.get(RANDOM.nextInt(SIZE-1));
    }

    public String getItemName(){
        return itemName;
    }

    public Integer getItemValue(){
        return value;
    }

    public ItemType getItemType(){
        return itemType;
    }


}


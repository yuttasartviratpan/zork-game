package io.muzoo.domo.ssc.zork.item.usable;

import io.muzoo.domo.ssc.zork.item.ItemUsable;
import io.muzoo.domo.ssc.zork.item.ItemUsableType;

public class HealthPotion extends ItemUsable {
    int healAmount;

    public HealthPotion(String itemName, String itemDescription, ItemUsableType itemType){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.usableType = itemType;
        this.healAmount = 20;
    }


    public int use(){
        return healAmount;
    }
}

package io.muzoo.domo.ssc.zork.item.usable;

import io.muzoo.domo.ssc.zork.item.ItemUsable;
import io.muzoo.domo.ssc.zork.item.ItemUsableType;

//Throwable weapons allow you to get another turn to attack (since you're keeping your distance from the enemy)
public class ThrowingKnives extends ItemUsable {
    public ThrowingKnives(String itemName, String itemDescription, ItemUsableType itemType){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.usableType = itemType;
    }

    public void use(){

    }
}

package io.muzoo.domo.ssc.zork.item.usable;

import io.muzoo.domo.ssc.zork.item.ItemUsable;
import io.muzoo.domo.ssc.zork.item.ItemUsableType;

public class KeyItem extends ItemUsable {
    public KeyItem(String itemName, String itemDescription, ItemUsableType itemType){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.usableType = itemType;
    }

    public int use(){
        return -1;
    }

    @Override
    public void getInfo(){
        System.out.println("Item name: " + itemName);
    }

}

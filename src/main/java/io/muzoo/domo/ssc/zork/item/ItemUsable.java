package io.muzoo.domo.ssc.zork.item;

public abstract class ItemUsable{
    protected String itemName;
    protected String itemDescription;
    protected ItemUsableType usableType;

    public String getName(){
        return itemName;
    }

    public String getDescription(){
        return itemDescription;
    }

    public ItemUsableType getUsableType(){
        return usableType;
    }

}

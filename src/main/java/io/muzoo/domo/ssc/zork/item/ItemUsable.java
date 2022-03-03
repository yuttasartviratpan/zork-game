package io.muzoo.domo.ssc.zork.item;

public abstract class ItemUsable extends Item{
    protected String itemName;
    protected String itemDescription;
    protected ItemUsableType usableType;

    @Override
    public void getInfo(){
        System.out.println("Item name: " + itemName);
        System.out.println("Item description: " + itemDescription);
    }

    @Override
    public Class getItemClass(){
        return this.getClass().getSuperclass();
    }

    public String getName(){
        return itemName;
    }

    public String getDescription(){
        return itemDescription;
    }

    public ItemUsableType getUsableType(){
        return usableType;
    }

    public abstract int use();
}

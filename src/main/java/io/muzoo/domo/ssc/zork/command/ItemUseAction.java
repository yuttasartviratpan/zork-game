package io.muzoo.domo.ssc.zork.command;

public enum ItemUseAction {
    USE_POTION(1),
    USE_THROWABLE(1),
    NO_ACTION(0);

    int actionValue;

    ItemUseAction(int value){
        this.actionValue = value;
    }

    public int getActionValue(){
        return actionValue;
    }

    public void setActionValue(int value){
        actionValue = value;
    }
}

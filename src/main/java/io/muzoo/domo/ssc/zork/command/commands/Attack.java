package io.muzoo.domo.ssc.zork.command.commands;

import io.muzoo.domo.ssc.zork.Game;
import io.muzoo.domo.ssc.zork.command.Command;

public class Attack extends Command {
    @Override
    public void run(Game gameState, String argument) {
        if(gameState.getMap().getMonsterInRoom() != null){
            System.out.println("You attacked "+ gameState.getMap().getMonsterInRoom().getMonsterType().getMonsterName());
            gameState.getMap().getMonsterInRoom().decrementMonsterHP(gameState.getPlayer().attackDamage());
            if(gameState.getMap().getMonsterInRoom().getMonsterCurrentHP() > 0){
                System.out.println("The enemy struck back!");
                gameState.getPlayer().decrementHP(gameState.getMap().getMonsterInRoom().getMonsterAtk());
            }
        }
        else{
            System.out.println("There is nothing to attack");
        }
    }
}

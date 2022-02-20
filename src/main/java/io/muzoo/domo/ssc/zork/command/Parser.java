package io.muzoo.domo.ssc.zork.command;
import java.io.IOException;
import java.io.InputStream;

import java.util.Scanner;
public class Parser {
    String command;
    boolean thereIsArg = false;

    public boolean parser(){
        Scanner userInput = new Scanner(System.in);
        if(userInput.hasNextLine()){
            command = userInput.nextLine();
        }
        else{
            command = "";
        }
        return getCommand(command);
    }

    public boolean getCommand(String inputtedText) {
        //Parse the first String entered as a command
        Scanner text = new Scanner(inputtedText);
        command = text.next();
        if(command.isEmpty()){
            System.out.println("No command has been entered. Please enter a command.");
            return true;
        }
        else if(command.equalsIgnoreCase("exit")){
            System.out.println("Okay. Aborting.");
            return false;
        }
        else if(command.equalsIgnoreCase("arg")){
            System.out.println("This is your arg: ");
            if(!text.hasNext()){
                getArgument("");
            }
            else{
                getArgument(text.next());
            }
            return true;
        }
        else{
            System.out.println("Unknown command. Use \"help\" to view the list of available command");
            return true;
        }

    }

    public void getArgument(String text){
        if(text.equals("")){
            System.out.println("Argument for this command not found. Please try again");
        }
        else{
            if(text.equalsIgnoreCase("pong")){
                System.out.println("Domo desu");
            }
            else if(text.equalsIgnoreCase("pong-desu")){
                System.out.println("You're not the desu desu");
            }
            else{
                System.out.println("Incorrect argument for this command. Please try again");
            }
        }
    }

}

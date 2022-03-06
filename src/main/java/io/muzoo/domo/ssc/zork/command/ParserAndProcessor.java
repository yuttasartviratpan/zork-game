package io.muzoo.domo.ssc.zork.command;

import java.util.*;

public class ParserAndProcessor {
    /*
        Check first whether the command is null or not.
        Print appropriately if it is null
     */
    Parser parsed = new Parser();
    CommandType command;
    String arguments;

    public void run(){
        parsed.parser();
        command = parsed.processedCommand;
        arguments = parsed.arguments;
    }

    public CommandType getCommandType(){
        return command;
    }

    public String getCommandString(){
        if(command == null){
            System.out.println("Unknown command or no command entered");
            return "null";
        }
        return command.getCommandString();
    }

    public Class<? extends Command> getCommandClass(){
        return command.getCommandClass();
    }

    public String getArguments(){
        return arguments;
    }

    public void checkArgument(){ //Delete this when actually implemented Commands
        System.out.println("Argument = " + arguments);
    }

}

class Parser{
    String command;
    CommandType processedCommand;
    String arguments;

    public void parser(){
        Scanner userInput = new Scanner(System.in);
        if(userInput.hasNextLine()){
            command = userInput.nextLine();
        }
        arguments = "";
        getCommand(command);
    }

    private void getCommand(String inputtedText) {
        inputtedText = inputtedText.trim();

        /*
            Split the string on the first occurrence of " ".
            So, there should be only at most length 2 array of String
         */
        String[] parsedString = inputtedText.split(" ", 2);

        if(parsedString.length == 0){
            processedCommand = null;
        }
        else if(parsedString.length == 1){
            String commandString = parsedString[0].toLowerCase();
            if(CommandType.checkCommandString(commandString)){
                processedCommand = CommandType.getCommandType(commandString);
            }
            else{
                processedCommand = null;
            }
        }
        else{
            String commandString = parsedString[0].toLowerCase();
            arguments = parsedString[1];
            if(CommandType.checkCommandString(commandString)){
                processedCommand = CommandType.getCommandType(commandString);
            }
            else{
                processedCommand = null;
            }
        }
    }

}

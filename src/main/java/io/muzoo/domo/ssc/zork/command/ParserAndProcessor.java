package io.muzoo.domo.ssc.zork.command;

import java.util.*;

public class ParserAndProcessor {
    Parser parsed = new Parser();
    boolean inGameState = false;
    CommandList command;
    List<String> arguments;

    public void run(){
        parsed.parser();

        command = parsed.processedCommand;
        arguments = parsed.arguments;
    }

    public CommandList getCommand(){
        return command;
    }

    public List<String> getArguments(){
        return arguments;
    }

    private void checkArgument(){ //Delete this when actually implemented Commands
        if(parsed.arguments.isEmpty()){
            System.out.println("No argument were given");
        }
        else{
            for (String argument : parsed.arguments) {
                System.out.println("- " + argument);
            }
        }
    }

}

class Parser{
    String command;
    CommandList processedCommand;
    List<String> arguments;

    Map<String, CommandList> allCommand = (new AllCommandList()).getAllCommandList();
    Map<String, Integer> numberOfArgumentInCommand = (new AllCommandList()).getAllNumberOfArgumentInCommand();

    public void parser(){
        Scanner userInput = new Scanner(System.in);
        if(userInput.hasNextLine()){
            command = userInput.nextLine();
        }
        arguments = new ArrayList<>();
        getCommand(command);
    }

    private void getCommand(String inputtedText) {
        //Parse the first String entered as a command
        Scanner text = new Scanner(inputtedText);
        if(!text.hasNext()){
            processedCommand = CommandList.EMPTY;
        }
        else{
            String parsedCommand = text.next().toLowerCase();
            if(numberOfArgumentInCommand.containsKey(parsedCommand)){
                if(numberOfArgumentInCommand.get(parsedCommand) == 0){
                    processedCommand = allCommand.get(parsedCommand);
                }
                else if(numberOfArgumentInCommand.get(parsedCommand) == 1){
                    processedCommand = allCommand.get(parsedCommand);
                    if(text.hasNext()){
                        arguments.add(text.next());
                    }
                }
            }
            else{
                processedCommand = CommandList.NOTFOUND;
            }
        }
    }

}
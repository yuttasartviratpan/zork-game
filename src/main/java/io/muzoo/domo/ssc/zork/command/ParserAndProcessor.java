package io.muzoo.domo.ssc.zork.command;

import java.util.*;

public class ParserAndProcessor {
    Parser parsed = new Parser();
    boolean inGameState = false;
    public boolean run(){ //true to keep running the game. false to stop the game
        parsed.parser();
        switch (parsed.processedCommand) {
            case EMPTY:
                System.out.println("No command has been entered. Use \"help\" to view the list of available command.");
                return true;

            case INFO:
                //Run command info
                System.out.println("Here is your info");
                return true;

            case TAKE:
                //Run command take
                System.out.println("This is your take: ");
                checkArgument();
                return true;

            case USE:
                //Run command use
                System.out.println("This is your use: ");
                checkArgument();
                return true;

            case DROP:
                //Run command drop
                System.out.println("This is your drop: ");
                checkArgument();
                return true;

            case ATTACK:
                //Run command attack
                System.out.println("This is your attack: ");
                checkArgument();
                return true;

            case GO:
                //Run command go
                System.out.println("This is your go: ");
                checkArgument();
                return true;

            case MAP:
                //Run command map
                System.out.println("Here's your map");
                return true;

            case AUTOPILOT:
                //Run command auto
                System.out.println("Autopiloting using: ");
                checkArgument();
                return true;

            case HELP:
                //Run command help
                System.out.println("There is no help right now");
                return true;

            case QUIT:
                //Run command quit
                System.out.println("Quitting the game");
                return false;

            case PLAY:
                //Run command play
                System.out.println("Here's your play: ");
                checkArgument();
                return true;

            case LOAD:
                //Run command load
                System.out.println("Here's your load: ");
                checkArgument();
                return true;

            case SAVE:
                //Run command save
                System.out.println("Here's your save: ");
                checkArgument();
                return true;

            case EXIT:
                //Run command exit
                System.out.println("Okay. Aborting.");
                return false;

            default:
                System.out.println("Unknown command. Use \"help\" to view the list of available command.");
                return true;

        }
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

    Map<String, CommandList> allCommand = new HashMap<>();
    Map<String, Integer> numberOfArgumentInCommand = new HashMap<>();


    public void parser(){
        initCommandList();
        initCommandHasArgument();
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

    private void initCommandList(){
        allCommand.put("info", CommandList.INFO);
        allCommand.put("take", CommandList.TAKE);
        allCommand.put("use", CommandList.USE);
        allCommand.put("drop", CommandList.DROP);
        allCommand.put("attack", CommandList.ATTACK);
        allCommand.put("go", CommandList.GO);
        allCommand.put("map", CommandList.MAP);
        allCommand.put("autopilot", CommandList.AUTOPILOT);
        allCommand.put("help", CommandList.HELP);
        allCommand.put("quit", CommandList.QUIT);
        allCommand.put("play", CommandList.PLAY);
        allCommand.put("load", CommandList.LOAD);
        allCommand.put("save", CommandList.SAVE);
        allCommand.put("exit", CommandList.EXIT);
    }

    private void initCommandHasArgument(){
        numberOfArgumentInCommand.put("info", 0);
        numberOfArgumentInCommand.put("take", 1);
        numberOfArgumentInCommand.put("use", 1);
        numberOfArgumentInCommand.put("drop", 1);
        numberOfArgumentInCommand.put("attack", 1);
        numberOfArgumentInCommand.put("go", 1);
        numberOfArgumentInCommand.put("map", 0);
        numberOfArgumentInCommand.put("autopilot", 1);
        numberOfArgumentInCommand.put("help", 0);
        numberOfArgumentInCommand.put("quit", 0);
        numberOfArgumentInCommand.put("play", 1);
        numberOfArgumentInCommand.put("load", 1);
        numberOfArgumentInCommand.put("save", 1);
        numberOfArgumentInCommand.put("exit", 0);
    }

}
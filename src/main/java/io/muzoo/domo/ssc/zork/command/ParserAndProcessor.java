package io.muzoo.domo.ssc.zork.command;

//Takes input from the user, then parse it into a command
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ParserAndProcessor {
    Parser parsed = new Parser();

    public boolean run(){ //true to keep running the game. false to stop the game
        parsed.parser();
        switch (parsed.processedCommand) {
            case EMPTY:
                System.out.println("No command has been entered. Use \"help\" to view the list of available command.");
                return true;

            case EXIT:
                System.out.println("Okay. Aborting.");
                return false;

            case INFO:
                System.out.println("Here is your info");
                return true;

            case GO:
                System.out.println("This is your go: ");
                if(parsed.arguments.isEmpty()){
                    System.out.println("No argument were given");
                }
                else{
                    for (String argument : parsed.arguments) {
                        System.out.println("- " + argument);
                    }
                }
                return true;

            default:
                System.out.println("Unknown command. Use \"help\" to view the list of available command.");
                return true;

        }
    }
}

class Parser{
    String command;
    CommandList processedCommand;
    List<String> arguments;


    public void parser(){
        Scanner userInput = new Scanner(System.in);
        if(userInput.hasNextLine()){
            command = userInput.nextLine();
        }
        else{
            processedCommand = CommandList.EMPTY;
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
            switch (text.next().toLowerCase()){
                case "exit":
                    processedCommand = CommandList.EXIT;
                    break;

                case "info":
                    processedCommand = CommandList.INFO;
                    break;

                case "go":
                    processedCommand = CommandList.GO;
                    if(text.hasNext()){
                        arguments.add(text.next());
                    }
                    break;

                default:
                    processedCommand = CommandList.NOTFOUND;
                    break;
            }
        }
    }

    public CommandList getCommand(){
        return processedCommand;
    }

    public List<String> getArgument(){
        return arguments;
    }

}
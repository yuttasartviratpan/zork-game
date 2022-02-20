package io.muzoo.domo.ssc.zork;

import io.muzoo.domo.ssc.zork.command.Parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean running = true;
        Parser game_instance = new Parser();
        while(running){
            running = game_instance.parser();

        }
    }
}

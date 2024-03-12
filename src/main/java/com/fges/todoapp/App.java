package com.fges.todoapp;


import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.InsertCommand.InsertCommand;
import com.fges.todoapp.commands.ListCommand.ListCommand;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Hello world!
 */
public class App {

    /**
     * Do not change this method
     */
    public static void main(String[] args) {
        System.exit(exec(args));
    }

    public static int exec(String[] args) {

        OptionsParser op = new OptionsParser(args);
        FileManager file = new FileManager(op);

        try{
            file.getCommand();
        }catch (Exception e) {
            ErrorHandling.printError("Impossible to execute the command", e);
        }finally {
            System.err.println("Done.");
        }

        return 0;
    }

}

package com.fges.todoapp;


import com.fges.todoapp.parser.OptionsParser;

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
        CommandManager manager = new CommandManager(op);

        try{
            manager.getCommand();
        }catch (Exception e) {
            ErrorHandling.printError("Impossible to execute the command", e);
        }finally {
            System.err.println("Done.");
        }

        return 0;
    }

}

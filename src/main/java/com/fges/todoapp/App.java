package com.fges.todoapp;


import com.fges.todoapp.tools.OptionManager;
import com.fges.todoapp.parser.OptionsParser;
import com.fges.todoapp.tools.CommandManager;

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

        OptionManager om = new OptionsParser(args).getManager();
        CommandManager manager = new CommandManager(om);

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

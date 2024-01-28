package com.fges.todoapp;


import com.fges.todoapp.commands.InsertCommand;
import com.fges.todoapp.commands.ListCommand;

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

        Path filePath = Paths.get(op.getOptions().get("fileName"));
        String fileContent = "";

        if (Files.exists(filePath)) {
            try{
                fileContent = Files.readString(filePath);
            }catch (Exception e) {
                ErrorHandling.printError("Impossible to read file", e);
            }
        }

        try{
            new InsertCommand(op.getCommand(), op, fileContent, filePath);
            new ListCommand(op.getCommand(), op, fileContent);
        }catch (Exception e) {
            ErrorHandling.printError("Impossible to execute the command", e);
        }finally {
            System.err.println("Done.");
        }

        return 0;
    }
}

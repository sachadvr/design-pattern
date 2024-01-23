package com.fges.todoapp;


import java.io.IOException;
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
    public static void main(String[] args) throws Exception {
        System.exit(exec(args));
    }

    public static int exec(String[] args) throws IOException {
        OptionsParser op = new OptionsParser(args);
        Path filePath = Paths.get(op.getFileName());
        String fileContent = "";

        if (Files.exists(filePath)) {
            fileContent = Files.readString(filePath);
        }

        new InsertCommand(op.getCommand(), op, fileContent, filePath);
        new ListCommand(op.getCommand(), op, fileContent);

        System.err.println("Done.");
        return 0;
    }
}

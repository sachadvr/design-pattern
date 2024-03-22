package com.fges.todoapp;

import com.fges.todoapp.parser.OptionsParser;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static final String FILENAME = "fileName";
    private static OptionsParser op;
    public FileManager(OptionsParser op) {
        this.op = op;
    }

    public String getContent(String bypassFileName) {
        Path filePath = Paths.get(bypassFileName);
        String fileContent = "";

        if (Files.exists(filePath)) {
            try{
                fileContent = Files.readString(filePath);
            }catch (Exception e) {
                ErrorHandling.printError("Impossible to read file", e);
            }
        }
        return fileContent;

    }

    public String getContent() {
        return getContent(op.getOptions().get(FILENAME));

    }

    public Path getFilePath() {
        return Paths.get(op.getOptions().get(FILENAME));
    }


}

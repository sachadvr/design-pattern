package com.fges.todoapp;

import com.fges.todoapp.tools.OptionManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static final String FILENAME = "fileName";
    private static OptionManager om;
    public FileManager(OptionManager om) {
        this.om = om;
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
        return getContent(om.getOptions().get(FILENAME));

    }

    public Path getFilePath() {
        return Paths.get(om.getOption(FILENAME));
    }


}

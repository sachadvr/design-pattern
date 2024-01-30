package com.fges.todoapp.commands.InsertCommand;

import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.DataProcessor;

import java.nio.file.Files;
import java.nio.file.Path;

public class InsertCommandCsvDataProcessor implements DataProcessor {
    @Override
    public void process(String todo, String fileContent, OptionsParser op, Path filePath) throws Exception {
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += String.format("%s;%d", todo, op.getOptions().containsKey("isDone") ? 1 : 0);

        Files.writeString(filePath, fileContent);    }
}

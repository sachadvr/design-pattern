package com.fges.todoapp;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CsvDataProcessor;
import com.fges.todoapp.commands.DataProcessor;
import com.fges.todoapp.commands.JsonDataProcessor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    private OptionsParser op;
    public FileManager(OptionsParser op) {
        this.op = op;
    }

    public String getContent() {
        Path filePath = Paths.get(op.getOptions().get("fileName"));
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

    public Path getFilePath() {
        return Paths.get(op.getOptions().get("fileName"));
    }

    public DataProcessor getDataProcessor() {
        String fileName = op.getOptions().get("fileName");
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String DataProcessor = op.getCommand().toLowerCase().substring(0, 1).toUpperCase() + op.getCommand().substring(1) + "Command";
        switch (extension) {
            case ".json" -> {
                try {
                    DataProcessor = "com.fges.todoapp.commands." + DataProcessor + "." + DataProcessor + "JsonDataProcessor";
                    return (JsonDataProcessor) Class.forName(DataProcessor).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case ".csv" -> {
                try {
                    DataProcessor = "com.fges.todoapp.commands." + DataProcessor + "." + DataProcessor + "CsvDataProcessor";
                    return (CsvDataProcessor) Class.forName(DataProcessor).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            default -> {
                return null;
            }
        }
        return null;

    }

    public Command getCommand() {
        String DataProcessor = op.getCommand().toLowerCase().substring(0, 1).toUpperCase() + op.getCommand().substring(1) + "Command";
        try {
            DataProcessor = "com.fges.todoapp.commands." + DataProcessor + "." + DataProcessor;
            return (Command) Class.forName(DataProcessor).getConstructor(String.class, OptionsParser.class, String.class, Path.class, DataProcessor.class).newInstance(op.getCommand(), op, getContent(), getFilePath(), getDataProcessor());
        } catch (Exception e) {
            ErrorHandling.printError("Command not found, try insert/list", e);
        }
        return null;
    }


}

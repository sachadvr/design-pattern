package com.fges.todoapp;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.DataProcessor;
import com.fges.todoapp.service.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public final String FILENAME = "fileName";
    private OptionsParser op;
    public FileManager(OptionsParser op) {
        this.op = op;
    }

    public String getContent() {
        Path filePath = Paths.get(op.getOptions().get(FILENAME));
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
        return Paths.get(op.getOptions().get(FILENAME));
    }

    public DataProcessor getDataProcessor() throws Exception {
        String fileName = op.getOptions().get(FILENAME);
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String Command = op.getCommand().toLowerCase().substring(0, 1).toUpperCase() + op.getCommand().substring(1) + "Command";
        Command = "com.fges.todoapp.commands." + Command + "." + Command + "DataProcessor";
        return (DataProcessor) Class.forName(Command).getConstructor().newInstance();
    }

    public LoadService getLoadService() {
        if (op.getOptions().get(FILENAME).endsWith(".csv")) {
            return new LoadCsvService();
        }else if (op.getOptions().get(FILENAME).endsWith(".json")) {
            return new LoadJsonService();
        }
        return null;
    }

    public WriteService getWriteService() {
        String output = op.getOptions().get(FILENAME);
        if (op.getOptions().get("destination") != null) {
            output = op.getOptions().get("destination");
        }
        if (output.endsWith(".csv")) {
            return new WriteCsvService();
        }else if (output.endsWith(".json")) {
            return new WriteJsonService();
        }
        return null;
    }

    public Command getCommand() {
        String DataProcessor = op.getCommand().toLowerCase().substring(0, 1).toUpperCase() + op.getCommand().substring(1) + "Command";
        try {
            DataProcessor = "com.fges.todoapp.commands." + DataProcessor + "." + DataProcessor;
            return (Command) Class.forName(DataProcessor).getConstructor(String.class, OptionsParser.class, String.class, Path.class, DataProcessor.class, LoadService.class, WriteService.class).newInstance(op.getCommand(), op, getContent(), getFilePath(), getDataProcessor(), getLoadService(), getWriteService());
        } catch (Exception e) {
            ErrorHandling.printError("Command not found, try insert/list", e);
        }
        return null;
    }


}

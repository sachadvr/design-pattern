package com.fges.todoapp;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.Execute;
import com.fges.todoapp.service.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    public static final String FILENAME = "fileName";
    private static OptionsParser op;
    public FileManager(OptionsParser op) {
        this.op = op;
    }

    public static String getContent(String bypassFileName) {
        Path filePath = Paths.get(op.getOptions().get(FILENAME));

        if (bypassFileName != null) {
            filePath = Paths.get(bypassFileName);
        }
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

    public Execute getDataProcessor() throws Exception {
        String fileName = op.getOptions().get(FILENAME);
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String Command = op.getCommand().toLowerCase().substring(0, 1).toUpperCase() + op.getCommand().substring(1) + "Command";
        Command = "com.fges.todoapp.commands." + Command + "." + Command + "Execute";
        return (Execute) Class.forName(Command).getConstructor().newInstance();
    }

    public static LoadService getLoadService(String bypassFileName) {
        String output = op.getOptions().get(FILENAME);
       //
        if (bypassFileName != null) {
            output = bypassFileName;
        }
        if (output.endsWith(".csv")) {
            return new LoadCsvService();
        }else if (output.endsWith(".json")) {
            return new LoadJsonService();
        }
        return null;
    }

    public static WriteService getWriteService(String bypassFileName) {
        String output = op.getOptions().get(FILENAME);
        if (op.getCommand() != null && op.getCommand().equals("web")) {
            return new WriteWebService();
        }
        if (op.getOptions().get("destination") != null) {
            output = op.getOptions().get("destination");
        }
        if (bypassFileName != null) {
            output = bypassFileName;
        }
        if (output.endsWith(".csv")) {
            return new WriteCsvService();
        }else if (output.endsWith(".json")) {
            return new WriteJsonService();
        }else if (output.endsWith(".web")) {
            return new WriteWebService();
        }
        return null;
    }

    public Command getCommand() {
        String DataProcessor = op.getCommand().toLowerCase().substring(0, 1).toUpperCase() + op.getCommand().substring(1) + "Command";
        try {
            DataProcessor = "com.fges.todoapp.commands." + DataProcessor + "." + DataProcessor;
            return (Command) Class.forName(DataProcessor).getConstructor(String.class, OptionsParser.class, String.class, Path.class, Execute.class, LoadService.class, WriteService.class).newInstance(op.getCommand(), op, getContent(null), getFilePath(), getDataProcessor(), getLoadService(null), getWriteService(null));
        } catch (Exception e) {
            ErrorHandling.printError("Command not found, try insert/list", e);
        }
        return null;
    }


}

package com.fges.todoapp;

import com.fges.todoapp.commands.Command;
import com.fges.todoapp.parser.OptionsParser;
import com.fges.todoapp.parser.YamlParser;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

public class CommandManager {

    public static final String FILENAME = "fileName";
    private OptionsParser op;

    private FileManager fileManager;

    public CommandManager(OptionsParser op) {
        this.op = op;
        fileManager = new FileManager(op);
    }
    public Command getCommand() {
        String commandClassName = op.getCommand().toLowerCase();
        try {
            List<Map<String, String>> commandMappings = new YamlParser().loadCommandMappings("/commands.yaml");
            for (Map<String, String> mapping : commandMappings) {
                if (commandClassName.equals(mapping.get("name"))) {
                    String fullClassName = mapping.get("class");
                    Class<?> commandClass = Class.forName(fullClassName);
                    Constructor<?> constructor = commandClass.getConstructor(String.class, Object[].class);
                    Object[] args = {op, fileManager};
                    return (Command) constructor.newInstance(op.getCommand(), args);
                }
            }
        } catch (Exception e) {
            ErrorHandling.printError("Commands not found", e);
        }
        return null;
    }


}

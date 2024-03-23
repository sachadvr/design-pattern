package com.fges.todoapp.tools;

import com.fges.todoapp.ErrorHandling;
import com.fges.todoapp.FileManager;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.parser.YamlParser;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

public class CommandManager {

    public static final String FILENAME = "fileName";
    private OptionManager om;

    private FileManager fileManager;

    public CommandManager(OptionManager om) {
        this.om = om;
        fileManager = new FileManager(om);
    }
    public List<Map<String, String>> getCommands() {
        return new YamlParser().loadCommandMappings("/commands.yaml", "commands");
    }
    public Command getCommand() {
        String commandClassName = om.getCommand().toLowerCase();
        try {
            List<Map<String, String>> commandMappings = getCommands();
            int index = commandMappings.indexOf(
                    commandMappings.stream()
                            .filter(mapping -> commandClassName.equals(mapping.get("name")))
                            .findFirst()
                            .orElse(null)
            );
            if (index != -1) {
                Map<String, String> mapping = commandMappings.get(index);
                String fullClassName = mapping.get("class");
                Class<?> commandClass = Class.forName(fullClassName);
                Constructor<?> constructor = commandClass.getConstructor(String.class, Object[].class);
                Object[] args = {om, fileManager};
                return (Command) constructor.newInstance(om.getCommand(), args);
            }
            throw new Exception("Sorry no command found for " + commandClassName);
        } catch (Exception e) {
            ErrorHandling.printError("Command not found", e);
        }
        return null;
    }


}

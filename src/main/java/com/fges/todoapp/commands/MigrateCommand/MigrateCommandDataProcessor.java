package com.fges.todoapp.commands.MigrateCommand;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.DataProcessor;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MigrateCommandDataProcessor implements DataProcessor {


    @Override
    public void process(ObjectNode todo, String fileContent, OptionsParser op, Path filePath, LoadService loadService, WriteService writeService) throws Exception {
        ArrayNode nodes = loadService.getTodos(fileContent, op, filePath);
        Path newFilePath = Paths.get(op.getOptions().get("destination"));
        writeService.write(newFilePath, nodes);
    }
}

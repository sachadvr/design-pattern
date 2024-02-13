package com.fges.todoapp.commands.ListCommand;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.DataProcessor;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;

public class ListCommandDataProcessor implements DataProcessor {


    @Override
    public void process(ObjectNode todo, String fileContent, OptionsParser op, Path filePath, LoadService loadService, WriteService writeService) throws Exception {
        ArrayNode nodes = loadService.getTodos(fileContent, op, filePath);
        if (op.getOptions().containsKey("isDone")) {
            for (int i = 0; i < nodes.size(); i++) {
                if (nodes.get(i).get("isdone").asBoolean()) {
                    System.out.println("- Done: " + nodes.get(i).get("name").asText());
                }
            }
            return;
        }
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).get("isdone").asBoolean()) {
                System.out.println("- Done: " + nodes.get(i).get("name").asText());
                continue;
            }
            System.out.println("- " + nodes.get(i).get("name").asText());
        }

    }
}

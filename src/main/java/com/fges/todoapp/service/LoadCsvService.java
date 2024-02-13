package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;

import java.nio.file.Path;
import java.util.Arrays;

public class LoadCsvService implements LoadService {


    public ArrayNode getTodos(String fileContent, OptionsParser op, Path filePath) {
        ArrayNode outputNode = JsonNodeFactory.instance.arrayNode();

        Arrays.stream(fileContent.split("\n"))
                .map(line -> Arrays.asList(line.split(";")))
                .filter(todoList -> todoList.size() > 1)
                .forEach(todoList -> {
                    String taskName = todoList.get(0);
                    boolean isDone = Boolean.parseBoolean(todoList.get(1));
                    ObjectNode itemNode = JsonNodeFactory.instance.objectNode();
                    itemNode.put("name", taskName);
                    itemNode.put("isdone", isDone);
                    outputNode.add(itemNode);
                });

        return outputNode;

    }
}

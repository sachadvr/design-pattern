package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.TodoList;

import java.nio.file.Files;
import java.nio.file.Path;

public class WriteJsonService implements WriteService {

    public void write(Path filePath, TodoList nodes) throws Exception {
        Files.write(filePath, nodes.buildJSON().getBytes());
    }
}

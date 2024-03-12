package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.TodoList;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteCsvService implements WriteService {

    public void write(Path filePath, TodoList nodes) throws IOException {

        String fileContent = "";
        StringBuilder sb = new StringBuilder();

        nodes.list().forEach(todo -> {
            sb.append(todo.getName());
            sb.append("~");
            sb.append(todo.isDone());
            sb.append("\n");
        });


        fileContent = sb.toString();

        Files.writeString(filePath, fileContent);
    }
}

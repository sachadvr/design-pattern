package com.fges.todoapp.service.Writing;

import com.fges.todoapp.model.TodoList;

import java.nio.file.Files;
import java.nio.file.Path;

public class WriteJson extends WriteService {

    @Override
    public void write(Path filePath, TodoList nodes) throws Exception {
        Files.write(filePath, nodes.buildJSON().getBytes());
    }
}

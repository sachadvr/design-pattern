package com.fges.todoapp.service.Writing;

import com.fges.todoapp.model.TodoList;

import java.nio.file.Path;

public interface WriteServiceInterface {

    public void write(Path filePath, TodoList nodes) throws Exception;
}

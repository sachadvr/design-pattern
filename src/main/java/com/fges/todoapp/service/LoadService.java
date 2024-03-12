package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.TodoList;

import java.nio.file.Path;

public interface LoadService {

    TodoList getTodos(String fileContent, OptionsParser op, Path filePath) throws Exception;

}

package com.fges.todoapp.service.Loading;

import com.fges.todoapp.parser.OptionsParser;
import com.fges.todoapp.model.TodoList;

import java.nio.file.Path;

public interface LoadService {

    TodoList getTodos(String fileContent, OptionsParser op, Path filePath) throws Exception;

}

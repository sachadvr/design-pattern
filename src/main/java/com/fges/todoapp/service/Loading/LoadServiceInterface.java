package com.fges.todoapp.service.Loading;

import com.fges.todoapp.tools.OptionManager;
import com.fges.todoapp.model.TodoList;

import java.nio.file.Path;

public interface LoadServiceInterface {

    TodoList getTodos(String fileContent, OptionManager om, Path filePath) throws Exception;

}

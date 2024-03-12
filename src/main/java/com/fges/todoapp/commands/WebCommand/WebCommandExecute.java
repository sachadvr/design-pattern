package com.fges.todoapp.commands.WebCommand;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.FileManager;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.Todo;
import com.fges.todoapp.TodoList;
import com.fges.todoapp.commands.Execute;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;
import java.nio.file.Paths;

public class WebCommandExecute implements Execute {


    @Override
    public void process(Todo todo, String fileContent, OptionsParser op, Path filePath, LoadService loadService, WriteService writeService) throws Exception {
        TodoList nodes = loadService.getTodos(fileContent, op, filePath);
        nodes.add(todo);
        writeService.write(filePath, nodes);
    }
}

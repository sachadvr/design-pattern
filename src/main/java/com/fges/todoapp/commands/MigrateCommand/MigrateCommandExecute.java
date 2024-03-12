package com.fges.todoapp.commands.MigrateCommand;

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
import java.util.List;

public class MigrateCommandExecute implements Execute {


    @Override
    public void process(Todo todo, String fileContent, OptionsParser op, Path filePath, LoadService loadService, WriteService writeService) throws Exception {
        TodoList nodes = loadService.getTodos(fileContent, op, filePath);

        String outputFileName = op.getOptions().get("output");
        Path newFilePath = Paths.get(op.getOptions().get("output"));
        TodoList outputNodes = FileManager.getLoadService(outputFileName).getTodos(FileManager.getContent(op.getOptions().get("output")), op, newFilePath);
        List<Todo> todos = nodes.list();
        for (int i = 0; i < todos.size(); i++) {
            outputNodes.add(todos.get(i));
        }

        FileManager.getWriteService(outputFileName).write(newFilePath, outputNodes);
    }
}

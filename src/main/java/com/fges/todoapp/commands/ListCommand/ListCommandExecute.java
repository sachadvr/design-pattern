package com.fges.todoapp.commands.ListCommand;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.Todo;
import com.fges.todoapp.TodoList;
import com.fges.todoapp.commands.Execute;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;
import java.util.List;

public class ListCommandExecute implements Execute {


    @Override
    public void process(Todo todo, String fileContent, OptionsParser op, Path filePath, LoadService loadService, WriteService writeService) throws Exception {
        TodoList nodes = loadService.getTodos(fileContent, op, filePath);
        List<Todo> todos = nodes.list();
        if (op.getOptions().containsKey("isDone")) {
            for (int i = 0; i < todos.size(); i++) {
                if (todos.get(i).isDone()) {
                    System.out.println("- Done: " + todos.get(i).getName());
                }
            }
            return;
        }
        for (int i = 0; i < todos.size(); i++) {
            if (todos.get(i).isDone()) {
                System.out.println("- Done: " + todos.get(i).getName());
                continue;
            }
            System.out.println("- " + todos.get(i).getName());
        }

    }
}

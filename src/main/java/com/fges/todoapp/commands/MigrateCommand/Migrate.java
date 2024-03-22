package com.fges.todoapp.commands.MigrateCommand;


import com.fges.todoapp.model.Todo;
import com.fges.todoapp.model.TodoList;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Migrate extends Command implements CommandInterface {

    public Migrate(String cmd, Object ...args) throws Exception {
        super(cmd, args);
    }

    @Override
    public void execute(Todo todo) throws Exception {
        String output = op.getOptions().get("output");
        Path newFilePath = Paths.get(output);

        TodoList nodes = getLoadService().getTodos(fileContent, op, filePath);
        TodoList outputNodes = getLoadService(output).getTodos(fileManager.getContent(output), op, newFilePath);

        List<Todo> todos = nodes.list();
        for (int i = 0; i < todos.size(); i++) {
            outputNodes.add(todos.get(i));
        }

        getWriteService(output).write(newFilePath, outputNodes);
    }
    @Override
    public String support() {
        return "migrate";
    }

    @Override
    public int neededArgs() {
        return 1;
    }
}

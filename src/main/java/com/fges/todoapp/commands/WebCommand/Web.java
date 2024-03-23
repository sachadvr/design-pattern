package com.fges.todoapp.commands.WebCommand;


import com.fges.todoapp.model.Todo;
import com.fges.todoapp.model.TodoList;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.service.Loading.LoadService;
import com.fges.todoapp.service.Writing.WriteService;
import com.fges.todoapp.service.Writing.WriteServiceInterface;
import com.fges.todoapp.service.Writing.WriteWeb;
import dumbcrud.DummyCrudEndpoint;
import dumbcrud.TodoProvider;

import java.io.IOException;

public class Web extends Command {

    public Web(String cmd, Object... args) throws Exception {
        super(cmd, args);
    }

    @Override
    public WriteService getWriteService() {
        return new WriteWeb();
    }

    @Override
    public void execute(Todo todo) throws Exception {
        TodoList nodes = getLoadService().getTodos(fileContent, om, filePath);

        getWriteService().write(filePath, nodes);
    }

    @Override
    public String support() {
        return "web";
    }

    @Override
    public int neededArgs() {
        return 1;
    }
}

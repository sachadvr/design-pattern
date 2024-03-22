package com.fges.todoapp.commands.WebCommand;


import com.fges.todoapp.model.Todo;
import com.fges.todoapp.model.TodoList;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.service.Writing.WriteService;
import com.fges.todoapp.service.Writing.WriteWebService;

public class Web extends Command implements CommandInterface {

    public Web(String cmd, Object... args) throws Exception {
        super(cmd, args);
    }

    @Override
    public WriteService getWriteService() {
        return new WriteWebService();
    }

    @Override
    public void execute(Todo todo) throws Exception {
        TodoList nodes = getLoadService().getTodos(fileContent, op, filePath);
        nodes.add(todo);
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

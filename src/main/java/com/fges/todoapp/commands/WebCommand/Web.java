package com.fges.todoapp.commands.WebCommand;


import com.fges.todoapp.model.Todo;
import com.fges.todoapp.model.TodoList;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.service.Writing.WriteServiceInterface;
import com.fges.todoapp.service.Writing.WriteWeb;

public class Web extends Command implements CommandInterface {

    public Web(String cmd, Object... args) throws Exception {
        super(cmd, args);
    }

    @Override
    public WriteServiceInterface getWriteService() {
        return new WriteWeb();
    }

    @Override
    public void execute(Todo todo) throws Exception {
        TodoList nodes = getLoadService().getTodos(fileContent, om, filePath);
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

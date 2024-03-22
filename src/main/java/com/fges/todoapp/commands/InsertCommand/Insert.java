package com.fges.todoapp.commands.InsertCommand;


import com.fges.todoapp.model.Todo;
import com.fges.todoapp.model.TodoList;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;

public class Insert extends Command implements CommandInterface {
    public Insert(String cmd, Object... args) throws Exception {
        super(cmd, args);
    }

    @Override
    public void execute(Todo todo) throws Exception {
        TodoList nodes = getLoadService().getTodos(fileContent, op, filePath);
        nodes.add(todo);
        getWriteService().write(filePath, nodes);
    }
    @Override
    public String support() {
        return "insert";
    }

    @Override
    public int neededArgs() {
        return 2;
    }

}

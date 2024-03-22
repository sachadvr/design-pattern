package com.fges.todoapp.commands.ListCommand;


import com.fges.todoapp.model.Todo;
import com.fges.todoapp.model.TodoList;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;

public class List extends Command implements CommandInterface {

    public List(String cmd, Object... args) throws Exception {
        super(cmd, args);
    }

    @Override
    public void execute(Todo todo) throws Exception {
        TodoList nodes = getLoadService().getTodos(fileContent, op, filePath);
        java.util.List<Todo> todos = nodes.list();
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
    @Override
    public String support() {
        return "list";
    }

    @Override
    public int neededArgs() {
        return 1;
    }
}

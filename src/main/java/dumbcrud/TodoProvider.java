package dumbcrud;

import com.fges.todoapp.model.Todo;
import dumbcrud.CrudProvider;

import java.util.ArrayList;
import java.util.List;

public class TodoProvider implements CrudProvider<Todo> {
    private List<Todo> todoList = new ArrayList<>();

    public TodoProvider(List<Todo> todoList) {
        this.todoList = todoList;
    }
@Override
public void add(Todo todo) throws Exception {
    todoList.add(todo);
}

@Override
public List<Todo> list() throws Exception {
    // Add your logic to return a list of your domain class
    return todoList;
}
}

package com.fges.todoapp.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    ArrayList<Todo> todos = new ArrayList<>();



    public List<Todo> list() {
        return todos;
    }

    public void add(Todo todo) {
        todos.add(todo);
    }

    public void add(String nameToString, boolean aBoolean) {
        Todo todo = new Todo(nameToString, aBoolean);
        todos.add(todo);
    }

    public String buildJSON() {
        JSONArray jsonArray = new JSONArray();
        for (Todo todo : todos) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", todo.getName().replaceAll("\"", ""));
            jsonObject.put("done", todo.isDone());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();

    }

    public void addJson(String json) {

    }
}

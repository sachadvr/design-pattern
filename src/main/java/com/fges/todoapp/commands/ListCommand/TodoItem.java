package com.fges.todoapp.commands.ListCommand;

public class TodoItem {
    private final String name;
    private final Integer isdone;


    public TodoItem(String name, Integer isdone) {
        this.name = name;
        this.isdone = isdone;
    }

    public String getName() {
        return name;
    }
    public Integer getIsdone() {
        return isdone;
    }
}
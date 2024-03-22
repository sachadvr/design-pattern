package com.fges.todoapp.commands;

import com.fges.todoapp.model.Todo;

public interface CommandInterface {
    public String support();

    public int neededArgs();

    public void execute(Todo todo) throws Exception;

    }
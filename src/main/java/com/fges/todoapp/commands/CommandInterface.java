package com.fges.todoapp.commands;

public interface CommandInterface {
    public boolean exec() throws Exception;
    public String support();
}

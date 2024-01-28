package com.fges.todoapp.commands;

public interface CommandInterface {
    public String support();

    public void execCSV(String todo) throws Exception;

    public void execJSON(String todo) throws Exception;

    public int neededArgs();
}
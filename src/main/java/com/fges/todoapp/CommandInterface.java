package com.fges.todoapp;

import java.io.IOException;

public interface CommandInterface {
    public void exec() throws IOException;
    public String support();
}

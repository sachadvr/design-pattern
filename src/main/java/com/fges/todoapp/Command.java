package com.fges.todoapp;


import org.apache.commons.cli.*;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * Hello world!
 */
abstract public class Command implements CommandInterface {
    public String cmd;
    public OptionsParser op;

    public String fileContent;

    public Path filePath;

    public Command(String cmd, OptionsParser op, String fileContent, Path filePath) {
        this.cmd = cmd;
        this.op = op;
        this.fileContent = fileContent;
        this.filePath = filePath;
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public String support() {
        return null;
    }
}

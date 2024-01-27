package com.fges.todoapp.commands;


import com.fges.todoapp.OptionsParser;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Hello world!
 */
abstract public class Command implements CommandInterface {
    public String cmd;
    public OptionsParser op;

    public String fileContent;

    public Path filePath;

    public Command(String cmd, OptionsParser op, String fileContent, Path filePath) throws IOException {

        this.cmd = cmd;
        this.op = op;
        this.fileContent = fileContent;
        this.filePath = filePath;
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public boolean exec() throws Exception {
        if (!isCommand()) return false;
        if (op.getPositionalArgs().size() < this.neededArgs()) {
            throw new Exception("Not enougth arguments for the command " + support());
        }
        return true;
    }

    public String support() {
        return null;
    }
    public int neededArgs() {
        return 0;
    }
}

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

    public Command(String cmd, OptionsParser op, String fileContent, Path filePath) throws Exception {
        this.cmd = cmd;
        this.op = op;
        this.fileContent = fileContent;
        this.filePath = filePath;

        if (!isCommand()) return;
        if (op.getPositionalArgs().size() < this.neededArgs()) {
            throw new Exception("Not enougth arguments for the command " + support());
        }
        exec();

    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public void execCSV(String todo) throws Exception {}

    public void execJSON(String todo) throws Exception {}

    public void exec() throws Exception {
        String todo = op.getPositionalArgs().size() > 1 ? op.getPositionalArgs().get(1) : null ;

        String fileName = op.getOptions().get("fileName");
        if (fileName.endsWith(".json")) {
            execJSON(todo);

            return;
        } else if (fileName.endsWith(".csv")) {
            execCSV(todo);

            return;
        }
        throw new Exception("Sorry, only json & csv are accepted");
    }
    public String support() {
        return null;
    }
    public int neededArgs() {
        return 0;
    }
}

package com.fges.todoapp.commands;


import com.fges.todoapp.FileManager;
import com.fges.todoapp.parser.OptionsParser;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.service.Loading.LoadCsvService;
import com.fges.todoapp.service.Loading.LoadJsonService;
import com.fges.todoapp.service.Loading.LoadService;
import com.fges.todoapp.service.Writing.WriteCsvService;
import com.fges.todoapp.service.Writing.WriteJsonService;
import com.fges.todoapp.service.Writing.WriteService;

import java.nio.file.Path;

/**
 * Hello world!
 */
abstract public class Command implements CommandInterface {
    public String cmd;

    public OptionsParser op;

    public FileManager fileManager;

    public String fileContent;

    public Path filePath;
    public Command(String cmd, Object... args) throws Exception {
        this.cmd = cmd;
        if (args.length < 2) {
            throw new Exception("Not enough arguments for the command " + support());
        }

        this.op = (OptionsParser) args[0];
        this.fileManager = (FileManager) args[1];
        this.fileContent = fileManager.getContent();
        this.filePath = fileManager.getFilePath();
        if (!isCommand()) return;
        if (op.getPositionalArgs().size() < this.neededArgs()) {
            throw new Exception("Not enougth arguments for the command " + support());
        }
        exec();
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public LoadService getLoadService() {
        String output = op.getOptions().get("fileName");
        if (output.endsWith(".csv")) {
            return new LoadCsvService();
        }else if (output.endsWith(".json")) {
            return new LoadJsonService();
        }
        return null;
    }

    public LoadService getLoadService(String override) {
        if (override.endsWith(".csv")) {
            return new LoadCsvService();
        }else if (override.endsWith(".json")) {
            return new LoadJsonService();
        }
        return null;
    }

    public WriteService getWriteService() {
        String output = op.getOptions().get("fileName");
        if (output.endsWith(".csv")) {
            return new WriteCsvService();
        }else if (output.endsWith(".json")) {
            return new WriteJsonService();
        }
        return null;
    }

    public WriteService getWriteService(String override) {
        if (override.endsWith(".csv")) {
            return new WriteCsvService();
        }else if (override.endsWith(".json")) {
            return new WriteJsonService();
        }
        return null;
    }

    public void exec() throws Exception {
        try {
            Todo todo = new Todo(op.getPositionalArgs().size() > 1 ? op.getAllArgs() : null, op.getOptions().containsKey("isDone") ? true : false);

            execute(todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String support() {
        return null;
    }
    public int neededArgs() {
        return 0;
    }
}

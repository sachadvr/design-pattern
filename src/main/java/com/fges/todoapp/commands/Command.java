package com.fges.todoapp.commands;


import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;

/**
 * Hello world!
 */
abstract public class Command implements CommandInterface {
    public String cmd;

    public OptionsParser op;

    public String fileContent;

    public Path filePath;
    private final DataProcessor processor;

    private final LoadService loadService;

    private final WriteService writeService;
    public Command(String cmd, OptionsParser op, String fileContent, Path filePath, DataProcessor dataProcessor, LoadService loadService, WriteService writeService) throws Exception {
        this.cmd = cmd;
        this.op = op;
        this.fileContent = fileContent;
        this.filePath = filePath;
        this.processor = dataProcessor;
        this.writeService = writeService;
        this.loadService = loadService;
        if (!isCommand()) return;
        if (op.getPositionalArgs().size() < this.neededArgs()) {
            throw new Exception("Not enougth arguments for the command " + support());
        }
        exec();
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }
    public void exec() throws Exception {
        try {
            ObjectNode todoNode = new ObjectNode(JsonNodeFactory.instance);
            todoNode.put("name", op.getPositionalArgs().size() > 1 ? op.getPositionalArgs().get(1) : null);
            todoNode.put("isdone", op.getOptions().containsKey("isDone") ? true : false);
            processor.process(todoNode, fileContent, op, filePath, loadService, writeService);
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

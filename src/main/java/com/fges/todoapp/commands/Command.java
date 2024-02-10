package com.fges.todoapp.commands;


import com.fges.todoapp.OptionsParser;

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
    public Command(String cmd, OptionsParser op, String fileContent, Path filePath, DataProcessor dataProcessor) throws Exception {
        this.cmd = cmd;
        this.op = op;
        this.fileContent = fileContent;
        this.filePath = filePath;
        this.processor = dataProcessor;
        if (!isCommand()) return;
        if (op.getPositionalArgs().size() < this.neededArgs()) {
            throw new Exception("Not enougth arguments for the command " + support());
        }
        exec();
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public void executeDataProcessor() {
        try {
            processor.process(op.getPositionalArgs().size() > 1 ? op.getPositionalArgs().get(1) : null, fileContent, op, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exec() throws Exception {
        executeDataProcessor();
    }
    public String support() {
        return null;
    }
    public int neededArgs() {
        return 0;
    }
}

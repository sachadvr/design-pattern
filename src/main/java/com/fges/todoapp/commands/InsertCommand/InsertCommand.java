package com.fges.todoapp.commands.InsertCommand;


import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.commands.DataProcessor;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class InsertCommand extends Command implements CommandInterface {
    public InsertCommand(String cmd, OptionsParser op, String fileContent, Path filePath, DataProcessor dataProcessor) throws Exception {
        super(cmd, op, fileContent, filePath, dataProcessor);
    }

    @Override
    public String support() {
        return "insert";
    }

    @Override
    public int neededArgs() {
        return 2;
    }
}

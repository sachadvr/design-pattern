package com.fges.todoapp.commands.ListCommand;


import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.commands.DataProcessor;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;

/**
 * Hello world!
 */
public class ListCommand extends Command implements CommandInterface {

    public ListCommand(String cmd, OptionsParser op, String fileContent, Path filePath, DataProcessor dataProcessor, LoadService loadService, WriteService writeService) throws Exception {
        super(cmd, op, fileContent, filePath, dataProcessor, loadService, writeService);
    }

    @Override
    public String support() {
        return "list";
    }

    @Override
    public int neededArgs() {
        return 1;
    }
}

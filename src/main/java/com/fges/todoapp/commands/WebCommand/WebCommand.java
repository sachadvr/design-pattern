package com.fges.todoapp.commands.WebCommand;


import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.commands.Execute;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;

/**
 * Hello world!
 */
public class WebCommand extends Command implements CommandInterface {

    public WebCommand(String cmd, OptionsParser op, String fileContent, Path filePath, Execute dataProcessor, LoadService loadService, WriteService writeService) throws Exception {
        super(cmd, op, fileContent, filePath, dataProcessor, loadService, writeService);
    }

    @Override
    public String support() {
        return "web";
    }

    @Override
    public int neededArgs() {
        return 1;
    }
}

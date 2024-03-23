package com.fges.todoapp.commands;


import com.fges.todoapp.FileManager;
import com.fges.todoapp.parser.ServiceParser;
import com.fges.todoapp.tools.OptionManager;
import com.fges.todoapp.model.Todo;
import com.fges.todoapp.service.Loading.LoadCsv;
import com.fges.todoapp.service.Loading.LoadJson;
import com.fges.todoapp.service.Loading.LoadServiceInterface;
import com.fges.todoapp.service.Writing.WriteCsv;
import com.fges.todoapp.service.Writing.WriteJson;
import com.fges.todoapp.service.Writing.WriteServiceInterface;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Path;

/**
 * Hello world!
 */
abstract public class Command implements CommandInterface {
    public String cmd;

    public OptionManager om;

    public FileManager fileManager;

    public String fileContent;

    public Path filePath;

    public ServiceParser serviceParser;
    public Command(String cmd, Object... args) throws Exception {
        this.cmd = cmd;
        if (args.length < 2) {
            throw new Exception("Not enough arguments for the command " + support());
        }

        this.om = (OptionManager) args[0];
        this.fileManager = (FileManager) args[1];
        this.fileContent = fileManager.getContent();
        this.filePath = fileManager.getFilePath();
        if (!isCommand()) return;
        if (om.getArguments().size() < this.neededArgs()) {
            throw new Exception("Not enougth arguments for the command " + support());
        }
        exec();
    }

    public boolean isCommand() {
        return support().equals(cmd);
    }

    public LoadServiceInterface getLoadService(String override) {
       return serviceParser.findLoadService(override);
    }

    public LoadServiceInterface getLoadService() {
        return getLoadService(om.getOption("fileName"));
    }


    public WriteServiceInterface getWriteService(String override) {
        return serviceParser.findWriteService(override);
    }

    public WriteServiceInterface getWriteService() {
        return getWriteService(om.getOption("fileName"));
    }

    public void exec() throws Exception {
        try {
            Todo todo = new Todo(om.getArguments().size() > 1 ? om.ArgsToString() : null, om.hasOption("isDone") ? true : false);
            serviceParser = new ServiceParser();
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

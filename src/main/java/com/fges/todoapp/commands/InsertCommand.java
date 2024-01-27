package com.fges.todoapp.commands;


import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.Command;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class InsertCommand extends Command {
    public InsertCommand(String cmd, OptionsParser op, String fileContent, Path filePath) throws Exception {
        super(cmd, op, fileContent, filePath);
        exec();
    }

    public boolean exec() throws Exception {
        if (!super.exec()) return false;

        String todo = op.getPositionalArgs().get(1);
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todo;
        Files.writeString(filePath, fileContent);

        return true;
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

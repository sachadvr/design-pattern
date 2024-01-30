package com.fges.todoapp.commands.ListCommand;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;
import com.fges.todoapp.commands.InsertCommand.InsertCommandCsvDataProcessor;
import com.fges.todoapp.commands.InsertCommand.InsertCommandJsonDataProcessor;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class InsertCommand extends Command implements CommandInterface {
    public InsertCommand(String cmd, OptionsParser op, String fileContent, Path filePath) throws Exception {
        super(cmd, op, fileContent, filePath, new InsertCommandCsvDataProcessor(), new InsertCommandJsonDataProcessor());
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

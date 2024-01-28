package com.fges.todoapp.commands;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.OptionsParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class InsertCommand extends Command implements CommandInterface{
    public InsertCommand(String cmd, OptionsParser op, String fileContent, Path filePath) throws Exception {
        super(cmd, op, fileContent, filePath);
    }

    @Override
    public void execCSV(String todo) throws Exception {
        if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
            fileContent += "\n";
        }
        fileContent += todo;
        Files.writeString(filePath, fileContent);
    }

    @Override
    public void execJSON(String todo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(todo);
        }

        Files.writeString(filePath, actualObj.toString());
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

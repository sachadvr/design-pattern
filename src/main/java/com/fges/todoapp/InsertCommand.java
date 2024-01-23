package com.fges.todoapp;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 */
public class InsertCommand extends Command {
    public InsertCommand(String cmd, OptionsParser op, String fileContent, Path filePath) throws IOException {
        super(cmd, op, fileContent, filePath);
        exec();
    }

    public void exec() throws IOException {
        if (isCommand()) {
            if (op.getPositionalArgs().size() < 2) {
                System.err.println("Missing TODO name");
                return;
            }
            String todo = op.getPositionalArgs().get(1);
            if (!fileContent.endsWith("\n") && !fileContent.isEmpty()) {
                fileContent += "\n";
            }
            fileContent += todo;
            Files.writeString(filePath, fileContent);
        }
    }
    @Override
    public String support() {
        return "insert";
    }
}

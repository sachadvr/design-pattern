package com.fges.todoapp.commands.InsertCommand;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.DataProcessor;
import com.fges.todoapp.commands.ListCommand.TodoItem;

import java.nio.file.Files;
import java.nio.file.Path;

public class InsertCommandJsonDataProcessor implements DataProcessor {
    @Override
    public void process(String todo, String fileContent, OptionsParser op, Path filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);

        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.add(mapper.valueToTree(new TodoItem(todo, op.getOptions().containsKey("isDone") ? 1 : 0)));
        }

        Files.writeString(filePath, actualObj.toString());
    }
}

package com.fges.todoapp.commands.ListCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.CsvDataProcessor;
import com.fges.todoapp.commands.JsonDataProcessor;

import java.nio.file.Path;
import java.util.Map;

public class ListCommandJsonDataProcessor implements JsonDataProcessor {
    @Override
    public void process(String todo, String fileContent, OptionsParser op, Path filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> {
                JsonNode isDone = node.get("isdone");
                JsonNode name = node.get("name");
                String nameToString = name.toString().substring(1, name.toString().length() - 1).trim();

                if (op.getOptions().containsKey("isDone")) {
                    if (isDone != null && isDone.asBoolean()) {
                        System.out.println("- Done: " + nameToString);
                    }
                } else {
                    try {
                        System.out.println((isDone != null && isDone.asBoolean() ? "- Done: " : "- ") + mapper.readValue(node.toString(), Map.class).get("name"));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }
}

package com.fges.todoapp.commands.ListCommand;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.JsonDataProcessor;

import java.nio.file.Path;
import java.util.Map;

public class ListCommandJsonDataProcessor implements JsonDataProcessor {
    @Override
    public void process(String todo, String fileContent, OptionsParser op, Path filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(fileContent);

        if (rootNode instanceof ArrayNode) {
            processArrayNode((ArrayNode) rootNode, op, mapper);
        }
    }

    private void processArrayNode(ArrayNode arrayNode, OptionsParser op, ObjectMapper mapper) {
        arrayNode.forEach(node -> {
            JsonNode isDoneNode = node.get("isdone");
            JsonNode nameNode = node.get("name");
            String name = getNameFromNode(nameNode);

            if (op.getOptions().containsKey("isDone")) {
                printDoneTaskStatus(isDoneNode, name);
            } else {
                printTask(isDoneNode, name, node, mapper);
            }
        });
    }

    private String getNameFromNode(JsonNode nameNode) {
        return nameNode != null ? nameNode.asText() : "";
    }

    private void printDoneTaskStatus(JsonNode isDoneNode, String name) {
        if (isDoneNode != null && isDoneNode.asBoolean()) {
            System.out.println("- Done: " + name);
        }
    }

    private void printTask(JsonNode isDoneNode, String name, JsonNode node, ObjectMapper mapper) {
        try {
            String output = (isDoneNode != null && isDoneNode.asBoolean() ? "- Done: " : "- ") +
                    mapper.readValue(node.toString(), Map.class).get("name");
            System.out.println(output);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

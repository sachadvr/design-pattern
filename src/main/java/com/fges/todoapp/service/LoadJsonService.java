package com.fges.todoapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;

import java.nio.file.Path;

public class LoadJsonService implements LoadService {

    public ArrayNode getTodos(String fileContent, OptionsParser op, Path filePath) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        ArrayNode outputNode = mapper.createArrayNode();

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> {
                JsonNode isDone = node.get("isdone");
                JsonNode name = node.get("name");
                String nameToString = name.toString().substring(1, name.toString().length() - 1).trim();
                ObjectNode todoNode = JsonNodeFactory.instance.objectNode();
                todoNode.put("name", nameToString);
                todoNode.put("isdone", isDone.asBoolean() ? true : false);

                outputNode.add(todoNode);
            });
        }
        return outputNode;
    }
}

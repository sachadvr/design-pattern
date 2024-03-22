package com.fges.todoapp.service.Loading;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.parser.OptionsParser;
import com.fges.todoapp.model.TodoList;

import java.nio.file.Path;

public class LoadJsonService implements LoadService {

    public TodoList getTodos(String fileContent, OptionsParser op, Path filePath) throws Exception {
        TodoList todoList = new TodoList();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> {
                JsonNode isDone = node.get("done");
                JsonNode name = node.get("name");
                String nameToString = name.toString().substring(1, name.toString().length() - 1).trim();

                todoList.add(nameToString, isDone.asBoolean());
            });
        }
        return todoList;
    }
}

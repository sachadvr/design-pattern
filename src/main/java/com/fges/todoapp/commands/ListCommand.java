package com.fges.todoapp.commands;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.MissingNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.Command;
import com.fges.todoapp.commands.CommandInterface;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class ListCommand extends Command implements CommandInterface {

    public ListCommand(String cmd, OptionsParser op, String fileContent) throws Exception {
        super(cmd, op, fileContent, null);
    }

    @Override
    public void execCSV(String todo) throws Exception {
        System.out.println(Arrays.stream(fileContent.split("\n"))
                .map(mytodo -> "- " + mytodo)
                .collect(Collectors.joining("\n"))
        );
    }

    @Override
    public void execJSON(String todo) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(fileContent);
        if (actualObj instanceof MissingNode) {
            actualObj = JsonNodeFactory.instance.arrayNode();
        }

        if (actualObj instanceof ArrayNode arrayNode) {
            arrayNode.forEach(node -> System.out.println("- " + node.toString()));
        }
    }

    @Override
    public String support() {
        return "list";
    }

    @Override
    public int neededArgs() {
        return 1;
    }
}

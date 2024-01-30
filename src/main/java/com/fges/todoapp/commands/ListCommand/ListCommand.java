package com.fges.todoapp.commands.ListCommand;


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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class ListCommand extends Command implements CommandInterface {

    public ListCommand(String cmd, OptionsParser op, String fileContent) throws Exception {
        super(cmd, op, fileContent, null, new ListCommandCsvDataProcessor(), new ListCommandJsonDataProcessor());
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
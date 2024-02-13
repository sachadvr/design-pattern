package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;

import java.nio.file.Files;
import java.nio.file.Path;

public class WriteJsonService implements WriteService {

    public void write(Path filePath, ArrayNode nodes) throws Exception {
        Files.write(filePath, nodes.toString().getBytes());
    }
}

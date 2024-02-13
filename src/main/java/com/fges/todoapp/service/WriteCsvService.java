package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteCsvService implements WriteService {

    public void write(Path filePath, ArrayNode nodes) throws IOException {

        String fileContent = "";
        StringBuilder sb = new StringBuilder();

        nodes.forEach(node -> {
            sb.append(node.get("name").asText())
                    .append(";")
                    .append(node.get("isdone").asBoolean() ? "true" : "false")
                    .append("\n");
        });
        fileContent = sb.toString();
        Files.writeString(filePath, fileContent);
    }
}

package com.fges.todoapp.service;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fges.todoapp.OptionsParser;

import java.nio.file.Path;

public interface WriteService {

    public void write(Path filePath, ArrayNode nodes) throws Exception;
}

package com.fges.todoapp.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.Todo;
import com.fges.todoapp.service.LoadService;
import com.fges.todoapp.service.WriteService;

import java.nio.file.Path;

public interface Execute {
    public void process(Todo todo, String fileContent, OptionsParser op, Path filePath, LoadService loadService, WriteService writeService) throws Exception;
}

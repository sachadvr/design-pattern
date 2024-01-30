package com.fges.todoapp.commands;

import com.fges.todoapp.OptionsParser;

import java.nio.file.Path;

public interface DataProcessor {
    void process(String todo, String fileContent, OptionsParser op, Path filePath) throws Exception;
}

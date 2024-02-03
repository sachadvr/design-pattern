package com.fges.todoapp.commands.ListCommand;

import com.fges.todoapp.OptionsParser;
import com.fges.todoapp.commands.DataProcessor;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ListCommandCsvDataProcessor implements DataProcessor {
    @Override
    public void process(String todo, String fileContent, OptionsParser op, Path filePath) throws Exception {

        System.out.println(Arrays.stream(fileContent.split("\n"))
                .map(line -> Arrays.asList(line.split(";")))
                .filter(todoList -> todoList.size() > 1 && (!op.getOptions().containsKey("isDone") || "1".equals(todoList.get(1))))
                .map(todoList -> {
                    String taskName = todoList.get(0);
                    String isDoneString = todoList.get(1);
                    boolean isDone = "1".equals(isDoneString);

                    return isDone ? "- Done: \"" + taskName + "\"" : "- " + taskName;

                })
                .collect(Collectors.joining("\n"))
        );

    }
}

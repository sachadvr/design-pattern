package com.fges.todoapp.service.Loading;

import au.com.bytecode.opencsv.CSVReader;
import com.fges.todoapp.parser.OptionsParser;
import com.fges.todoapp.model.TodoList;

import java.io.StringReader;
import java.nio.file.Path;
import java.util.List;

public class LoadCsvService implements LoadService {


    public TodoList getTodos(String fileContent, OptionsParser op, Path filePath) {
        TodoList todoList = new TodoList();
        try (CSVReader reader = new CSVReader(new StringReader(fileContent), '~')) {
            List<String[]> allRows = reader.readAll();

            allRows.forEach(todoListCsv -> {
                        String taskName = todoListCsv[0];
                        boolean isDone = Boolean.parseBoolean(todoListCsv[1]);
                        todoList.add(taskName, isDone);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return todoList;

    }
}

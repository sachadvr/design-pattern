package com.fges.todoapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DeleteCommand extends Command {

    public DeleteCommand(String cmd, OptionsParser op, String fileContent, Path filePath) throws IOException {
        super(cmd, op, fileContent, filePath);
        exec();
    }

    @Override
    public void exec() throws IOException {
        if (isCommand()) {
            List<String> positionalArgs = op.getPositionalArgs();
            if (positionalArgs.size() < 2) {
                System.err.println("Missing TODO index to delete");
                return;
            }

            try {
                int todoIndex = Integer.parseInt(positionalArgs.get(1)) - 1;

                String[] todos = fileContent.split("\n");

                if (todoIndex < 0 || todoIndex >= todos.length) {
                    System.err.println("Invalid TODO index");
                    return;
                }

                StringBuilder updatedContent = new StringBuilder();
                for (int i = 0; i < todos.length; i++) {
                    if (i != todoIndex) {
                        updatedContent.append(todos[i]);
                        if (i < todos.length - 1) {
                            updatedContent.append("\n");
                        }
                    }
                }

                Files.writeString(filePath, updatedContent.toString());
            } catch (NumberFormatException e) {
                System.err.println("Invalid TODO index format");
            }
        }
    }

    @Override
    public String support() {
        return "delete";
    }
}

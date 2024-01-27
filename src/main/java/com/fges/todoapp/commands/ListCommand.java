package com.fges.todoapp.commands;


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

        exec();
    }

    public boolean exec() throws Exception {
        if (!super.exec()) return false;
        System.out.println(Arrays.stream(fileContent.split("\n"))
                    .map(todo -> "- " + todo)
                    .collect(Collectors.joining("\n"))
            );
        return true;
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

package com.fges.todoapp.tools;

import com.fges.todoapp.ErrorHandling;
import com.fges.todoapp.options.Option;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionManager {

    private List<String> positionalArgs;
    private Map<String, String> options = new HashMap<>();
    public OptionManager(Map<String, String> options, List<String> args) {
        this.options = options;
        this.positionalArgs = args;
    }

    public String ArgsToString() {
        return String.join(" ", positionalArgs.subList(1, positionalArgs.size()));
    }

    public List<String> getArguments() {
        return positionalArgs;
    }


    public Map<String, String> getOptions() {
        return options;
    }

    public String getOption(String option) {
        return options.get(option);
    }

    public boolean hasOption(String option) {
        return options.containsKey(option);
    }

    public String getCommand() {
        return (positionalArgs != null && !positionalArgs.isEmpty()) ? positionalArgs.get(0) : null;
    }

}

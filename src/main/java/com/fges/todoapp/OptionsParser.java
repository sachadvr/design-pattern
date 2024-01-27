package com.fges.todoapp;

import com.fges.todoapp.options.Option;
import com.fges.todoapp.options.OptionsContainer;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OptionsParser {

    private final Options cliOptions;
    private final CommandLineParser parser;
    private List<String> positionalArgs;
    private final HashMap<String, String> options = new HashMap<>();
    private List<Option> OptionsClasses = new ArrayList<>();

    public HashMap<String, String> getOptions() {
        return options;
    }

    public List<String> getPositionalArgs() {
        return positionalArgs;
    }

    public OptionsParser(String[] args) {
        cliOptions = new Options();
        parser = new DefaultParser();

        try {
            parseOptions(args);
        } catch (ParseException ex) {
            ErrorHandling.printError("Failed to parse arguments: ", ex);
            return;
        } catch (Exception e) {
            ErrorHandling.printError("An error occured when parsing options", e);
        }

        if (positionalArgs.isEmpty()) {
            ErrorHandling.printError("Missing command. Please provide a command.", new Exception());
        }
    }

    public String getCommand() {
        return (positionalArgs != null && !positionalArgs.isEmpty()) ? positionalArgs.get(0) : null;
    }


    private void parseOptions(String[] args) throws Exception {
        CommandLine cmd;

        for (Class<? extends Option> optionClass : OptionsContainer.allOptions) {
            Option option = optionClass.newInstance();
            cliOptions.addRequiredOption(option.getOption(), "source", true, option.getDescription());
            OptionsClasses.add(option);
        }

        cmd = parser.parse(cliOptions, args);

        for (Option option : OptionsClasses) {
            String optionName = option.getOptionName();
            options.put(optionName, cmd.getOptionValue(option.getOption()));
        }

        positionalArgs = cmd.getArgList();
    }

}

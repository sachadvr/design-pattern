package com.fges.todoapp;

import com.fges.todoapp.options.Option;
import com.fges.todoapp.options.OptionsContainer;
import org.apache.commons.cli.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class OptionsParser {

    private final Options cliOptions;
    private final CommandLineParser parser;
    private List<String> positionalArgs;
    private final HashMap<String, String> options = new HashMap<>();
    private final List<Option> optionsClasses = new ArrayList<>();

    public Map<String, String> getOptions() {
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


    private void parseOptions(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ParseException {
        CommandLine cmd;

        for (Class<? extends Option> optionClass : OptionsContainer.allOptions) {
            Option option = optionClass.getDeclaredConstructor().newInstance();
            if (Boolean.TRUE.equals(option.isRequired())) {
                cliOptions.addRequiredOption(option.getOption(), option.getLongOption(), option.hasArgs(), option.getDescription());
            }else {
                cliOptions.addOption(option.getOption(), option.getLongOption(), option.hasArgs(), option.getDescription());
            }
            optionsClasses.add(option);
        }
        cmd = parser.parse(cliOptions, args);
        for (Option option : optionsClasses) {
            String optionName = option.getOptionName();

            if(cmd.hasOption(option.getOption())) {
                options.put(optionName, cmd.getOptionValue(option.getOption()));
            }

        }
        positionalArgs = cmd.getArgList();
    }

}

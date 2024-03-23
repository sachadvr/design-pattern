package com.fges.todoapp.parser;

import com.fges.todoapp.ErrorHandling;
import com.fges.todoapp.options.Option;
import com.fges.todoapp.tools.OptionManager;
import org.apache.commons.cli.*;

import java.util.*;

public class OptionsParser {

    private CommandLine cmd;

    private final Options cliOptions;
    private final CommandLineParser parser;
    private List<String> positionalArgs;
    private final HashMap<String, String> options = new HashMap<>();
    private final List<Option> optionsClasses = new ArrayList<>();


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

            return;
        }

        if (positionalArgs.isEmpty()) {
            ErrorHandling.printError("Missing command. Please provide a command.", new Exception());
        }
    }

    public OptionManager getManager() {
        return new OptionManager(this.options, this.positionalArgs);
    }

    public List<Option> getOptionsClasses() {
        List<Map<String, String>> optionMappings = new YamlParser().loadCommandMappings("/options.yaml", "options");

        optionMappings.forEach(mapping -> {
            String fullClassName = mapping.get("class");
            try {
                Class<? extends Option> optionClass = (Class<? extends Option>) Class.forName(fullClassName);
                Option option = optionClass.getDeclaredConstructor().newInstance();
                optionsClasses.add(option);
            } catch (Exception e) {
                ErrorHandling.printError("Failed to load options", e);
            }
        });

        return optionsClasses;

    }

    private void parseOptions(String[] args) throws ParseException {
        for (Option option : getOptionsClasses()) {
            if (Boolean.TRUE.equals(option.isRequired())) {
                cliOptions.addRequiredOption(option.getOption(), option.getLongOption(), option.hasArgs(), option.getDescription());
            }else {
                cliOptions.addOption(option.getOption(), option.getLongOption(), option.hasArgs(), option.getDescription());
            }
        }
        cmd = parser.parse(cliOptions, args);
        processCommandOptions();
        positionalArgs = cmd.getArgList();

    }

    private void processCommandOptions() {
        for (Option option : optionsClasses) {
            String optionName = option.getOptionName();

            if(cmd.hasOption(option.getOption())) {
                options.put(optionName, cmd.getOptionValue(option.getOption()));
            }

        }
    }



}

package com.fges.todoapp;


import org.apache.commons.cli.*;

import java.util.List;

/**
 * Hello world!
 */
public class OptionsParser {

    private final Options cliOptions;

    private final CommandLineParser parser;

    private List<String> positionalArgs;

    private String fileName;

    public String getFileName() {
        return fileName;
    }
    public List<String> getPositionalArgs() {
        return positionalArgs;
    }

    public OptionsParser(String[] args) {
        cliOptions = new Options();
        parser = new DefaultParser();

        this.getOptions(args);
    }

    public String getCommand() {
        return this.getPositionalArgs().get(0);
    }
    public void getOptions(String[] args) {
        cliOptions.addRequiredOption("s", "source", true, "File containing the todos");
        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException ex) {
            System.err.println("Fail to parse arguments: " + ex.getMessage());
            return;
        }
        fileName = cmd.getOptionValue("s");
        positionalArgs = cmd.getArgList();
        if (positionalArgs.isEmpty()) {
            System.err.println("Missing Command");
        }
    }
}

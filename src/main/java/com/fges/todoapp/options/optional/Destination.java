package com.fges.todoapp.options.optional;


import com.fges.todoapp.options.Option;

/**
 * Hello world!
 */
public class Destination extends Option {
    public static String option = "o";
    public static String longoption = "output";

    public Destination() {
        super(option, longoption);
    }

    public String getOptionName() {
        return "output";
    }
    public String getDescription() {
        return "to change csv to json or json to csv";
    }

    public Boolean hasArgs() {
        return true;
    }

    public Boolean isRequired() {
        return false;
    }
}

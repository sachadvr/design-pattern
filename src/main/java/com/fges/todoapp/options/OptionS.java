package com.fges.todoapp.options;


/**
 * Hello world!
 */
public class OptionS extends Option {
    public static String option = "s";
    public static String longoption = "source";

    public OptionS() {
        super(option, longoption);
    }

    public String getOptionName() {
        return "fileName";
    }
    public String getDescription() {
        return "File containing the todos";
    }

    public Boolean isRequired() {
        return true;
    }
}

package com.fges.todoapp.options;


/**
 * Hello world!
 */
public class OptionS extends Option {
    public static String option = "s";
    public OptionS() {
        super(option);
    }

    public String getOptionName() {
        return "fileName";
    }
    public String getDescription() {
        return "File containing the todos";
    }
}

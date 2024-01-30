package com.fges.todoapp.options;


/**
 * Hello world!
 */
public class OptionDone extends Option {
    public static String option = "d";
    public static String longoption = "done";

    public OptionDone() {
        super(option, longoption);
    }

    public String getOptionName() {
        return "isDone";
    }
    public String getDescription() {
        return "Done options";
    }

    public Boolean isRequired() {
        return false;
    }
}

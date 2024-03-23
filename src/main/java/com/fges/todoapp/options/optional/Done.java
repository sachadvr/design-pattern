package com.fges.todoapp.options.optional;


import com.fges.todoapp.options.Option;

/**
 * Hello world!
 */
public class Done extends Option {
    public static String option = "d";
    public static String longoption = "done";

    public Done() {
        super(option, longoption);
    }

    public String getOptionName() {
        return "isDone";
    }
    public String getDescription() {
        return "Done options";
    }

    public Boolean hasArgs() {
        return false;
    }

    public Boolean isRequired() {
        return false;
    }
}

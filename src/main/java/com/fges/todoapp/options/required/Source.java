package com.fges.todoapp.options.required;


import com.fges.todoapp.options.Option;

/**
 * Hello world!
 */
public class Source extends Option {
    public static String option = "s";
    public static String longoption = "source";

    public Source() {
        super(option, longoption);
    }

    public String getOptionName() {
        return "fileName";
    }
    public String getDescription() {
        return "File containing the todos";
    }

    public Boolean hasArgs() {
        return true;
    }

    public Boolean isRequired() {
        return true;
    }
}

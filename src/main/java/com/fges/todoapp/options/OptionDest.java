package com.fges.todoapp.options;


/**
 * Hello world!
 */
public class OptionDest extends Option {
    public static String option = "dest";
    public static String longoption = "destination";

    public OptionDest() {
        super(option, longoption);
    }

    public String getOptionName() {
        return "destination";
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

package com.fges.todoapp.options;


/**
 * Hello world!
 */
abstract public class Option {

    public String option = null;
    public Option(String option) {
        this.option = option;
    }

    public String getOption() {
        return option;
    }

    public String getOptionName() {
        return null;
    }

    public String getDescription() {
        return null;
    }
}

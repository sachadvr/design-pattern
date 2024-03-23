package com.fges.todoapp.options;


/**
 * Hello world!
 */
abstract public class Option implements OptionInterface {

    public String option = null;
    public String longoption = null;
    public Option(String option, String longoption) {
        this.option = option;
        this.longoption = longoption;
    }

    public String getOption() {
        return option;
    }
    public String getLongOption() {
        return longoption;
    }

}

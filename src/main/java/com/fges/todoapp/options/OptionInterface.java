package com.fges.todoapp.options;


/**
 * Hello world!
 */
interface OptionInterface {

    public String getOption();
    public String getLongOption();

    public String getOptionName();

    public String getDescription();

    public Boolean hasArgs();
    public Boolean isRequired();
}

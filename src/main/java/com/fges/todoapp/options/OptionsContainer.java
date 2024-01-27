package com.fges.todoapp.options;


import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class OptionsContainer {
    public static List<Class<? extends Option>> allOptions = new ArrayList<>();

    static {
        allOptions.add(OptionS.class);
    }
}
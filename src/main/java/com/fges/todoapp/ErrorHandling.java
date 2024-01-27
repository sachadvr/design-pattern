package com.fges.todoapp;

public class ErrorHandling {
    public static void printError(String err, Exception ex) {
        System.out.println("[/!\\] " + err + (ex != null ? ex : ""));
    }
    public static void printError(String err) {
        printError(err, null);
    }
}

package com.fges.todoapp;

public class ErrorHandling {
    public static void printError(String err, Exception ex) {
        System.err.println("----------------------------------------------------------------");
        System.err.println("[/!\\] " + err + (ex != null ? "\n" + ex.getMessage(): ""));
        System.err.println("----------------------------------------------------------------");
    }
    public static void printError(String err) {
        printError(err, null);
    }
}

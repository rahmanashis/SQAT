package com.ashis.sqat;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Pass the fully qualified name of an application class with a main method.");
            return;
        }

        try {
            Class<?> applicationClass = Class.forName(args[0]);
            applicationClass.getMethod("main", String[].class)
                    .invoke(null, (Object) new String[0]);
        } catch (ReflectiveOperationException exception) {
            throw new IllegalArgumentException(
                    "Could not launch application class: " + args[0], exception);
        }
    }
}
package ru.otus.daniil.console;


import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        ConsoleManager manager = new ConsoleManager();

        try {
            manager.run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

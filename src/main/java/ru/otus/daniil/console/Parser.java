package ru.otus.daniil.console;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    public static boolean parsedSuccessfully = true;

    public static Map<String, String> parse(String[] command, List<String> reqParams) {
        Map<String, String> map = new HashMap<>();
        //сквозной итератор
        int i = 0;

        for (; i < reqParams.size(); i++) {
            try {
                map.put(reqParams.get(i), command[i]);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Illegal value for \"" + reqParams.get(i) + "\" parameter");
                parsedSuccessfully = false;
                return null;
            }
        }

        for (; i < command.length; i++) {
            map.put(command[i], "1"); //наименование ключа и его контент если есть
        }

        parsedSuccessfully = true;
        return map;
    }
}

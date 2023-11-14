package ru.otus.daniil.console;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Command {

    List<String> getRequiredParams();

    void execute(Map<String, String> map) throws IOException;

}

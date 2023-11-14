package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ChangeDirectory implements Command {

    private ConsoleManager manager;

    public ChangeDirectory(ConsoleManager consoleManager) {
        this.manager = consoleManager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("path");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {

        String newPath = manager.getAbsolutePath(map.get("path"));

        if (new File(newPath).isDirectory()) {
            manager.setPath(new File(newPath).getCanonicalPath());
        } else {
            System.out.println("Directory is not exists");
        }
    }
}

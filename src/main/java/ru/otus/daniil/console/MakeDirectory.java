package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MakeDirectory implements Command {

    private ConsoleManager manager;

    public MakeDirectory(ConsoleManager consoleManager) {
        this.manager = consoleManager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("name");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {
        String name = map.get("name");
        if (name.isEmpty()) {
            System.out.println("Wrong name");
            return;
        }
        //System.out.println(manager.getPath());
        File file = new File(manager.getPath() + File.separator + name);

        if (file.isDirectory()) {
            System.out.println("Directory already exists");
            return;
        }
        //System.out.println(file.getAbsolutePath());
        if (!file.mkdir()) {
            System.out.println("Unknown error. Directory is not created");
        }
    }
}

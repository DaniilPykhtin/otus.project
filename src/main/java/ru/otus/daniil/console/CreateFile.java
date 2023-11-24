package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CreateFile implements Command {
    private ConsoleManager manager;

    public CreateFile(ConsoleManager manager) {
        this.manager = manager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("name");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {


        if (!map.get("name").contains(".")) {
            System.out.println("Filename must contain extension");
            return;
        }

        File newFile = new File(manager.getAbsolutePath(map.get("name")));

        if (newFile.exists()) {
            System.out.println("File already exists");
            return;
        }

        if (!newFile.createNewFile()) {
            System.out.println("Unknown error. Cannot touch file");
        }


    }
}

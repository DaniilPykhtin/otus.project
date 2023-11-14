package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class ListFiles implements Command {

    private ConsoleManager manager;

    public ListFiles(ConsoleManager manager) {
        this.manager = manager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of();
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {

        String path = manager.getPath();
        if (map.get("-l") != null) {
            for (File file : new File(path).listFiles()) {
                System.out.printf("%4s %10s %10s %5s %10s %s",
                        file.isDirectory() ? "d---" : "----",
                        Files.getOwner(Paths.get(path, file.getName())),
                        file.getUsableSpace(),
                        Files.size(Paths.get(path, file.getName())),
                        new Date(file.lastModified()),
                        file.getName());

                System.out.println();
            }
            return;
        }
        for (File file : new File(path).listFiles()) {
            if (file.getName().startsWith(".")) {
                continue;
            }
            System.out.printf("%4s %s", file.isDirectory() ? "d---" : "----", file.getName());
            System.out.println();
        }
    }
}

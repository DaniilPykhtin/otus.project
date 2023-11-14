package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Date;

public class FileInfo implements Command {

    private ConsoleManager manager;

    public FileInfo(ConsoleManager manager) {
        this.manager = manager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("filename");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {

        File file = new File(manager.getAbsolutePath(map.get("filename")));

        if (!file.isFile()) {
            System.out.println("File doesn't exist");
            return;
        }
        System.out.println("Filename: " + file.getName());
        System.out.println("Parentfile: " + file.getParentFile().getName());
        System.out.println("Last Modified: " + new Date(file.lastModified()));
        System.out.println("Can read: " + (file.canRead() ? "Y" : "N"));
        System.out.println("Can write: " + (file.canWrite() ? "Y" : "N"));
        System.out.println("Can execute: " + (file.canExecute() ? "Y" : "N"));
    }
}
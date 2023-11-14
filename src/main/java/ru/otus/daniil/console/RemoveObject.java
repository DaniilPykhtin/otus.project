package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RemoveObject implements Command {
    private ConsoleManager manager;

    public RemoveObject(ConsoleManager consoleManager) {
        this.manager = consoleManager;
    }

    private void deleteFolderRecursive(File parentFile) {

        if (parentFile.listFiles().length == 0) {
            parentFile.delete();
            return;
        }

        for (File file : parentFile.listFiles()) {
            if (file.isDirectory()) {
                deleteFolderRecursive(file);

            } else {
                if (!file.delete()) {
                    System.out.println("Unknown error. Object \"" + file.getPath() + "\" cannot be deleted");
                }
            }
        }
        if (!parentFile.delete()) {
            System.out.println("Unknown error. Directory\"" + parentFile.getPath() + "\" cannot be deleted");
        }
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("path");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {

        File file = new File(manager.getAbsolutePath(map.get("path")));

        if (!file.exists()) {
            System.out.println("Object doesn't exist");
            return;
        }

        if (file.isFile()) {
            if (!file.delete()) {
                System.out.println("Unknown error. File was not deleted");
            }
            return;
        }

        if (file.listFiles().length > 0) {
            if (map.get("-r") != null) {
                deleteFolderRecursive(file);
            } else {
                System.out.println("Directory has content and cannot be deleted");
            }
        } else {
            if (!file.delete()) {
                System.out.println("Unknown error. Directory was not deleted");
            }
        }
    }
}

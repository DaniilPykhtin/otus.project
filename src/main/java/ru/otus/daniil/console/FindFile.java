package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FindFile implements Command {

    private ConsoleManager manager;

    public FindFile(ConsoleManager manager) {
        this.manager = manager;
    }

    private void findFileRecursive(File parentFile, String fileToFind) {
        for (File file : parentFile.listFiles()) {
            if (file.isDirectory()) {
                findFileRecursive(file, fileToFind);
            }
            if (file.isFile()) {
                if (file.getName().equals(fileToFind)) {
                    System.out.println(file.getAbsoluteFile());
                }
            }
        }
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("filename");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {
        File parentFile = new File(manager.getPath());
        String fileToFind = map.get("filename");

        findFileRecursive(parentFile, fileToFind);
    }
}

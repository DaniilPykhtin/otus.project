package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MoveObject implements Command {

    private ConsoleManager manager;

    public MoveObject(ConsoleManager manager) {
        this.manager = manager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of("source", "destination");
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {
        File sourceFile = new File(manager.getAbsolutePath(map.get("source")));
        File destinationFile = new File(manager.getAbsolutePath(map.get("destination")));

        if (!sourceFile.exists()) {
            System.out.println("Source object doesn't exist");
            return;
        }

        if (destinationFile.isDirectory()) {
            destinationFile = new File(destinationFile.getAbsoluteFile() + File.separator + sourceFile.getName());
        }
        if (destinationFile.isFile()) {
            System.out.println("Cannot rewrite existing file in destination directory");
            return;
        }

        //System.out.println("Переношу " + sourceFile.getAbsolutePath() + " в " + destinationFile.getAbsolutePath());
        if (!sourceFile.renameTo(destinationFile.getCanonicalFile())) {
            System.out.println("Unknown error. Cannot move/rename source object");
        }
    }
}

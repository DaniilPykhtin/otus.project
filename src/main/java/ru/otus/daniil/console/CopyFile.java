package ru.otus.daniil.console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class CopyFile implements Command {

    private ConsoleManager manager;

    public CopyFile(ConsoleManager manager) {
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

        if (!sourceFile.isFile()) {
            System.out.println("File doesn't exist or cannot be accessed");
            return;
        }

        if (!destinationFile.isDirectory()) {
            System.out.println("Illegal destination directory");
            return;
        }
        //добавим наименование файла в таргет
        destinationFile = new File(destinationFile.getAbsolutePath() + File.separator + sourceFile.getName());

        if (destinationFile.exists()) {
            System.out.println("Cannot copy file. Destination file already exists");
            return;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(sourceFile.getAbsoluteFile(), StandardCharsets.UTF_8));
            BufferedWriter bw = new BufferedWriter(new FileWriter(destinationFile.getAbsoluteFile(), StandardCharsets.UTF_8));

            String line;

            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();

            }
            bw.flush();

        } catch (IOException e) {
            System.out.println("Unknown error. Cannot copy file");
            throw e;
        }
    }
}

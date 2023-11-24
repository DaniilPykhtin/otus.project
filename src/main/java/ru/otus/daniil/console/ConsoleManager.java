package ru.otus.daniil.console;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleManager {

    private String path;
    private final String root;
    private Scanner scanner = new Scanner(System.in);
    private Map<String, Command> router = new HashMap<>();

    public String getPath() {
        return path;
    }

    //    public String getRoot() {
//        return root;
//    }
    public void setPath(String path) {
        this.path = path;
    }

    public ConsoleManager() {
        path = new File(".").getAbsolutePath().replace(File.separator + ".", "");
        root = Paths.get(new File(".").getAbsolutePath()).getRoot().toString();
        router.put("ls", new ListFiles(this));
        router.put("cd", new ChangeDirectory(this));
        router.put("mkdir", new MakeDirectory(this));
        router.put("touch", new CreateFile(this));
        router.put("rm", new RemoveObject(this));
        router.put("mv", new MoveObject(this));
        router.put("cp", new CopyFile(this));
        router.put("help", new Help(this));
        router.put("finfo", new FileInfo(this));
        router.put("find", new FindFile(this));
    }

    public String getAbsolutePath(String path) {
        return path.startsWith(root) ? path : this.path + File.separator + path;
    }

    private void printCurrentDir() {
        System.out.print(path + File.separator + ">");
    }

    private String[] splitCommand(String command) {
        return command.split(" ");
    }

    public void run() throws IOException {

        while (true) {

            printCurrentDir();
            String[] splittedCommand = splitCommand(scanner.nextLine());
            //System.out.println(Arrays.toString(splittedCommand));
            String name = splittedCommand[0];

            if (name.equals("exit")) {
                break;
            }

            Command currentCommand = router.get(name);

            if (currentCommand == null) {
                System.out.println("Команды не существует");
                continue;
            }
            splittedCommand = Arrays.copyOfRange(splittedCommand, 1, splittedCommand.length);

            Map<String, String> commandMap = Parser.parse(splittedCommand, currentCommand.getRequiredParams());

            if (Parser.parsedSuccessfully) {
                currentCommand.execute(commandMap);
            }
        }
    }
}
package ru.otus.daniil.console;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Help implements Command {
    ConsoleManager manager;

    public Help(ConsoleManager manager) {
        this.manager = manager;
    }

    @Override
    public List<String> getRequiredParams() {
        return List.of();
    }

    @Override
    public void execute(Map<String, String> map) throws IOException {
        System.out.println("ls – распечатать список файлов текущего каталога. Если добавлен ключ -l, то будет распечатана\n" +
                "\tболее подробная информация о файлах: имя – размер – дата последнего изменения");
        System.out.println("cd [path] – переход в указанную поддиректорию. cd .. – переход в родительский каталог");
        System.out.println("mkdir [name] – создание новой директории с указанным именем");
        System.out.println("rm [filename] – удаление указанного файла или директории. Если добавлен ключ -r, то будет рекурсивно\n" +
                "\tудалена непустая директория");
        System.out.println("mv [source] [destination] – переименовать/перенести файл или директорию");
        System.out.println("cp [source] [destination] – скопировать файл");
        System.out.println("finfo [filename] – получить подробную информацию о файле");
        System.out.println("find [filename] – найти файл с указанным именем в текущем каталоге или любом его подкаталоге");
        System.out.println("exit – завершить работу");
    }
}

package org.kaoden.ws.homework;

import org.kaoden.ws.homework.obj.Entry;
import org.kaoden.ws.homework.service.Finder;
import org.kaoden.ws.homework.service.Reader;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Menu {

    private static final String MODE_OPTIONS = """
            Варианты поиска:
            1. Найти запись по UUID
            2. Найти запись на наименованию
            Вариант (1/2), Выход (3):
            """;
    private static final String INVALID_MODE_MESSAGE = "Неверный режим поиска.";

    private static final int FIND_BY_UUID = 1;
    private static final int FIND_BY_NAME = 2;
    private static final int EXIT_MODE = 3;

    private final Scanner scanner = new Scanner(System.in);

    public void selectSearchMode(String filePath) {
        Map<UUID, Entry> entries = getEntries(filePath);
        Finder finder = new Finder(entries);

        int mode = setMode();
        while (mode != EXIT_MODE) {
            switch (mode) {
                case FIND_BY_UUID -> finder.findByUUID();
                case FIND_BY_NAME -> finder.findByName();
                default -> System.out.println(INVALID_MODE_MESSAGE);
            }
            System.out.println();
            mode = setMode();
        }
    }

    private int setMode() {
        System.out.print(MODE_OPTIONS);
        return scanner.nextInt();
    }

    private Map<UUID, Entry> getEntries(String filePath) {
        Reader reader = new Reader();
        Map<UUID, Entry> entries;
        try {
            entries = reader.readEntriesFromJson(filePath);
        } catch (IOException ioe) {
            throw new RuntimeException("An error occurred while reading file: " +filePath);
        }
        return entries;
    }
}

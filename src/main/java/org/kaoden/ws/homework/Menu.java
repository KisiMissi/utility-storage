package org.kaoden.ws.homework;

import org.kaoden.ws.homework.service.Finder;

import java.util.Scanner;

public class Menu {

    private static final String MODE_OPTIONS = """
            Варианты поиска:
            1. Найти запись по UUID
            2. Найти запись на наименованию
            Вариант (1/2), Выход (3):
            """;
    private static final String INVALID_MODE_MESSAGE = "Неверный режим поиска.";

    private static final String UUID_ENTRY_MESSAGE = "Введите UUID записи: ";
    private static final String NAME_ENTRY_MESSAGE = "Введите название записи: ";

    private static final int FIND_BY_UUID = 1;
    private static final int FIND_BY_NAME = 2;
    private static final int EXIT_MODE = 3;

    private final Scanner scanner = new Scanner(System.in);
    private final Finder entryFinder;

    public Menu(Finder entryFinder) {
        this.entryFinder = entryFinder;
    }

    public void selectSearchMode() {
        int mode = setMode();
        while (mode != EXIT_MODE) {
            switch (mode) {
                case FIND_BY_UUID -> findEntryByUUID();
                case FIND_BY_NAME -> findEntryByName();
                default -> System.out.println(INVALID_MODE_MESSAGE);
            }
            System.out.println();
            mode = setMode();
        }
    }

    private int setMode() {
        System.out.print(MODE_OPTIONS);
        return Integer.parseInt(scanner.nextLine());
    }

    private void findEntryByUUID() {
        System.out.print(UUID_ENTRY_MESSAGE);
        entryFinder.findByUUID(scanner.nextLine());
    }

    private void findEntryByName() {
        System.out.print(NAME_ENTRY_MESSAGE);
        entryFinder.findByName(scanner.nextLine());
    }
}

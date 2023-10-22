package org.kaoden.ws.homework.service;

import org.kaoden.ws.homework.obj.Entry;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Finder {

    private static final String UUID_ENTRY_MESSAGE = "Введите UUID записи: ";
    private static final String NAME_ENTRY_MESSAGE = "Введите название записи: ";
    private static final String INVALID_UUID_MESSAGE = "Неверный UUID.";

    private final Map<UUID, Entry> entries;
    private final Scanner sc = new Scanner(System.in);

    public Finder(Map<UUID, Entry> entries) {
        this.entries = entries;
    }

    public void findByUUID() {
        System.out.print(UUID_ENTRY_MESSAGE);
        UUID uuid;
        try {
            uuid = UUID.fromString(sc.nextLine());
        } catch (IllegalArgumentException ex) {
            System.out.println(INVALID_UUID_MESSAGE);
            return;
        }
        if (entries.containsKey(uuid))
            System.out.println(entries.get(uuid));
        else
            System.out.println("There is no entry with that " + uuid + " UUID");
    }

    public void findByName() {
        System.out.print(NAME_ENTRY_MESSAGE);
        String targetName = sc.nextLine();
        for (Map.Entry<UUID, Entry> entry : entries.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(targetName.toLowerCase()))
                System.out.println(entry.getValue());
        }
    }
}

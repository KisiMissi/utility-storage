package org.kaoden.ws.homework.service;

import org.kaoden.ws.homework.obj.Entry;

import java.util.Map;
import java.util.UUID;

public class Finder {

    private static final String INVALID_UUID_MESSAGE = "Неверный UUID.";

    private final Map<UUID, Entry> entries;

    public Finder(Map<UUID, Entry> entries) {
        this.entries = entries;
    }

    public void findByUUID(String stringUUID) {
        UUID uuid;
        try {
            uuid = UUID.fromString(stringUUID);
        } catch (IllegalArgumentException ex) {
            System.out.println(INVALID_UUID_MESSAGE);
            return;
        }
        if (entries.containsKey(uuid))
            System.out.println(entries.get(uuid));
        else
            System.out.println("There is no entry with that " + uuid + " UUID");
    }

    public void findByName(String targetName) {
        for (Map.Entry<UUID, Entry> entry : entries.entrySet()) {
            if (entry.getValue().getName().toLowerCase().contains(targetName.toLowerCase())) {
                System.out.println(entry.getValue());
            }
        }
    }
}

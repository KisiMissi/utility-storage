package org.kaoden.ws.homework.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kaoden.ws.homework.obj.Entry;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FinderTest {

    private final ByteArrayOutputStream outStreamCaptor = new ByteArrayOutputStream();
    private static Map<UUID, Entry> entries;

    @BeforeAll
    static void setEntries() {
        entries = new HashMap<>();

        Entry entry = new Entry();
        UUID uuid = UUID.fromString("1a92d32f-8273-4c48-b5e1-3e0b762a7c11");
        entry.setUuid(uuid);
        entry.setName("Тестовый материал 1");
        entries.put(uuid, entry);

        entry = new Entry();
        uuid = UUID.fromString("bd1b34fc-5397-4c11-a24a-8e75f04536a2");
        entry.setUuid(uuid);
        entry.setName("Тестовый материал 2");
        entries.put(uuid, entry);
    }

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outStreamCaptor));
    }

    void providerInput(String data) {
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    void findByInvalidUUID() {
        // Arrange
        String testUUID = "1a92d32f-8273";
        providerInput(testUUID);

        // Act
        new Finder(entries).findByUUID();

        // Assert
        assertEquals(
                "Введите UUID записи: Неверный UUID.",
                outStreamCaptor.toString().trim()
        );
    }

    @Test
    void findByValidUUID() {
        // Arrange
        String testUUID = "1a92d32f-8273-4c48-b5e1-3e0b762a7c11";
        providerInput(testUUID);
        String expectedOutput = "Введите UUID записи: Entry: uuid=1a92d32f-8273-4c48-b5e1-3e0b762a7c11, name='Тестовый материал 1', description='null', link='null";

        // Act
        new Finder(entries).findByUUID();

        // Assert
        assertEquals(expectedOutput, outStreamCaptor.toString().trim());
    }

    @Test
    void findByNonexistentName() {
        // Arrange
        String name = "some-text";
        providerInput(name);

        // Act
        new Finder(entries).findByName();

        // Assert
        assertEquals(
                "Введите название записи:",
                outStreamCaptor.toString().trim()
        );
    }

    @Test
    void findByPartName() {
        // Arrange
        String name = "Тестовый материал";
        providerInput(name);
        String expectedOutput = "Введите название записи: Entry: uuid=1a92d32f-8273-4c48-b5e1-3e0b762a7c11, name='Тестовый материал 1', description='null', link='null\r\n" +
        "Entry: uuid=bd1b34fc-5397-4c11-a24a-8e75f04536a2, name='Тестовый материал 2', description='null', link='null";

        // Act
        new Finder(entries).findByName();

        // Assert
        assertEquals(expectedOutput, outStreamCaptor.toString().trim());
    }

}
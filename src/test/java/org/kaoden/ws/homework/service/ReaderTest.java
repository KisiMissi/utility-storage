package org.kaoden.ws.homework.service;

import org.junit.jupiter.api.Test;
import org.kaoden.ws.homework.obj.Entry;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    private final Reader reader = new Reader();

    @Test
    void invalidFilePath() {
        // Arrange
        String filePath = "test.json";
        String expectedMessage = "File with this location \""+filePath+"\" doesn't exist";

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reader.readEntriesFromJson(filePath));

        // Asset
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void emptyDataFile() {
        // Arrange
        String filePath = "src\\test\\resources\\emptyData.json";
        Map<UUID, Entry> expectedEntries = new HashMap<>();

        // Act
        Map<UUID, Entry> actualEntries = reader.readEntriesFromJson(filePath);

        // Assert
        assertEquals(expectedEntries, actualEntries);
    }

    @Test
    void invalidKeyNameInDataFile() {
        // Arrange
        String filePath = "src\\test\\resources\\dataWithInvalidKeyName.json";
        String expectedMessage = "An error occurred while reading file: " + filePath;

        // Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> reader.readEntriesFromJson(filePath));

        // Assert
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void someFieldsMissingInDataFile() {
        String filePath = "src\\test\\resources\\dataWithMissingFields.json";

        // Act
        Map<UUID, Entry> actualEntries = reader.readEntriesFromJson(filePath);

        // Arrange
        assertAll(
                () -> assertNull(actualEntries.get(null).getUuid()),
                () -> assertNull(actualEntries.get(null).getName()),
                () -> assertEquals("Описание полезного материала", actualEntries.get(null).getDescription()),
                () -> assertEquals("https://example.com/material", actualEntries.get(null).getLink())
        );
    }

    @Test
    void readEntryWithoutAllFields() {
        // Arrange
        String filePath = "src\\test\\resources\\dataWithEntryWithoutAllFields.json";

        // Act
        Map<UUID, Entry> actualEntries = reader.readEntriesFromJson(filePath);

        // Assert
        assertAll(
                () -> assertNull(actualEntries.get(null).getUuid()),
                () -> assertNull(actualEntries.get(null).getName()),
                () -> assertNull(actualEntries.get(null).getDescription()),
                () -> assertNull(actualEntries.get(null).getLink())
        );
    }
}
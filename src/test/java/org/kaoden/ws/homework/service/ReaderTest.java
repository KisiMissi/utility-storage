package org.kaoden.ws.homework.service;

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.junit.jupiter.api.Test;
import org.kaoden.ws.homework.obj.Entry;

import java.io.IOException;
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

        // Asset
        assertThrows(RuntimeException.class, () -> reader.readEntriesFromJson(filePath));
    }

    @Test
    void emptyDataFile() throws IOException {
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

        // Assert
        assertThrows(UnrecognizedPropertyException.class, () -> reader.readEntriesFromJson(filePath));
    }

    @Test
    void someFieldsMissingInDataFile() throws IOException {
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

}
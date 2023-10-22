package org.kaoden.ws.homework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private final Menu menu = new Menu();

    @Test
    void nonexistentFile() {
        // Arrange
        String filePath = "src\\test\\resources\\data.json";

        // Assert
        assertThrows(RuntimeException.class, () -> menu.selectSearchMode(filePath));
    }
}
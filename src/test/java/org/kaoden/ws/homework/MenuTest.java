package org.kaoden.ws.homework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kaoden.ws.homework.service.Finder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Mock
    private Finder finder;

    @BeforeEach
    void setMockitoAnnotations() {
        MockitoAnnotations.openMocks(this);
    }

    void provideInput(String data) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }

    @Test
    void findByUUID() {
        // Arrange
        String modeAndUUID = "1" + System.lineSeparator() + "1a92d32f-8273-4c48-b5e1-3e0b762a7c11" + System.lineSeparator() + "3";
        provideInput(modeAndUUID);

        // Act
        new Menu(finder).selectSearchMode();

        // Assert
        verify(finder).findByUUID("1a92d32f-8273-4c48-b5e1-3e0b762a7c11");
    }

    @Test
    void findByName() {
        // Arrange
        String modeAndUUID = "2" + System.lineSeparator() + "SOME-NAME" + System.lineSeparator() + "3";
        provideInput(modeAndUUID);

        // Act
        new Menu(finder).selectSearchMode();

        // Assert
        verify(finder).findByName("SOME-NAME");
    }

    @Test
    void invalidSearchModeNumber() {
        // Arrange
        String mode = "100\n3";
        provideInput(mode);
        ByteArrayOutputStream outStreamCap = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStreamCap));

        // Act
        new Menu(finder).selectSearchMode();

        // Arrange
        assertTrue(outStreamCap.toString().contains("Неверный режим поиска."));
    }

    @Test
    void invalidSearchModeType() {
        // Arrange
        String invalidMode = "MODE\n3";
        provideInput(invalidMode);

        // Act
        NumberFormatException exception = assertThrows(NumberFormatException.class, () -> new Menu(finder).selectSearchMode());

        // Assert
        assertEquals("For input string: \"MODE\"", exception.getMessage());
    }

}
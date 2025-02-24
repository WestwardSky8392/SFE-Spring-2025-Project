package project.annotations.NetworkAPI;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Unit test for the Screen interface.
 * Ensures that showWindow() correctly interacts with AskUser and returns a Window instance.
 */
public class TestScreen {

    @Mock
    private Screen mockScreen;

    @Mock
    private AskUser mockAskUser;

    @Mock
    private Window mockWindow;

    /**
     * Sets up the mock dependencies before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that showWindow() correctly returns a Window instance.
     */
    @Test
    void testShowWindow() {
        // Arrange
        when(mockScreen.showWindow(mockAskUser)).thenReturn(mockWindow);

        // Act
        Window result = mockScreen.showWindow(mockAskUser);

        // Assert
        assertEquals(mockWindow, result, "showWindow should return the expected Window instance.");
        verify(mockScreen).showWindow(mockAskUser);
    }
}

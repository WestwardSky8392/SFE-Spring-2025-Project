package project.annotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import project.apis.networkapi.Screen;
import project.apis.networkapi.AskUser;
import project.apis.networkapi.Window;

/**
 * Test for Screen-related functionality.
 * This test mocks the dependencies (Screen, AskUser, Window) 
 */
public class TestScreen {

    @Mock
    private Screen mockScreen;
    
    @Mock
    private AskUser mockAskUser;
    
    @Mock
    private Window mockWindow;

    /**
     * Sets up test dependencies before each test.
     * Initializes Mockito mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the showWindow() method of Screen interface.
     */
    @Test
    void testShowWindow() {
        // Arrange: mock the behavior of the showWindow method.
        when(mockScreen.showWindow(mockAskUser)).thenReturn(mockWindow);
        
        // Act: call the method being tested.
        Window result = mockScreen.showWindow(mockAskUser);

        // Assert: verify the expected behavior.
        assertSame(mockWindow, result, "The returned Window should be the one mocked.");
        verify(mockScreen).showWindow(mockAskUser);
    }
}

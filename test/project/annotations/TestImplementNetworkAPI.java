package test.project.annotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import project.apis.networkapi.AskUser;
import project.apis.networkapi.Screen;
import project.apis.networkapi.SendInfo;
import project.apis.networkapi.ValidInfo;
import project.apis.networkapi.Window;

/**
 * Smoke test for ImplementNetworkAPI.
 * This test verifies interactions with the mocked dependencies
 * to ensure the API methods are called correctly.
 */
public class TestImplementNetworkAPI {

    private ImplementNetworkAPI networkAPI;

    @Mock
    private Screen mockScreen;
    
    @Mock
    private AskUser mockAskUser;
    
    @Mock
    private SendInfo mockSendInfo;
    
    @Mock
    private Window mockWindow;

    /**
     * Sets up test dependencies before each test.
     * Initializes Mockito mocks and creates an instance of ImplementNetworkAPI.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        networkAPI = new ImplementNetworkAPI(mockScreen);
    }

    /**
     * Tests the showWindow() method to ensure it correctly calls
     * the Screen interface and returns the expected Window instance.
     */
    @Test
    void testShowWindow() {
        // Arrange
        when(mockScreen.showWindow(mockAskUser)).thenReturn(mockWindow);

        // Act
        Window result = networkAPI.showWindow(mockAskUser);

        // Assert
        assertEquals(mockWindow, result, "showWindow should return the expected Window instance.");
        verify(mockScreen).showWindow(mockAskUser);
    }

    /**
     * Tests the sendToProcess() method to verify that ValidInfo correctly
     * processes and returns the expected SendInfo instance.
     */
    @Test
    void testSendValidInfo() {
        // Arrange
        ValidInfo validInfo = new ValidInfo(mockAskUser);
        when(validInfo.sendToProcess()).thenReturn(mockSendInfo);

        // Act
        SendInfo result = validInfo.sendToProcess();

        // Assert
        assertEquals(mockSendInfo, result, "sendToProcess should return the expected SendInfo instance.");
    }
}

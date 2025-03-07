// filepath: /workspaces/SFE-Spring-2025-Project/test/project/annotations/TestImplementNetworkAPI.java
package project.annotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import project.apis.networkapi.AskUser;
import project.apis.networkapi.Screen;
import project.apis.networkapi.SendInfo;
import project.apis.networkapi.TestingForErrorsProcess;
import project.apis.networkapi.ValidInfo;
import project.apis.networkapi.Window;
import project.apis.networkapi.ImplementNetworkAPI;
import project.apis.datastorage.DataStorageAPI;

/**
 * Smoke test for ImplementNetworkAPI.
 * This test verifies interactions with the mocked dependencies
 * to ensure the API methods are called correctly.
 */
public class TestImplementNetworkAPI {

    private ImplementNetworkAPI networkAPI;
    private DataStorageAPI dataStorageAPI;

    @Mock
    private Screen mockScreen;
    
    @Mock
    private AskUser mockAskUser;
    
    @Mock
    private SendInfo mockSendInfo;
    
    @Mock
    private Window mockWindow;

    @Mock
    private TestingForErrorsProcess mockTestingForErrorsProcess;

    /**
     * Sets up test dependencies before each test.
     * Initializes Mockito mocks and creates an instance of ImplementNetworkAPI.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // DataStorageAPI is not used in the current implementation, so we mock it separately.
        dataStorageAPI = mock(DataStorageAPI.class);
        networkAPI = new ImplementNetworkAPI(mockScreen);
    }

    /**
     * Tests the showWindow() method to ensure it correctly calls
     * the Screen interface and returns the expected Window instance.
     */
    @Test
    void testShowWindow() throws Exception {
        // Arrange
        when(mockScreen.showWindow(mockAskUser)).thenReturn(mockWindow);
        // Act
        Window result = networkAPI.showWindow(mockAskUser);
        // Assert
        assertEquals(mockWindow, result, "showWindow should return the expected Window instance.");
        verify(mockScreen).showWindow(mockAskUser);
    }

    /**
     * Tests the sendToProcess() method for ValidInfo.
     * Verifies that ValidInfo returns the expected SendInfo instance.
     */
    @Test
    void testSendValidInfo() throws Exception {
        // Arrange
        ValidInfo validInfo = mock(ValidInfo.class); // Create a mock ValidInfo
        when(validInfo.sendToProcess()).thenReturn(mockSendInfo);
        // Act
        SendInfo result = validInfo.sendToProcess();
        // Assert
        assertEquals(mockSendInfo, result, "sendToProcess should return the expected SendInfo instance.");
        verify(validInfo).sendToProcess();
    }

    /**
     * Tests the testingForErrorsProcess() method.
     * Verifies that it returns the expected string.
     */
    @Test
    void testTestingForErrorsProcess() throws Exception {
        // Arrange
        when(mockTestingForErrorsProcess.testingForErrorsProcess()).thenReturn("expectedString");
        // Act
        String result = mockTestingForErrorsProcess.testingForErrorsProcess();
        // Assert
        assertEquals("expectedString", result, "testingForErrorsProcess should return the expected string.");
        verify(mockTestingForErrorsProcess).testingForErrorsProcess();
    }
}
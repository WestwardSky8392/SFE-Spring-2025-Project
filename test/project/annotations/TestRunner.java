package project.annotations;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.PrintWriter;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class TestRunner {
    public static void main(String[] args) {
        // Create a launcher
        Launcher launcher = LauncherFactory.create();
        
        // Create a listener
        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        
        // Register the listener
        launcher.registerTestExecutionListeners(listener);
        
        // Create a request
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(
                        selectClass(TestDataStorageAPI.class),
                        selectClass(TestDigitalRootPersistenceAPI.class),
                        selectClass(TestStorageComputeAPI.class)
                )
                .build();
        
        // Execute tests
        launcher.execute(request);
        
        // Get the summary
        TestExecutionSummary summary = listener.getSummary();
        
        // Print the summary
        summary.printTo(new PrintWriter(System.out));
    }
}

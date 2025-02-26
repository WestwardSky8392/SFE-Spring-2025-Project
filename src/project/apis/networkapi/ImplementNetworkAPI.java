package project.apis.networkapi;

/**
 * Prototype implementation of the NetworkAPI.
 * This class uses a Screen dependency to handle UI interactions.
 */
public class ImplementNetworkAPI { 

    private final Screen screen;

    /**
     * Constructor accepting a Screen dependency.
     * @param screen The Screen implementation to use.
     */
    public ImplementNetworkAPI(Screen screen) {
        this.screen = screen;
    }

    /**
     * Delegates the call to the injected Screen instance.
     * @param askUser User interaction request object.
     * @return The Window instance from the Screen.
     */
    public Window showWindow(AskUser askUser) {
        return screen.showWindow(askUser);
    }

    /**
     * Placeholder method to send information for processing.
     * @return A SendInfo instance (currently null).
     */
    public SendInfo sendToProcess() {
        return null; // Update this if needed
    }
}

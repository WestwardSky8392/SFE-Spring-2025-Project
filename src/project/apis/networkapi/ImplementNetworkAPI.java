package project.apis.networkapi;

import project.apis.networkapi.Screen;
import project.apis.networkapi.AskUser;
import project.apis.networkapi.Window;
import project.apis.networkapi.SendInfo;
import project.apis.networkapi.ValidInfo;


/**
 * Prototype implementation of the NetworkAPI.
 * This is a placeholder implementation that does not perform actual network operations.
 */
public class ImplementNetworkAPI implements Screen {

    /**
     * Displays a window for user interaction.
     * Currently, this returns null as a placeholder.
     * @param askUser User interaction request object.
     * @return A Window instance (currently null).
     */
    @Override
    public Window showWindow(AskUser askUser) {
        return null; // Placeholder
    }

    /**
     * Placeholder method to send information for processing.
     * @return A SendInfo instance (currently null).
     */
    public SendInfo sendToProcess() {
        return null;
    }
}

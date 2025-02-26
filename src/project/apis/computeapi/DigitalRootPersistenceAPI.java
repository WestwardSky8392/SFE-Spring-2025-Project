package project.apis.computeapi;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface DigitalRootPersistenceAPI {
    /**
     * Method to process the number and find its digital root persistence.
     * @param number the number to calculate the digital root persistence for
     * @return a string with the results of the persistence count and the digital root
     */
    String processDigitalRootPersistence(int number);
}

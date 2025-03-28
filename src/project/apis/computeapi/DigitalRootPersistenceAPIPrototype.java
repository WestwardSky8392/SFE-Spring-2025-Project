package project.apis.computeapi;
//import Computation.java
import project.apis.computeapi.DigitalRootPersistenceAPI; 
import project.annotations.ConceptualAPIPrototype;

public class DigitalRootPersistenceAPIPrototype implements DigitalRootPersistenceAPI {

    @Override
    @ConceptualAPIPrototype
    public String processDigitalRootPersistence(int number) {
        // Simulated behavior: returning a static response (for testing)
        //new computation class will be made and will compute the userInput
        return "Number " + number + " has a digital root persistence of 4 and final root 5";
    }
}


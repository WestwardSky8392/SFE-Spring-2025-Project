package NetworkAPI;

import java.lang.annotation.Annotation;

//takes in user input and sends to processAPI
public class NetworkAPIPrototype implements NetworkAPI {
    String userInput = null;
    api.prototype.StorageComputeAPIPrototype dataStorage;
    //will load in with null, then the input will be set to null when program initializes
    //this will change after askUser method is complete
    public NetworkAPIPrototype(String userInput){
        this.userInput = userInput;
    }
    @Override
    public void implementsNewScreen(Screen screen){
        //loads screen to ask user for required information
    }
    @Override
    public void askUser(){
        //will ask user for input PROPER and required data.
    }
    @Override
    public void sendData(String userInput, api.prototype.StorageComputeAPIPrototype dataStorage){
        //will take userInput and send it to the process API.
    }
}

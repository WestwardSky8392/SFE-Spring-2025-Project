package networkapi;

import datastorageapi.StorageComputeAPIPrototype;

public interface NetworkAPI {
    void askUser();
    void sendData(String userInput, StorageComputeAPIPrototype dataStorage);
    void implementsNewScreen(Screen screen);
}

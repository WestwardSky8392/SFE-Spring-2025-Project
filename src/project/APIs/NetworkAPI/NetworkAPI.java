package network_api;

public interface NetworkAPI {
    void askUser();
    void sendData(String userInput, api.prototype.StorageComputeAPIPrototype dataStorage);
    void implementsNewScreen(Screen screen);
}

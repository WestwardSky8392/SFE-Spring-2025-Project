public class api implements DataStorage, RetriveData, StoreData {
    DataStorage dataStorage = null; // <--- file reading??
    RetriveData retriveData = dataStorage.getData(new UserInput());
    // rename these^^^^^^^^^, in correct order though!
//see if user data is valid by default(Integer)
    Delimiter delimiter = new Delimiter(retriveData);

    @Override
    @ConceptualAPIPrototype
    public RetriveData getData(UserInput userInput) {
        return null;
    }
    @Override
    public StoreData SData(UserInput userInput) {
        return null;
    }
}

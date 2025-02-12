public class api implements DataStorage, RetriveData {
    DataStorage dataStorage = null; // <--- file reading??
    RetriveData retriveData = dataStorage.getData(new UserInput());
    // rename these^^^^^^^^^, in correct order though!
//see if user data is valid by default(Integer)
    Delimiter delimiter = new Delimiter(retriveData);

    @Override
    public RetriveData getData(UserInput userInput) {
        return null;
    }
}

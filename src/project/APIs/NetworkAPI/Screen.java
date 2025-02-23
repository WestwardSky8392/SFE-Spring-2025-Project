package NetworkAPI;

public class Screen implements ScreenInstructions {
    Object window;
    public Screen(Object window){
        this.window = window;
    }
    @Override
    public void loadScreen(){
        //will load screen for user to enter information
    }
}

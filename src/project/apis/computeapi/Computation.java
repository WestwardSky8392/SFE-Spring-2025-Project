package project.apis.computeapi;

public class Computation {
    String userInput;

    public Computation(String userInput){
        this.userInput = userInput; // Fix the constructor bug
    }

    public String getUserInput(){
        return userInput;
    }

    public void setUserInput(String newInput){
        // No validation needed; all String values (including null/empty) are allowed
        userInput = newInput;
    }
     
    // Compute Logic (Digital Root Persistence)
    public String computeUserInput(int number){
        // Validate input parameter
        if (number < 0) {
            throw new IllegalArgumentException("Number cannot be negative");
        }

        String numberConvert = String.valueOf(number);
        int total = 0;
        int count = 0;
        while(numberConvert.length()>1){
            total = 0;
            for(int i = 0; i < numberConvert.length(); i++){
                total += Integer.valueOf(String.valueOf(numberConvert.charAt(i)));
            }
            numberConvert = String.valueOf(total);
            count++;
        }
        return String.valueOf(count);
    }
}

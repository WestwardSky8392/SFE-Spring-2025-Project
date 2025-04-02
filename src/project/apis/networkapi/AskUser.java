package project.apis.networkapi;

public class AskUser {
    private String inputPath;
    private String outputPath;
    private char delimiter;

    public AskUser() {
        // Default no-arg constructor
    }

    //reading/writing
    public AskUser(String inputPath, String outputPath, char delimiter) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.delimiter = delimiter;
    }

    //coordinator can read 
    public String getInputPath() { return inputPath; }
    public String getOutputPath() { return outputPath; }
    public char getDelimiter() { return delimiter; }

    int info;
    public AskUser getInfo(){
        return null;
    }
}

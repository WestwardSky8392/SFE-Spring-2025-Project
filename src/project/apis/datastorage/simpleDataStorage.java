public class SimpleDataStorage implements DataStorage {
    @Override
    public int[] readData(String location) throws Exception {
        String[] stringData = location.split(",");
        int[] data = new int[stringData.length];
        for (int i = 0; i < stringData.length; i++) {
            data[i] = Integer.parseInt(stringData[i].trim());
        }
        return data;
    }
    //overide below
    @Override
    public void writeData(String location, int[] results) throws Exception {
        System.out.println("Writing results to location " + location + ": ");
        for (int result : results) {
            System.out.print(result + " ");
        }
        System.out.println();
    }
}

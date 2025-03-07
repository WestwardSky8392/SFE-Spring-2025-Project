package project.apis.datastorage;

public class SimpleDataStorage implements DataStorageAPI {
    @Override
    public int[] readData(String location) throws Exception {
        try {
            String[] stringData = location.split(",");
            int[] data = new int[stringData.length];
            for (int i = 0; i < stringData.length; i++) {
                data[i] = Integer.parseInt(stringData[i].trim());
            }
            return data;
        } catch (Exception e) {
            throw new Exception("Error reading data", e);
        }
    }

    @Override
    public void writeData(String location, int[] result) throws Exception {
        try {
            System.out.print("Writing results to location " + location + ": ");
            for (int num : result) {
                System.out.print(num + " ");
            }
            System.out.println();
        } catch (Exception e) {
            throw new Exception("Error writing data", e);
        }
    }
}

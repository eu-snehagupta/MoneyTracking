import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private final String fileName = "MoneyTracking.txt";

    public void writeAsData(ArrayList<MoneyTracking> list) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (MoneyTracking data : list) {
                bufferedWriter.write(data.toString() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR: " +e);
        }
    }

    public ArrayList<MoneyTracking> readAsData() {

        ArrayList<MoneyTracking> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            String[] data;

            while ((line = bufferedReader.readLine()) != null) {
                data = line.split("\\*\\*");
                MoneyTracking dataValue = new MoneyTracking(data[0], data[1], data[2], Double.parseDouble(data[3]));
                list.add(dataValue);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("ERROR: " +e);
        }
        return list;
    }

    public void writeAsObject(ArrayList<MoneyTracking> list) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream outputFile = new ObjectOutputStream(file);

            outputFile.writeObject(list);
            outputFile.close();
            file.close();
        } catch (IOException e) {
            System.out.println("ERROR: " +e);
        }

    }

    public ArrayList<MoneyTracking> readAsObject() {
        ArrayList<MoneyTracking> list = new ArrayList<>();
        try {
            FileInputStream inputFile = new FileInputStream(fileName);
            ObjectInputStream stream = new ObjectInputStream(inputFile);

            list = (ArrayList<MoneyTracking>) stream.readObject();
            stream.close();
            inputFile.close();
        } catch (IOException e) {
            System.out.println("ERROR: " +e);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Class Not Found" +e);
        }
        return list;
    }

}


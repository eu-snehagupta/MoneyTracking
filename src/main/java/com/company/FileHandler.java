/**
 * File Handler class handles the reads/writes function.
 * The list of item is stored in a file variable 'fileName'.
 *
 * @author Sneha Gupta
 * @version 1.1 (2020.10.05)
 */

package com.company;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private final String fileName = "src/main/resources/MoneyTracking.txt";   //file should be just in one text.

    public void writeAsData(ArrayList<Items> list) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Items data : list) {
                bufferedWriter.write(data.toString() + "\n");
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
    }

    public ArrayList<Items> readAsData() {

        ArrayList<Items> list = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = "";
            String[] data;

            while ((line = bufferedReader.readLine()) != null) {
                data = line.split("\\*\\*");  // using '**' as string to distinguish between the values
                Items dataValue = new Items(data[0], data[1], data[2], Double.parseDouble(data[3]));
                list.add(dataValue);
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return list;
    }
/**
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
 */

}


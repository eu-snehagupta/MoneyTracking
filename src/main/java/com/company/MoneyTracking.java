/**
 * The MoneyTracking class represents money tracking application.
 * This class handles the 4 major features of the application-
 * + Display the items
 * + Add the items
 * + Edit or remove items
 * + Save and quit
 *
 * @author Sneha Gupta
 * @version 1.2 (2020.10.06)
 */

package com.company;

import java.util.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

class Items {
    private String type;
    private String title;
    //public LocalDate dateFormat;
    private String month;
    private double amount;

    public Items(String type, String title, String month, double amount) {
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.type = type;
        this.title = title;
        //this.dateFormat = LocalDate.parse(date, formatter);  //exceptional handling is required for this in futher steps.
        this.month = month;
        this.amount = amount;

    }

    @Override
    public String toString() {
        return "" + type + "**" + title + "**" + month + "**" + amount;
    }

    public String getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMonth() {
        return this.month;
    }

    public double getAmount() {
        return this.amount;
    }
}
/**
 * The MoneyTracking class represents money tracking application.
 * This class handles the 4 major features of the application-
 * + Display the items
 * + Add the items
 * + Edit or remove items
 * + Save and quit
 *
 * @author Sneha Gupta
 * @version 1.2 (2020.10.06)
 */

public class MoneyTracking {

    private static FileHandler fileHandler = new FileHandler();
    private static ArrayList<Items> moneyList = fileHandler.readAsData();

    public void showItems() {  //shorting is done
        System.out.println("Pick an option:");
        System.out.println("(1) Show Items");
        System.out.println("(2) Sort Items");
        System.out.println("->");

        int userChoice = scanUserInteger();

        switch (userChoice) {
            case 1 -> show();   //displays each items of the list.
            case 2 -> sort();  //sort items of the list.
            default -> System.out.println("Invalid userChoice!");
        }

        System.out.println("Enter any key to continue-->");
        scanUserString();
    }
    private void show() {
        System.out.println("Pick an option:");
        System.out.println("(1) All");
        System.out.println("(2) Show Expense");
        System.out.println("(3) Show Income");
        System.out.println("->");

        int userChoice = scanUserInteger();

        switch (userChoice) {
            case 1 -> printEachItem(filterByType("All"));   //displays each items of the list.
            case 2 -> printEachItem(filterByType("Expense"));  //But in case of an empty list,it display empty string.
            case 3 -> printEachItem(filterByType("Income"));   // need to handle that case.
            default -> System.out.println("Invalid userChoice!");
        }
    }
    private void sort() {
        System.out.println("Pick an option:");
        System.out.println("(1) Sort by title");
        System.out.println("(2) Sort by month");
        System.out.println("(3) Sort by amount");
        System.out.println("->");

        int userChoice = scanUserInteger();

        switch (userChoice) {
            case 1 -> printEachItem(sortByTitle());
            case 2 -> printEachItem(sortByMonth());
            case 3 -> printEachItem(sortByAmount());
            default -> System.out.println("Invalid userChoice!");
        }
    }
    private ArrayList<Items> sortByTitle() {

        //ArrayList<Items> readData = moneyList;

        Comparator<Items> compareByTitle = Comparator.comparing(Items::getTitle);
        Collections.sort(moneyList, compareByTitle);

        return moneyList;
    }
    private ArrayList<Items> sortByMonth() {

        //ArrayList<Items> readData = moneyList;

        List<String> validMonths = Arrays.asList("january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december");

        Comparator<Items> compareByMonth = Comparator.comparing(item -> Integer.valueOf(validMonths.indexOf(item.getMonth())));
        Collections.sort(moneyList, compareByMonth);

        //Comparator<Items> compareByMonth = Comparator.comparing(Items::getMonth);
        //Collections.sort(readData, compareByMonth);

        return moneyList;
    }
    private ArrayList<Items> sortByAmount() {

        //ArrayList<Items> readData = moneyList;

        Comparator<Items> compareByAmount = Comparator.comparingDouble(Items::getAmount);
        Collections.sort(moneyList, compareByAmount);

        return moneyList;
    }

//*********************//

    public void addItems() {
        System.out.println("Pick an option:");
        System.out.println("(1) Add Expense");
        System.out.println("(2) Add Income");
        System.out.println("->");

        int userChoice = scanUserInteger();

        String type = null;
        switch (userChoice) {
            case 1 -> type = "Expense";
            case 2 -> type = "Income";
            default -> System.out.println("Invalid userChoice!");
        }

        System.out.println("Enter your Title:");
        String title = scanUserString();

        System.out.println("Enter your month:");
        String month = scanUserMonth();

        System.out.println("Enter your Amount:");
        double amount = scanUserDouble();

        Items obj = new Items(type, title, month, amount);
        moneyList.add(obj);             //holds the values of the item in moneylist, till its saved using savequit feature.
        System.out.println("Item added successfully!");

        System.out.println("Enter any key to continue-->");
        scanUserString();
    }

//*********************//

    public void editItems() {
        System.out.println("Pick an option:");
        System.out.println("(1) Edit item");
        System.out.println("(2) Remove item");
        System.out.println("->");

        int userChoice = scanUserInteger();

        switch (userChoice) {
            case 1 -> edit();
            case 2 -> remove();
            default -> System.out.println("Invalid userChoice!");
        }
    }
    private void edit() {
        //ArrayList<Items> readData = moneyList;

        System.out.println("Pick an option:");
        for (int i = 0; i < moneyList.size(); i++) {
            System.out.println(i + ": " + moneyList.get(i));  //choice from 1,.. option, which list item to edit
        }

        int userChoice = scanUserInteger();

        System.out.println("Enter new type:");  //ask user to re-enter the values for title, date, amount
        String editType = scanUserType();        //should it be an optional feature??
        System.out.println("Enter new title:");  //ask user to re-enter the values for title, date, amount
        String editTitle = scanUserString();        //should it be an optional feature??
        System.out.println("Enter new month:");   //to ask user to enter only those values which he/she wish to edit
        String editDate = scanUserMonth();
        System.out.println("Enter new amount:");
        double editAmount = scanUserDouble();

        for (int i = 0; i < moneyList.size(); i++) {
            if (userChoice == i) {
                moneyList.set(i, new Items(editType, editTitle, editDate, editAmount));
            }
        }
        System.out.println("Item edited successfully!");
    }
    private void remove() {
        //ArrayList<Items> readData = moneyList;

        System.out.println("Pick an option:");  //choice from 1,.. option, which list item to remove
        for (int i = 0; i < moneyList.size(); i++) {
            System.out.println(i + ": " + moneyList.get(i));
        }

        int userChoice = scanUserInteger();

        for (int i = 0; i < moneyList.size(); i++) {
            if (userChoice == i) {
                moneyList.remove(i);
            }
        }
        System.out.println("Item removed successfully!");
    }

//*********************//

    public void saveQuit() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.writeAsData(moneyList);
        System.out.println("Item saved successfully!");
    }

//*********************//

    public ArrayList<Items> filterByType(String filterValue) {
        ArrayList<Items> showData = new ArrayList<>();

        ArrayList<Items> readData = moneyList;

        if (filterValue.equals("All")) {
            return readData;
        } else {
            for (Items objData : readData) {
                if ((objData.getType()).equals(filterValue)) {
                    showData.add(objData);
                }
            }
        }
        return showData;
    }

    private int scanUserInteger() {
        Scanner sc = new Scanner(System.in);
        int inputInteger;
        try {
            inputInteger = sc.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Input. Expecting an integer!");
        }
        return inputInteger;
    }
    private String scanUserString() {
        Scanner sc = new Scanner(System.in);
        String inputString;
        try {
            inputString = sc.nextLine();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Input. Expecting String!");
        }
        return inputString.toLowerCase();
    }
    private String scanUserMonth() {
        String month = scanUserString().toLowerCase();
        String[] validMonths = {"january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december"};
        boolean isValid = false;
        for (String validMonth : validMonths) {
            if (month.equals(validMonth)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            System.out.println("Invalid month. Re-enter the month:");
            scanUserMonth();
        }
        return month;
    }
    private String scanUserType() {
        String type = scanUserString();
        type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        String[] validTypes = {"Expense", "Income"};
        boolean isValid = false;
        for (String validType : validTypes) {
            if (type.equals(validType)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            System.out.println("Invalid type. Re-enter the type:");
            scanUserMonth();
        }
        return type;
    }
    private double scanUserDouble() {
        Scanner sc = new Scanner(System.in);
        double inputDouble;
        try {
            inputDouble = sc.nextDouble();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Input. Expecting a Double!");
        }
        return inputDouble;
    }

    private void printEachItem(ArrayList<Items> itemList) {
        for (Items item : itemList) {
            System.out.println(item);
        }
        if (itemList.size() == 0) {
            System.out.println("No items added.");
        }
    }

//*********************//

}
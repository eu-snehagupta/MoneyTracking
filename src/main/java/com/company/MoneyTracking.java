/**
 * Items class is used to create items for the money tracking application.
 *
 * @author Sneha Gupta
 */

package com.company;

import java.util.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

class Items {
    private String type;
    private String title;
    private String month;
    private double amount;
    //public LocalDate dateFormat;

    public Items(String type, String title, String month, double amount) {
        this.type = type;
        this.title = title;
        this.month = month;
        this.amount = amount;
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //this.dateFormat = LocalDate.parse(date, formatter);
    }

    /**
     * Returns a string representation of this list,
     * containing all elements in their insertion order.
     */
    @Override
    public String toString() {
        return "" + type + "**" + title + "**" + month + "**" + amount;
    }

    /**
     * Get the `type` of the list element.
     * The type is either `Expense` or `Income`
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the `title` of the list element.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Get the `month` of the list element.
     * The month value are months of the year.
     */
    public String getMonth() {
        return this.month;
    }

    /**
     * Get the `amount` of the list element.
     * The amount is of the type- double.
     */
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
 */

public class MoneyTracking {
    private static FileHandler fileHandler = new FileHandler();      // reads the data from the filepath and
    private static ArrayList<Items> moneyList = fileHandler.readAsData();     //store it under moneyList.

    /**
     * Shows the items of the list on the file and also displays new added items
     * Items can displayed by types and can be sorted,
     * This function calls `show` and `sortOrder` function internally,
     * based on user input, which is read using scanner.
     */
    public void showItems() {
        System.out.println("Pick an option:");
        System.out.println("(1) Show Items");
        System.out.println("(2) Sort Items");
        System.out.println("->");

        int userChoice = scanUserInteger();
        switch (userChoice) {
            case 1 -> show();       //displays each items of the list.
            case 2 -> sortOrder();      //sort every items of the list.
            default -> System.out.println("Invalid userChoice!");
        }
        //Added this print satement to solve below problem:
        System.out.println("Enter any key to continue-->");     //It is a bit hard to read the list
        scanUserString();                                     // because the main menu pops up directly
    }                                                       //after and user has to scrolls it up.

    /**
     * Shows the elements of the list by filtering the type,
     * which is based on user input using scanner.
     * This method calls the `filterByType` and `printEachItem` methods.
     */
    private void show() {
        System.out.println("Pick an option:");
        System.out.println("(1) All");
        System.out.println("(2) Show Expense");
        System.out.println("(3) Show Income");
        System.out.println("->");

        int userChoice = scanUserInteger();

        switch (userChoice) {
            case 1 -> printEachItem(filterByType("All"));   //displays each items of the list.
            case 2 -> printEachItem(filterByType("Expense"));  //But in case of an empty list, it displays
            case 3 -> printEachItem(filterByType("Income"));   // a message stating the no item found.
            default -> System.out.println("Invalid userChoice!");
        }
    }

    /**
     * Sorting order is defined based on user choice.
     * Sorting option are Ascending or Descending.
     * `sort` method is called inside the method to sort the items.
     */
    private void sortOrder() {
        System.out.println("Pick a sorting order:");
        System.out.println("(1) Ascending");
        System.out.println("(2) Descending");
        System.out.println("->");

        int userChoice = scanUserInteger();
        String order = "";

        switch (userChoice) {
            case 1 -> {
                order = "Ascending";
                sort(order);                       //sort the items in ascending order
            }
            case 2 -> {
                order = "Descending";
                sort(order);                            //sort the items in descending order
            }
            default -> System.out.println("Invalid userChoice!");
        }
    }

    /**
     * Asks user to sort on basis of title, month and amount
     * and prints the sorted list.
     * ´moneyList´ holds the last sorting stage of the list
     *
     * @param order
     */
    private void sort(String order) {
        System.out.println("Pick an option:");
        System.out.println("(1) Sort by title");
        System.out.println("(2) Sort by month");
        System.out.println("(3) Sort by amount");
        System.out.println("->");

        int userChoice = scanUserInteger();
        switch (userChoice) {
            case 1 -> printEachItem(sortByTitle(order));           //print each item line by line after each sorting
            case 2 -> printEachItem(sortByMonth(order));
            case 3 -> printEachItem(sortByAmount(order));
            default -> System.out.println("Invalid userChoice!");
        }
    }

    /**
     * Sort the items of the list based on its title.
     * title is of String type, which is sorted by defining custom comparator
     *
     * @param order
     * @return ArrayList<Items>
     */
    private ArrayList<Items> sortByTitle(String order) {
        //ArrayList<Items> readData = moneyList;

        //sort the string in alphabetical order, so all title should be either lower or uppercase
        Comparator<Items> compareByTitle = Comparator.comparing(Items::getTitle);
        Comparator<Items> ReverseCompareByTitle = compareByTitle.reversed();

        if (order.equals("Ascending")) {
            Collections.sort(moneyList, compareByTitle);
        } else {
            Collections.sort(moneyList, ReverseCompareByTitle);
        }
        return moneyList;
    }

    /**
     * Sort the items of the list based on its month.
     * month is of String type but holds a valid name of the year's month name,
     * which is stored in an array.
     * Index of the month is used to sort by defining a custom comparator.
     *
     * @param order
     * @return ArrayList<Items>
     */
    private ArrayList<Items> sortByMonth(String order) {
        //ArrayList<Items> readData = moneyList;
        List<String> validMonths = Arrays.asList("january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december");

        // sort the month based on month index
        // month should inserted in same format as listed above.
        Comparator<Items> compareByMonth =
                Comparator.comparing(item -> Integer.valueOf(validMonths.indexOf(item.getMonth())));
        Comparator<Items> ReverseCompareByMonth = compareByMonth.reversed();

        if (order.equals("Ascending")) {
            Collections.sort(moneyList, compareByMonth);
        } else {
            Collections.sort(moneyList, ReverseCompareByMonth);
        }
        return moneyList;
    }

    /**
     * Sort the items of the list based on its amount.
     * amount is of double type, which is sorted by defining a custom comparator
     *
     * @param order
     * @return ArrayList<Items>
     */
    private ArrayList<Items> sortByAmount(String order) {
        //ArrayList<Items> readData = moneyList;
        Comparator<Items> compareByAmount = Comparator.comparingDouble(Items::getAmount);
        Comparator<Items> ReverseCompareByAmount = compareByAmount.reversed();

        if (order.equals("Ascending")) {
            Collections.sort(moneyList, compareByAmount);
        } else {
            Collections.sort(moneyList, ReverseCompareByAmount);
        }
        return moneyList;
    }

    /**
     * This method adds item to the list.
     * Scanner is user to read input of the item type, title, amount and month.
     * Expection is thrown is the user inserts wrong inputs.
     * The item is added to the ´moneyList´ which can be used by other methods.
     */
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
        String month = scanUserMonth();     //user month input is formated.
        System.out.println("Enter your Amount:");
        double amount = scanUserDouble();
        Items obj = new Items(type, title, month, amount);
        moneyList.add(obj);             //holds the values of the item in moneylist, till its saved using savequit feature.
        System.out.println("Item added successfully!");

        System.out.println("Enter any key to continue-->");
        scanUserString();
    }

    /**
     * This method edit or remove the item from the list.
     * Scanner is user to read input of user, if he/she wants to edit or remove.
     * Based on it ´edit()´ or ´remove()´ function is called.
     */
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

    /**
     * Edit method ask user to re-enter the item's type, title, month and amount.
     * User picks the the item to be edited first, before editing.
     * Arraylist ´.set()´ method is used to edit the item.
     */
    private void edit() {
        //ArrayList<Items> readData = moneyList;
        int userChoice = pickAnOptionToEdit();

        System.out.println("Enter new type:");  //ask user to re-enter the values for title, date, amount
        String editType = scanUserType();        //should it be an optional feature??
        System.out.println("Enter new title:");  //to ask user to enter only those values which he/she wish to edit
        String editTitle = scanUserString();
        System.out.println("Enter new month:");
        String editDate = scanUserMonth();
        System.out.println("Enter new amount:");
        double editAmount = scanUserDouble();

        for (int i = 0; i < moneyList.size(); i++) {     //item value is edited on the moneylist, which gets
            if (userChoice == i) {                          // written in the file after save method is called.
                moneyList.set(i, new Items(editType, editTitle, editDate, editAmount));
            }
        }
        System.out.println("Item edited successfully!");
    }

    /**
     * Remove method ask remove the item from the list.
     * User picks the the item to be removed first from the list, which is displayed first.
     * Arraylist ´.remove()´ method is used to remove the item.
     */
    private void remove() {
        //ArrayList<Items> readData = moneyList;
        int userChoice = pickAnOptionToEdit();

        for (int i = 0; i < moneyList.size(); i++) {
            if (userChoice == i) {              //item value is edited on the moneylist, which gets
                moneyList.remove(i);                // written in the file after save method is called.
            }
        }
        System.out.println("Item removed successfully!");
    }

    /**
     * This method displays the list with the indexes,
     * user inserts the index of the item to be edited/removed.
     * If the input is outside the index of the arraylist, user is asked to repick.
     */
    private int pickAnOptionToEdit() {
        System.out.println("Pick an option:");
        for (int i = 0; i < moneyList.size(); i++) {
            System.out.println(i + ": " + moneyList.get(i));  //choice from 0,1,.. option, which list item to edit
        }
        int userChoice = scanUserInteger();
        if (userChoice < 0 || userChoice >= moneyList.size()) {
            System.out.println("Invalid option. Repick!");
            pickAnOptionToEdit();           //recursion call, until the input value is correct
        }
        return userChoice;
    }

    /**
     * This method saves the list to the file before quiting the application.
     * All new added/edited/removed items are reflected to the file after this method.
     */
    public void saveQuit() {
        FileHandler fileHandler = new FileHandler();
        fileHandler.writeAsData(moneyList);         // items are writen to the file only in this method
        System.out.println("Item saved successfully!");
    }

    /**
     * This method filter the list by its the type.
     * If All is passed as a parameter, then the method return the entire list,
     * without filtering.
     *
     * @param filterValue
     * @return ArrayList<Items>
     */
    private ArrayList<Items> filterByType(String filterValue) {
        ArrayList<Items> showData = new ArrayList<>();
        ArrayList<Items> readData = moneyList;

        if (filterValue.equals("All")) {            // returns the ´moneyList´ whole list
            return readData;
        } else {
            for (Items objData : readData) {
                if ((objData.getType()).equals(filterValue)) {
                    showData.add(objData);    // if no item is found, then returns an empty list
                }
            }
        }
        return showData;
    }

    /**
     * This method scan Integer value.
     * It thrown an IllegalArgumentException, if the input is not an integer.
     */
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

    /**
     * This method scan String value.
     * It thrown an IllegalArgumentException, if the input is not an string.
     */
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

    /**
     * This method scan String value of the month.
     * If the value entered is not a valid month name, it calls itself until
     * the right value is passed. It formats the input scanned to lower case.
     */
    private String scanUserMonth() {
        String month = scanUserString().toLowerCase();
        String[] validMonths = {"january", "february", "march", "april", "may", "june",
                "july", "august", "september", "october", "november", "december"};
        boolean isValid = false;
        for (String validMonth : validMonths) {     // only the correct month name is accepted
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

    /**
     * This method scan String value of the type.
     * If the value entered is not a valid type, it calls itself until
     * the right value is passed. It formats the input scanned to
     * first string as uppercase and rest as lower case.
     */
    private String scanUserType() {
        String type = scanUserString();
        type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
        String[] validTypes = {"Expense", "Income"};            // user input is being formatted
        boolean isValid = false;
        for (String validType : validTypes) {
            if (type.equals(validType)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            System.out.println("Invalid type. Re-enter the type:");
            scanUserType();
        }
        return type;
    }

    /**
     * This method scan Double value.
     * It thrown an IllegalArgumentException, if the input is not an double.
     */
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

    /**
     * This method prints each items of the list line by line.
     * If the file is empty/ list is empty, it prints a message
     * - No items available.
     */
    private void printEachItem(ArrayList<Items> itemList) {
        for (Items item : itemList) {
            System.out.println(item);
        }
        if (itemList.size() == 0) {                 //handles an empty list or an empty file.
            System.out.println("No items available.");
        }
    }
}
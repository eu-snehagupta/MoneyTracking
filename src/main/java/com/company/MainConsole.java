/**
 * The MainConsole class defines the main display function of the application.
 * With the help of Scanner, the user inputs their choices and the switch case is run.
 *
 * @author Sneha Gupta
 * @version 1.1 (2020.10.06)
 */
package com.company;

import java.util.Scanner;

public class MainConsole {
    /**
     * The display method displays the money tracking features
     * and ask user to insert the choice of feature.
     * Based on the choice, methods of money tracking is called.
     */
    public void display() {
        int userChoice;

        System.out.println("***********************************");
        System.out.println("Welcome to TrackMoney");
        System.out.println("You have currently (-)X kr on your account.");
        System.out.println("Pick an option:");
        System.out.println("(1) Show items (All/Expense(s)/Income(s))");
        System.out.println("(2) Add New Expense/Income");
        System.out.println("(3) Edit item (edit, remove)");
        System.out.println("(4) Save and Quit");
        System.out.println("->");
        Scanner sc = new Scanner(System.in);

        try {
            userChoice = sc.nextInt();
            select(userChoice);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid Input. Expecting an integer!");
        }
    }

    /**
     * The select method takes the user  input and
     * selects the methods of the money tracking.
     * Switch case is used, The display method is called after every method
     * until save and quit is selected by the user.
     *
     * @param userChoice
     */
    private void select(int userChoice) {  //selects the feature based on user inputs.
        MoneyTracking obj = new MoneyTracking();

        switch (userChoice) {
            case 1 -> {
                obj.showItems();
                display();  //recursion until user wants to save and quit.
                break;
            }
            case 2 -> {
                obj.addItems();
                display();
                break;
            }
            case 3 -> {
                obj.editItems();
                display();
                break;
            }
            case 4 -> {
                obj.saveQuit();
                System.out.println("***********************************");
                break;
            }
            default -> {
                System.out.println("Invalid choice!");
                display();
                break;
            }
        }
    }
}

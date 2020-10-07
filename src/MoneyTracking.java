import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MoneyTracking implements Serializable {

    private String type;
    private String title;
    //private LocalDate dateFormat;
    private String dateFormat;
    private double amount;

    public static ArrayList<MoneyTracking> moneyList = new ArrayList<>();
    String whichType = "";

    public MoneyTracking(String type, String title, String date, double amount) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.type = type;
        this.title = title;
        //this.dateFormat = LocalDate.parse(date, formatter);
        this.dateFormat = date;
        this.amount = amount;

    }

    public MoneyTracking() {
    }

    @Override
    public String toString() {

        return "" + type + "**" + title + "**" + dateFormat + "**" + amount;

    }

    public void showItems() {
        int choice;

        System.out.println("Pick an option:");
        System.out.println("(1) All");
        System.out.println("(2) Show Expense");
        System.out.println("(3) Show Income");

        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                System.out.println(filterByType("All"));
                break;
            case 2:
                System.out.println(filterByType("Expense"));
                break;
            case 3:
                System.out.println(filterByType("Income"));
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    public void addItems() {
        int choice;

        System.out.println("Pick an option:");
        System.out.println("(1) Add Expense");
        System.out.println("(2) Add Income");

        Scanner sc1 = new Scanner(System.in);
        choice = sc1.nextInt();

        switch (choice) {
            case 1:
                type = "Expense";
                break;
            case 2:
                type = "Income";
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }

        System.out.println("Enter your Title:");
        Scanner sc2 = new Scanner(System.in);
        String addTitle = sc2.nextLine();
        title = addTitle;

        System.out.println("Enter your Date(yyyy-MM-dd):");
        Scanner sc3 = new Scanner(System.in);
        String addDate = sc3.nextLine();
        String date = addDate;

        System.out.println("Enter your Amount:");
        Scanner sc4 = new Scanner(System.in);
        double addAmount = sc4.nextDouble();
        amount = addAmount;

        MoneyTracking obj = new MoneyTracking(type, title, date, amount);
        moneyList.add(obj);
        System.out.println("Item added successfully!");
    }


    public void editItems() {

        int choice;

        System.out.println("Pick an option:");
        System.out.println("(1) Edit item");
        System.out.println("(2) Remove item");

        Scanner sc1 = new Scanner(System.in);
        choice = sc1.nextInt();

        switch (choice) {
            case 1:
                edit();
                break;
            case 2:
                remove();
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
    }

    public void saveQuit() {

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeAsData(moneyList);
        System.out.println("Item saved successfully!");

    }

    public void edit() {
        ArrayList<MoneyTracking> showData = new ArrayList<>();
        int choice;

        System.out.println("Pick an option:");
        System.out.println("(1) Edit Expense");
        System.out.println("(2) Edit Income");

        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                editValue(filterByType("Expense"), "Expense");
                break;
            case 2:
                editValue(filterByType("Income"), "Income");
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        System.out.println("Item edited successfully!");
    }

    public void remove() {
        int choice;

        System.out.println("Pick an option:");
        System.out.println("(1) Remove Expense");
        System.out.println("(2) Remove Income");

        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();

        switch (choice) {
            case 1:
                removeValue(filterByType("Expense"));
                break;
            case 2:
                removeValue(filterByType("Income"));
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        System.out.println("Item removed successfully!");
    }

    public ArrayList<MoneyTracking> filterByType(String filterValue) {
        ArrayList<MoneyTracking> showData = new ArrayList<>();

        FileHandler fileHandler = new FileHandler();
        ArrayList<MoneyTracking> readData = fileHandler.readAsData();

        String filterValue_ = filterValue;
        if (filterValue_.equals("All")) {
            return readData;
        } else {
            for (MoneyTracking objData : readData) {
                if ((objData.type).equals(filterValue_)) {
                    showData.add(objData);
                }
            }
        }
        return showData;
    }

    public void editValue(ArrayList<MoneyTracking> showData, String editType) {
        int choice;
        System.out.println("Pick an option:");
        for (int i = 0; i < showData.size(); i++) {
            System.out.println(i + ": " + showData.get(i));
        }
        Scanner sc1 = new Scanner(System.in);
        choice = sc1.nextInt();

        System.out.println("Enter your Title:");
        Scanner sc2 = new Scanner(System.in);
        String editTitle = sc2.nextLine();
        System.out.println("Enter your Date(yyyy-MM-dd):");
        Scanner sc3 = new Scanner(System.in);
        String editDate = sc3.nextLine();
        System.out.println("Enter your Amount:");
        Scanner sc4 = new Scanner(System.in);
        double editAmount = sc4.nextDouble();

        for (int i = 0; i < showData.size(); i++) {
            if (choice == i) {
                showData.set(i, new MoneyTracking(editType, editTitle, editDate, editAmount));
            }
        }
    }

    public void removeValue(ArrayList<MoneyTracking> showData) {
        int choice;
        System.out.println("Pick an option:");
        for (int i = 0; i < showData.size(); i++) {
            System.out.println(i + ": " + showData.get(i));
        }
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();

        for (int i = 0; i < showData.size(); i++) {
            if (choice == i) {
                showData.remove(i);
            }
        }
    }
}
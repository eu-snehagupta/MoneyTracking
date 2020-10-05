import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MoneyTracking implements Serializable {

    private String type;
    private String title;
    private LocalDate dateFormat;
    private double amount;
    private ArrayList<MoneyTracking> moneyList;

    public MoneyTracking(String type, String title, String date, double amount){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.type = type;
        this.title = title;
        this.dateFormat = LocalDate.parse(date, formatter);
        this.amount = amount;
        this.moneyList = new ArrayList<>();
    }
    public MoneyTracking(){}

    @Override
    public String toString() {

        return "" + type + "**" + title + "**" + dateFormat + "**" + amount ;

    }
/*
    public static void main(String[] args) throws IOException
    {
        MoneyTracking obj1 = new MoneyTracking("Income" , "Salary", "2020-09-25", 3000.00);
        MoneyTracking obj2 = new MoneyTracking("Expense" , "Rent", "2020-09-30", 1000.00);
        MoneyTracking obj3 = new MoneyTracking("Expense" , "Food", "2020-10-01", 1000.00);

        ArrayList<MoneyTracking> moneyList = new ArrayList<>();
        moneyList.add(obj1);
        moneyList.add(obj2);
        moneyList.add(obj3);

        FileHandler fileHandler = new FileHandler();

        // write & read as data
        fileHandler.writeAsData(moneyList);
        ArrayList<MoneyTracking> readData = fileHandler.readAsData();
        System.out.println(readData);
        System.out.println("----------------------");

        // write and read as object
         fileHandler.writeAsObject(moneyList);
        ArrayList<MoneyTracking> checkObject = fileHandler.readAsObject();
        System.out.println(checkObject);
        System.out.println(checkObject.get(0));

    } */

    public void showItems(){
        FileHandler fileHandler = new FileHandler();
        ArrayList<MoneyTracking> readData = fileHandler.readAsData();
        System.out.println(readData);
        System.out.println("----------------------");
    }

    public void addItems(){
        System.out.println("Is it an Expense? Y/N");
        Scanner sc1= new Scanner(System.in);
        String addType = sc1.nextLine();
        if(addType.equals("Y")){ type = "Expense"; }
        else{ type = "Income"; }

        System.out.println("Enter your Title:");
        Scanner sc2= new Scanner(System.in);
        String addTitle = sc2.nextLine();
        title = addTitle;

        System.out.println("Enter your Date(yyyy-MM-dd):");
        Scanner sc3= new Scanner(System.in);
        String addDate = sc3.nextLine();
        String date = addDate;

        System.out.println("Enter your Amount:");
        Scanner sc4= new Scanner(System.in);
        double addAmount = sc4.nextDouble();
        amount = addAmount;

        MoneyTracking obj = new MoneyTracking(type, title, date, amount);
        moneyList.add(obj);

        FileHandler fileHandler = new FileHandler();
        fileHandler.writeAsData(moneyList);

    }


    public void editItems(){
        System.out.println("Hi3");
    }
    public void saveQuit(){


        // write and read as object
        // fileHandler.writeAsObject(moneyList);
        // ArrayList<MoneyTracking> checkObject = fileHandler.readAsObject();
        // System.out.println(checkObject);
        // System.out.println(checkObject.get(0));

    }
}

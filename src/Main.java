import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static final String fileName = "MoneyTracking.txt";

    public static void main(String[] args) throws IOException {
        display();
        ReadWrite obj = new ReadWrite(fileName);
        obj.read();
        obj.write("abcd");
    }
    public static void display(){
        double totalAmount = 100.00;
        System.out.println("Welcome to TrackMoney");
        System.out.println("You have currently " +totalAmount +"kr on your account.");
        System.out.println("Pick an option:");
        System.out.println("(1) Show items (All/Expense(s)/Income(s))");
        System.out.println("(2) Add New Expense/Income");
        System.out.println("(3) Edit item (edit, remove)");
        System.out.println("(4) Save and Quit");
        Scanner sc= new Scanner(System.in);
        int choice= sc.nextInt();
        System.out.println(choice);
    }
}



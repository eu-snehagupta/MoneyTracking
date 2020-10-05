import java.util.Scanner;
public class Main {

    //private static final String fileName = "MoneyTracking.txt";

    public static void main(String[] args) {
        display();
    }

    public static void display(){
        int choice;

        System.out.println("***********************************");
        System.out.println("Welcome to TrackMoney");
        System.out.println("You have currently (-)X kr on your account.");
        System.out.println("Pick an option:");
        System.out.println("(1) Show items (All/Expense(s)/Income(s))");
        System.out.println("(2) Add New Expense/Income");
        System.out.println("(3) Edit item (edit, remove)");
        System.out.println("(4) Save and Quit");
        System.out.println(" ");
        Scanner sc= new Scanner(System.in);

        choice = sc.nextInt();

        MoneyTracking obj = new MoneyTracking("Income","Food","2000-10-22",150.00);

        while(choice != 4){
            if (choice == 1){
                obj.showItems();
            }
            else if (choice == 2){
                obj.addItems();
            }
            else if (choice == 3){
                obj.editItems();
            } else {
                System.out.println("Invalid Choice!");
            }
            display();
            break;
        }
        if (choice == 4){
            obj.saveQuit();
        }
    }
}



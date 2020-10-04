import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadWrite {
    private String fileName;
    public ReadWrite(String fileName){
        this.fileName = fileName;
    }

    public void read() {
        try {
            File input = new File(fileName);
            Scanner input_ = new Scanner(input);
            while(input_.hasNextLine()){
                String data = input_.nextLine();
                System.out.println(data);
            }
            input_.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public void write(String data) throws IOException {
        try {
            FileWriter output = new FileWriter(fileName);
            output.write(data);
            output.close();
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}

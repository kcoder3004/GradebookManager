package src;
import java.util.Scanner;
public class GradebookApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        GradebookManager manager = new GradebookManager();
        System.out.println("==== Gradebook Manager ====");
        System.out.println("1. Add Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View All Students");
        System.out.println("4. View Student Details");
        System.out.println("5. Search Student by ID");
        System.out.println("6. Load Data from File");
        System.out.println("7. Save Data to File");
        System.out.println("8. Exit");
        System.out.print("Enter choice: ");

    }
    
}

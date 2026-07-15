package src;

import java.util.Scanner;

public class GradebookApp {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        GradebookManager manager = new GradebookManager();

        boolean running = true;

        while (running) {

            System.out.println();
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

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter student ID: ");
                    int id = input.nextInt();
                    input.nextLine();

                    System.out.print("Enter student name: ");
                    String name = input.nextLine();

                    GradebookStudent student = new GradebookStudent(id, name);

                    if (manager.addStudent(student)) {
                        System.out.println("Student added successfully.");
                    } else {
                        System.out.println("Duplicate student ID.");
                    }

                    break;

                case 2:

                    System.out.print("Enter student ID: ");
                    int studentId = input.nextInt();
                    input.nextLine();

                    GradebookStudent found = manager.findById(studentId);

                    if (found == null) {
                        System.out.println("Student not found.");
                        break;
                    }

                    System.out.print("Enter assignment title: ");
                    String title = input.nextLine();

                    System.out.print("Enter score: ");
                    double score = input.nextDouble();
                    input.nextLine();

                    GradeItem grade = new GradeItem(title, score);

                    found.addGrade(grade);

                    System.out.println("Grade added.");

                    break;

                case 3:

                    manager.viewAllStudents();

                    break;

                case 4:

                    System.out.print("Enter student ID: ");
                    int detailId = input.nextInt();
                    input.nextLine();

                    manager.viewStudentDetails(detailId);

                    break;

                case 5:

                    System.out.print("Enter student ID: ");
                    int searchId = input.nextInt();
                    input.nextLine();

                    GradebookStudent result = manager.findById(searchId);

                    if (result == null) {
                        System.out.println("Student not found.");
                    } else {
                        System.out.println(result);
                    }

                    break;

                case 6:

                    manager.loadFromFile("data/sample_data.txt");
                    System.out.println("Data loaded.");

                    break;

                case 7:

                    manager.saveToFile("data/output.txt");
                    System.out.println("Data saved.");

                    break;

                case 8:

                    running = false;
                    System.out.println("Goodbye!");

                    break;

                default:

                    System.out.println("Invalid choice.");
            }
        }

        input.close();
    }
}
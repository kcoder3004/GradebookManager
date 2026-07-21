package src;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GradebookApp {

    public static void main(String[] args) {

        // Setup input scanner and gradebook logic helper
        Scanner input = new Scanner(System.in);
        GradebookManager manager = new GradebookManager();
        boolean running = true;

        // Main program loop - keeps menu open until user exits
        while (running) {
            try {
                // Display options
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
                input.nextLine(); // clear leftover newline character from buffer

                switch (choice) {
                    case 1:
                        // Add new student
                        System.out.print("Enter student ID: ");
                        int id = input.nextInt();
                        input.nextLine();

                        System.out.print("Enter student name: ");
                        String name = input.nextLine();

                        GradebookStudent student = new GradebookStudent(id, name);

                        // Check if ID is already taken
                        if (manager.addStudent(student)) {
                            System.out.println("Student added successfully.");
                        } else {
                            System.out.println("Duplicate student ID.");
                        }
                        break;

                    case 2:
                        // Add a grade entry to an existing student
                        System.out.print("Enter student ID: ");
                        int studentId = input.nextInt();
                        input.nextLine();

                        GradebookStudent found = manager.findById(studentId);

                        // Make sure student exists before asking for grade details
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
                        // Show all student records
                        manager.viewAllStudents();
                        break;

                    case 4:
                        // View detailed grades for one student
                        System.out.print("Enter student ID: ");
                        int detailId = input.nextInt();
                        input.nextLine();

                        manager.viewStudentDetails(detailId);
                        break;

                    case 5:
                        // Quick lookup by ID
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
                        // Read initial data file
                        if (manager.loadFromFile("GradebookManager/data/sample_data.txt")) {
                            System.out.println("Data loaded successfully.");
                        }
                        break;

                    case 7:
                        // Save current data out to file
                        manager.saveToFile("GradebookManager/data/output.txt");
                        System.out.println("Data saved.");
                        break;

                    case 8:
                        // Stop the main loop
                        running = false;
                        System.out.println("Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Please choose 1-8.");
                }
            } catch (InputMismatchException e) {
                // Catch non-numeric inputs (letters when expecting numbers)
                System.out.println("Error: Invalid input type. Please enter numbers where requested.");
                input.nextLine(); // clear bad input so loop doesn't break
            } catch (IllegalArgumentException e) {
                // Catch custom validation errors from models/manager
                System.out.println("Validation Error: " + e.getMessage());
            }
        }
        
        // Clean up resources
        input.close();
    }
}
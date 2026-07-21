package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GradebookManager {

    private ArrayList<GradebookStudent> students;

    // Constructors
    public GradebookManager() {
        students = new ArrayList<>();
    }

    public GradebookManager(ArrayList<GradebookStudent> students) {
        this.students = students;
    }

    // Add student, but reject duplicates with the same ID
    public boolean addStudent(GradebookStudent student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == student.getId()) {
                return false; // ID already exists
            }
        }
        students.add(student);
        return true;
    }

    // Helper to search list by student ID
    public GradebookStudent findById(int id) {
        for (int i = 0; i < students.size(); i++) {
            GradebookStudent student = students.get(i);
            if (student.getId() == id) {
                return student;
            }
        }
        return null; // Not found
    }

    // Print all students on high level
    public void viewAllStudents() {
        if (students.size() == 0) {
            System.out.println("No students in gradebook.");
            return;
        }
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }

    // Print specific student and all their grades
    public void viewStudentDetails(int id) {
        GradebookStudent student = findById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        student.displayDetails();
    }

    // Load data file into memory
    public boolean loadFromFile(String filename) {
        File file = new File(filename);

        // Path fallback: if nested folder fails, try checking project root directly
        if (!file.exists() && filename.contains("/")) {
            String fallbackName = filename.substring(filename.lastIndexOf("/") + 1);
            File fallbackFile = new File(fallbackName);
            if (fallbackFile.exists()) {
                file = fallbackFile;
            }
        }

        // Verify file exists first so we don't accidentally wipe existing list on bad path
        if (!file.exists()) {
            System.out.println("Error: File not found. Please ensure your data file exists at either:");
            System.out.println("  -> " + filename);
            if (filename.contains("/")) {
                System.out.println("  -> " + filename.substring(filename.lastIndexOf("/") + 1));
            }
            return false;
        }

        // Wipe current data so we load fresh from file
        students.clear();

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                // Skip blank lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                
                try {
                    // Parse student record lines: STUDENT, id, name
                    if (parts[0].equals("STUDENT") && parts.length >= 3) {
                        int id = Integer.parseInt(parts[1].trim());
                        String name = parts[2].trim();

                        GradebookStudent student = new GradebookStudent(id, name);
                        addStudent(student);
                        
                    // Parse grade entry lines: GRADE, studentId, title, score
                    } else if (parts[0].equals("GRADE") && parts.length >= 4) {
                        int studentId = Integer.parseInt(parts[1].trim());
                        String title = parts[2].trim();
                        double score = Double.parseDouble(parts[3].trim());

                        GradebookStudent student = findById(studentId);
                        if (student != null) {
                            GradeItem grade = new GradeItem(title, score);
                            student.addGrade(grade);
                        }
                    }
                } catch (IllegalArgumentException e) {
                    // Skip bad rows without crashing whole import
                    System.out.println("Skipping malformed data line: " + line);
                }
            }
            fileScanner.close();
            return true;

        } catch (FileNotFoundException e) {
            System.out.println("Could not open file: " + file.getPath());
            return false;
        }
    }

    // Save all current memory records out to CSV text file
    public void saveToFile(String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);

            for (int i = 0; i < students.size(); i++) {
                GradebookStudent student = students.get(i);

                // Output student row
                writer.println("STUDENT," + student.getId() + "," + student.getName());
                ArrayList<GradeItem> grades = student.getGrades();

                // Output associated grade rows right under
                for (int j = 0; j < grades.size(); j++) {
                    GradeItem grade = grades.get(j);
                    writer.println("GRADE," + student.getId() + "," + grade.getTitle() + "," + grade.getScore());
                }
            }
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("Could not save file.");
        }
    }
}
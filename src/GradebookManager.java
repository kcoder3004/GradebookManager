package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class GradebookManager {

    private ArrayList<GradebookStudent> students;

    public GradebookManager() {
        students = new ArrayList<>();
    }

    public GradebookManager(ArrayList<GradebookStudent> students) {
        this.students = students;
    }

    public boolean addStudent(GradebookStudent student) {

        for (int i = 0; i < students.size(); i++) {

            if (students.get(i).getId() == student.getId()) {
                return false;
            }
        }

        students.add(student);
        return true;
    }

    public GradebookStudent findById(int id) {

        for (int i = 0; i < students.size(); i++) {

            GradebookStudent student = students.get(i);

            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    public void viewAllStudents() {

        if (students.size() == 0) {
            System.out.println("No students in gradebook.");
            return;
        }

        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i));
        }
    }

    public void viewStudentDetails(int id) {

        GradebookStudent student = findById(id);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.displayDetails();
    }

    public void loadFromFile(String filename) {

        students.clear();

        try {

            Scanner fileScanner = new Scanner(new File(filename));

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts[0].equals("STUDENT")) {

                    int id = Integer.parseInt(parts[1]);
                    String name = parts[2];

                    GradebookStudent student =
                            new GradebookStudent(id, name);

                    addStudent(student);
                }

                else if (parts[0].equals("GRADE")) {

                    int studentId = Integer.parseInt(parts[1]);
                    String title = parts[2];
                    double score = Double.parseDouble(parts[3]);

                    GradebookStudent student = findById(studentId);

                    if (student != null) {

                        GradeItem grade =
                                new GradeItem(title, score);

                        student.addGrade(grade);
                    }
                }
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");
        }
    }

    public void saveToFile(String filename) {

        try {

            PrintWriter writer = new PrintWriter(filename);

            for (int i = 0; i < students.size(); i++) {

                GradebookStudent student = students.get(i);

                writer.println(
                        "STUDENT,"
                        + student.getId()
                        + ","
                        + student.getName()
                );

                ArrayList<GradeItem> grades = student.getGrades();

                for (int j = 0; j < grades.size(); j++) {

                    GradeItem grade = grades.get(j);

                    writer.println(
                            "GRADE,"
                            + student.getId()
                            + ","
                            + grade.getTitle()
                            + ","
                            + grade.getScore()
                    );
                }
            }

            writer.close();

        } catch (FileNotFoundException e) {

            System.out.println("Could not save file.");
        }
    }
}
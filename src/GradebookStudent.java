package src;

import java.util.ArrayList;

public class GradebookStudent {
    private int id; 
    private String name; 
    private ArrayList<GradeItem> grades;

    public GradebookStudent(int id, String name, ArrayList<GradeItem> grades) {
        if (id < 0) {
            throw new IllegalArgumentException("Id must be greater than 0");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        
        this.id = id;
        this.name = name;

        this.grades = (grades != null) ? grades : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name; // Fixed reverse assignment
    }

    public void addGrade(GradeItem grade) {
        if (grade != null) {
            this.grades.add(grade);
        }
    }

    public ArrayList<GradeItem> getGrades() {
        return grades;
    }


    public double getAverageScore() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0; 
        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i).getScore();
        }
        
        return sum / grades.size();
    }

    public void displayDetails() {
    System.out.println("ID: " + id);
    System.out.println("Name: " + name);

    System.out.println("Grades:");

    if (grades.size() == 0) {
        System.out.println("No grades yet");
    } else {
        for (int i = 0; i < grades.size(); i++) {
            System.out.println(grades.get(i));
        }
    }

    System.out.println("Average: " + averageGrade());
}
}
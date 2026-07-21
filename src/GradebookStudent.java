package src;

import java.util.ArrayList;

public class GradebookStudent {

    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    // Initialize student record
    public GradebookStudent(int id, String name) {

        // Quick checks so we don't end up with bad data
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.id = id;
        this.name = name;
        this.grades = new ArrayList<>(); // start with empty grades list
    }

    // Basic getters
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

        this.name = name;
    }

    // Add a new assignment grade (ignore nulls just in case)
    public void addGrade(GradeItem grade) {

        if (grade != null) {
            grades.add(grade);
        }

    }

    // Return a copy of the list so external code can't mess with internal list directly
    public ArrayList<GradeItem> getGrades() {

        return new ArrayList<>(grades);

    }

    // Calculate total average across all added grades
    public double averageGrade() {

        // Avoid divide-by-zero if no grades added yet
        if (grades.size() == 0) {
            return 0.0;
        }

        double sum = 0.0;

        for (int i = 0; i < grades.size(); i++) {

            sum += grades.get(i).getScore();

        }

        return sum / grades.size();

    }

    // Print out full student breakdown
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

    // One-line summary format for lists/search results
    @Override
    public String toString() {

        return id + " - " + name + " - Average: " + averageGrade();

    }

}
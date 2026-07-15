package src;

import java.util.ArrayList;

public class GradebookStudent {

    private int id;
    private String name;
    private ArrayList<GradeItem> grades;

    public GradebookStudent(int id, String name) {

        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        this.id = id;
        this.name = name;
        grades = new ArrayList<>();
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

        this.name = name;
    }

    public void addGrade(GradeItem grade) {

        if (grade != null) {
            grades.add(grade);
        }

    }

    public ArrayList<GradeItem> getGrades() {

        return new ArrayList<>(grades);

    }

    public double averageGrade() {

        if (grades.size() == 0) {
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


    @Override
    public String toString() {

        return id + " - " + name + " - Average: " + averageGrade();

    }

}
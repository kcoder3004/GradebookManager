package src;

public class GradeItem {

    private String title;
    private double score;

    // Main constructor with input checks
    public GradeItem(String title, double score) {

        // Make sure assignment name isn't blank
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        // Standard 0-100 percentage range validation
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }

        this.title = title;
        this.score = score;
    }

    // Getters
    public String getTitle() {
        return title;
    }
    
    public double getScore() {
        return score;
    }

    // Setters - keep validation rules same as constructor
    public void setTitle(String title) {

        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }

        this.title = title;
    }

    public void setScore(double score) {

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }

        this.score = score;
    }

    // Formatting output as "Homework 1 : 95.0" for printing
    @Override
    public String toString() {
        return title + " : " + score;
    }

}
package src;
public class GradeItem {
    private String title;
    private double score;
    public GradeItem(String title, double score){
        this.title = title;
        this.score = score;
        if (title == null || title.trim().isEmpty()) {
        throw new IllegalArgumentException("Title cannot be empty.");
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score is out of bounds");
        }
    }
    public String getTitle(){
        return title;
    }

    public double getScore(){
        return score;
    }

    public void setTitle(String title){
        title = this.title;
    }

    public void setScore(double score){
        score = this.score;
    }
    @Override
    public String toString() {
    return title + " : " + score;
}

}

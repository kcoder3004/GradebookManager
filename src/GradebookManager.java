package src;
import java.util.ArrayList;

public class GradebookManager {
    private ArrayList<GradebookStudent> students;
    public GradebookManager(ArrayList<GradebookStudent> students){
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
        GradebookStudent currentStudent = students.get(i);
        
        if (currentStudent.getId() == id) {
            return currentStudent; 
        }
    }
    
    return null;
}

public void viewAllStudents(){
     for (int i = 0; i < students.size(); i++){
        GradebookStudent viewer = students.get(i);
        System.out.println(viewer.getId());
        System.out.println(viewer.getName());
        System.out.println(viewer.getAverageScore());
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

}

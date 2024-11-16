import java.util.ArrayList;
import java.util.List;

public class Sections {
    private String sectionCode;
    private List<Student> enrolledStudents;

    public Sections(String sectionCode) {
        this.sectionCode = sectionCode;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getSectionCode() {
        return sectionCode;
    }

//    public String getInstructor() {
//        return instructor;
//    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }
}

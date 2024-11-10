import java.util.ArrayList;
import java.util.List;

public class Sections {
    private String sectionCode;
    private String instructor;
    private List<Student> enrolledStudents;

    public Sections(String sectionCode, String instructor) {
        this.sectionCode = sectionCode;
        this.instructor = instructor;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void addStudent(Student student) {
        enrolledStudents.add(student);
    }
}

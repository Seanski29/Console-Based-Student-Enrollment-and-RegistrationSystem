import java.util.ArrayList;
import java.util.List;

public class Sections {
    private int sectionId;
    private String sectionCode;
    private int maxSlots;
    private List<Student> enrolledStudents;

    public Sections(int sectionId, String sectionCode, int maxSlots) {
        this.sectionId = sectionId;
        this.sectionCode = sectionCode;
        this.maxSlots = maxSlots;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public int getMaxSlots() {
        return maxSlots;
    }

    public void addStudent(Student student) {
        if (enrolledStudents.size() < maxSlots) {
            enrolledStudents.add(student);
            System.out.println("Student added to section: " + sectionCode);
        } else {
            System.out.println("No more slots available in " + sectionCode);
        }
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}

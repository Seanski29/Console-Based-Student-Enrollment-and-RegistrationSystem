public class Student extends User {
    private String srCode;
    private boolean isRegular;
    private Sections section;  // Store current section
    private int studentId;

    public Student(String username, String password, String srCode, boolean isRegular, int studentId) {
        super(username, password);
        this.srCode = srCode;
        this.isRegular = isRegular;
        this.studentId = studentId;
    }

    public String getSrCode() {
        return srCode;
    }

    public boolean isRegular() {
        return isRegular;
    }

    // Set the section for the student
    public void setSection(Sections section) {
        this.section = section;
    }

    public int studentId() {
        return studentId;
    }
    public int getStudentId() {
        return studentId;
    }

    public void setId(int studentId) {
        this.studentId = studentId;
    }

    // View student's details
    public void viewStudentDetails() {
        System.out.println("Name: " + getUsername());
        System.out.println("SR Code: " + srCode);
        System.out.println("Is Regular: " + isRegular);

        // Check if section is null before calling getSectionCode
        if (section != null) {
            System.out.println("Section: " + section.getSectionCode());
        } else {
            System.out.println("No section assigned yet.");
        }
    }

    // Switch sections for the student
    public void switchSection() {
        System.out.println("Switching section...");
        // Logic for switching section
    }
}
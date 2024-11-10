public class Student extends User {
    private String studentID;
    private boolean isRegular;

    public Student(String username, String password, String studentID, boolean isRegular) {
        super(username, password);
        this.studentID = studentID;
        this.isRegular = isRegular;
    }

    public String getStudentID() {
        return studentID;
    }

    public boolean isRegular() {
        return isRegular;
    }
}

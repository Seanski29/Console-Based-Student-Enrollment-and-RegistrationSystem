public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    // Example CRUD method for demonstration
    public void viewStudentInfo(Student student) {
        System.out.println("Student ID: " + student.getStudentID());
        System.out.println("Is Regular: " + (student.isRegular() ? "Yes" : "No"));
    }
}

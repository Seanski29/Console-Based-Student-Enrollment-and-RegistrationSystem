import java.sql.*;

public class Admin {
    private Connection connection;

    public Admin() {
        connection = DatabaseConnection.connect(); // Connection to DB
    }

    // Admin login logic
    public boolean login(String username, String password) {
        // Placeholder login logic for Admin
        return "admin".equals(username) && "admin123".equals(password); // Simplified for demo
    }

    // View student info based on student ID
    public void viewStudentInfo(int studentId) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("SR Code: " + rs.getString("sr_code"));
            }
        } catch (SQLException e) {
            System.out.println("Error viewing student info: " + e.getMessage());
        }
    }

    // Manage sections (e.g., add, remove sections)
    public void manageSections() {
        // Placeholder logic for managing sections
        System.out.println("Managing sections...");
    }
}

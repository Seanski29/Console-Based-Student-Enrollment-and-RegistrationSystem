// Define an interface for admin operations
import java.sql.*;

interface AdminOperations {
    void createUser(String username, String password, String srCode, boolean isRegular, String section);
    void readUser(int studentId);
    void updateUser(int studentId, String username, String password, String srCode, boolean isRegular, String section);
    void deleteUser(int studentId);
    void listAllUsers();
}

public class Admin implements AdminOperations {
    private Connection connection;
    private final String adminUsername = "admin"; // Default admin username
    private final String adminPassword = "admin123"; // Default admin password

    // Constructor to establish a database connection
    public Admin() {
        connection = DatabaseConnection.connect(); // Assuming a utility method for database connection
        if (connection == null) {
            System.out.println("Failed to connect to the database.");
            System.exit(1); // Exit if the database connection fails
        }
    }

    // Admin login method
    public boolean login(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    @Override
    public void createUser(String username, String password, String srCode, boolean isRegular, String section) {
        String query = "INSERT INTO students (username, password, sr_code, is_regular, sections) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, srCode);
            ps.setBoolean(4, isRegular);
            ps.setString(5, section);
            ps.executeUpdate();
            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    @Override
    public void readUser(int studentId) {
        String query = "SELECT * FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
                System.out.println("SR Code: " + rs.getString("sr_code"));
                System.out.println("Is Regular: " + rs.getBoolean("is_regular"));
                System.out.println("Section: " + rs.getString("sections"));
            } else {
                System.out.println("No user found with ID: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error reading user: " + e.getMessage());
        }
    }

    @Override
    public void updateUser(int studentId, String username, String password, String srCode, boolean isRegular, String section) {
        String query = "UPDATE students SET username = ?, password = ?, sr_code = ?, is_regular = ?, sections = ? WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, srCode);
            ps.setBoolean(4, isRegular);
            ps.setString(5, section);
            ps.setInt(6, studentId);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("No user found with ID: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(int studentId) {
        String query = "DELETE FROM students WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("No user found with ID: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    @Override
    public void listAllUsers() {
        String query = "SELECT * FROM students";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("List of all users:");
            while (rs.next()) {
                System.out.println("------------------------");
                System.out.println("Student ID: " + rs.getInt("student_id"));
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("Password: " + rs.getString("password"));
                System.out.println("SR Code: " + rs.getString("sr_code"));
                System.out.println("Is Regular: " + rs.getBoolean("is_regular"));
                System.out.println("Section: " + rs.getString("sections"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing users: " + e.getMessage());
        }
    }
}

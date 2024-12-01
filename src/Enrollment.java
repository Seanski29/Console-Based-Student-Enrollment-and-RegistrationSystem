import java.sql.*;
import java.util.Scanner;

public class Enrollment {
    private Connection connection;

    public Enrollment() {
        connection = DatabaseConnection.connect();
    }

    public int authenticateStudent(String username, String password) {
        String query = "SELECT student_id FROM students WHERE username = ? AND password = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("student_id");
            }
        } catch (SQLException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        }
        return -1;
    }
    public void displaySubjects() {
        String query = "SELECT subject_code, subject_name FROM subjects";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("==== Subjects ====");
            while (rs.next()) {
                System.out.println("Subject Code: " + rs.getString("subject_code") + "   Subject Name: " + rs.getString("subject_name"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to display subjects: " + e.getMessage());
        }
    }


    public void displayProfessors() {
        String query = "SELECT p.professor_name, s.subject_code FROM professors p " +
                       "LEFT JOIN subjects s ON p.professor_id = s.professor_id";

        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("==== Professors ====");
            int count = 0;
            while (rs.next() && count < 7) {  // Limit to 7 professors
                System.out.println("Subject Code: " + rs.getString("subject_code") +
                                   ", Professor Name: " + rs.getString("professor_name"));
                count++;
            }
        } catch (SQLException e) {
            System.out.println("Failed to display professors: " + e.getMessage());
        }
    }





    public void registerStudentInSection(Scanner scanner, int studentId) {
        System.out.println("Available Sections:");
        displayAvailableSections();

        System.out.print("Enter the Section Code: ");
        String sectionCode = scanner.nextLine();

        String query = "UPDATE students SET sections = ? WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, sectionCode);
            ps.setInt(2, studentId);
            ps.executeUpdate();
            System.out.println("Successfully registered in section: " + sectionCode);
        } catch (SQLException e) {
            System.out.println("Failed to register section: " + e.getMessage());
        }
    }

    public void viewStudentDetails(int studentId) {
        String query = """
            SELECT s.username, s.sr_code, s.is_regular, ss.section_code
            FROM students s
            LEFT JOIN sections ss ON s.sections = ss.section_code
            WHERE s.student_id = ?
            """;
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Username: " + rs.getString("username"));
                System.out.println("SR Code: " + rs.getString("sr_code"));
                System.out.println("Regular Status: " + (rs.getBoolean("is_regular") ? "Regular" : "Irregular"));
                System.out.println("Section Code: " + rs.getString("section_code"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to view student details: " + e.getMessage());
        }
    }

    public void displayAvailableSections() {
        String query = "SELECT section_code, max_slots FROM sections";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getString("section_code") + " - Max Slots: " + rs.getInt("max_slots"));
            }
        } catch (SQLException e) {
            System.out.println("Failed to display sections: " + e.getMessage());
        }
    }

    public void switchSection(Scanner scanner, int studentId) {
        System.out.println("Available Sections:");
        displayAvailableSections();

        System.out.print("Enter new Section Code: ");
        String newSection = scanner.nextLine();

        String query = "UPDATE students SET sections = ? WHERE student_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, newSection);
            ps.setInt(2, studentId);
            ps.executeUpdate();
            System.out.println("Section switched successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to switch section: " + e.getMessage());
        }
    }
}





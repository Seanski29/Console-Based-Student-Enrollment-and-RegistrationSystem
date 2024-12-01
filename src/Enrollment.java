import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Enrollment {
    private Connection connection;

    public Enrollment() {
        connection = DatabaseConnection.connect(); // Corrected method call
    }

    // Register student (based on regular or irregular status)
    public void registerStudent(Student student) {
        if (student.isRegular()) {
            registerRegularStudent(student); // Regular student registration
        } else {
            registerIrregularStudent(student); // Irregular student registration
        }
    }

    // Regular student registration (auto enroll in all subjects)
    private void registerRegularStudent(Student student) {
        System.out.println("Regular student: Registering in all subjects...");

        // Example: Assign a default section for regular students
        Sections defaultSection = new Sections(1, "BSIT1201", 30); // Replace with actual section assignment logic
        student.setSection(defaultSection);  // Assign the section to the student

        // Update section's available slots
        defaultSection.addStudent(student);
    }

    // Irregular student registration (choose section and subjects)
    private void registerIrregularStudent(Student student) {
        System.out.println("Irregular student: Choose section and subjects.");
        // Add logic here for section and subject selection
    }

    // View all available sections and subjects
    public void viewAvailableSectionsAndSubjects() {
        // Example query to fetch available sections
        String query = "SELECT * FROM sections WHERE available_slots > 0";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Section: " + rs.getString("section_code"));
                System.out.println("Available Slots: " + rs.getInt("available_slots"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching sections: " + e.getMessage());
        }
    }
}

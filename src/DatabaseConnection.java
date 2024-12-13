import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/enrollmentsystem?useSSL=false";
    private static final String USER = "root"; // Default username in XAMPP
    private static final String PASSWORD = ""; // Default password is empty in XAMPP

    public static Connection connect() {
        try {
            // Load the MySQL JDBC driver (for compatibility with older versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish and return the connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver not found.");
            e.printStackTrace();
            return null;
        }
    }
}

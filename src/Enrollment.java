import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Enrollment {
    private List<Student> students;
    private Admin admin;
    private List<Subjects> subjects;
    private List<Sections> sections;

    public Enrollment() {
        students = new ArrayList<>();
        subjects = new ArrayList<>();
        sections = new ArrayList<>();
        admin = new Admin("admin", "admin123");

        // Initializing hardcoded data
        initializeData();
    }

    // Initializes students, subjects, and sections
    private void initializeData() {
        students.add(new Student("alice", "pass1", "S101", true));
        students.add(new Student("bob", "pass2", "S102", false));

        subjects.add(new Subjects("CS101", "Programming 101", 30));
        sections.add(new Sections("A", "Prof. Smith"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Enrollment System");

        System.out.println("1. Student Login\n2. Admin Login\n3. Exit");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                studentLogin(scanner);
                break;
            case 2:
                adminLogin(scanner);
                break;
            case 3:
                System.out.println("Exiting system...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void studentLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (Student student : students) {
            if (student.login(username, password)) {
                System.out.println("Login successful!");
                // Display enrollment options for student
                return;
            }
        }
        System.out.println("Invalid credentials.");
    }

    private void adminLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (admin.login(username, password)) {
            System.out.println("Admin login successful!");
            // Perform CRUD operations
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }
}

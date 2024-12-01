import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Student student = new Student("student1", "password1", "12345", true); // Example student
        Enrollment enrollment = new Enrollment();

        while (true) {
            System.out.println("Welcome to the Enrollment System");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                // Admin login
                System.out.print("Enter Admin Username: ");
                String adminUsername = scanner.nextLine();
                System.out.print("Enter Admin Password: ");
                String adminPassword = scanner.nextLine();
                if (admin.login(adminUsername, adminPassword)) {
                    System.out.println("Admin login successful!");
                    admin.manageSections();
                } else {
                    System.out.println("Login failed.");
                }
            } else if (choice == 2) {
                // Student login
                System.out.print("Enter your username: ");
                String username = scanner.nextLine();
                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                if (student.login(username, password)) {
                    System.out.println("Student login successful!");
                    // Register student (ensure student has a section)
                    enrollment.registerStudent(student);
                    studentOptions(scanner, student, enrollment);
                } else {
                    System.out.println("Login failed.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void studentOptions(Scanner scanner, Student student, Enrollment enrollment) {
        while (true) {
            System.out.println("\nStudent Menu:");
            System.out.println("1. Register for Section");
            System.out.println("2. View Your Details");
            System.out.println("3. Switch Section");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                enrollment.registerStudent(student);
            } else if (choice == 2) {
                student.viewStudentDetails(); // Now works without error
            } else if (choice == 3) {
                student.switchSection();
            } else if (choice == 4) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

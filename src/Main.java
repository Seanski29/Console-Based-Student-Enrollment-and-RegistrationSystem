import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Enrollment enrollment = new Enrollment();

        while (true) {
            System.out.println("\n==== Enrollment System ====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Login as Student");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> adminLogin(scanner, admin);
                case 2 -> studentLogin(scanner, enrollment);
                case 3 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void adminLogin(Scanner scanner, Admin admin) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        if (admin.login(username, password)) {
            System.out.println("Admin login successful!");
            admin.manageSections();
        } else {
            System.out.println("Login failed.");
        }
    }

    public static void studentLogin(Scanner scanner, Enrollment enrollment) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        int studentId = enrollment.authenticateStudent(username, password);
        if (studentId > 0) {
            System.out.println("Student login successful!");
            studentOptions(scanner, enrollment, studentId);
        } else {
            System.out.println("Login failed.");
        }
    }

    public static void studentOptions(Scanner scanner, Enrollment enrollment, int studentId) {
        while (true) {
            System.out.println("\n==== Student Menu ====");
            System.out.println("1. Register for Section");
            System.out.println("2. View Your Details");
            System.out.println("3. Switch Section");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> enrollment.registerStudentInSection(scanner, studentId);
                case 2 -> enrollment.viewStudentDetails(studentId);
                case 3 -> enrollment.switchSection(scanner, studentId);
                case 4 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}


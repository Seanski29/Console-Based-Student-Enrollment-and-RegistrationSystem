import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Enrollment enrollment = new Enrollment();

        while (true) {
            System.out.println("\n==== CICS (1stYear) Second Semester ====");
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
            adminPanel(scanner, admin);
        } else {
            System.out.println("Login failed.");
        }
    }

    public static void adminPanel(Scanner scanner, Admin admin) {
        while (true) {
            System.out.println("\n==== Admin ====");
            System.out.println("1. Create User");
            System.out.println("2. Read User");
            System.out.println("3. Update User");
            System.out.println("4. Delete User");
            System.out.println("5. List All Users");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter SR Code: ");
                    String srCode = scanner.nextLine();
                    System.out.print("Is Regular (true/false): ");
                    boolean isRegular = scanner.nextBoolean();
                    scanner.nextLine();
                    System.out.print("Enter section: ");
                    String section = scanner.nextLine();
                    admin.createUser(username, password, srCode, isRegular, section);
                }
                case 2 -> {
                    System.out.print("Enter Student ID to read: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    admin.readUser(studentId);
                }
                case 3 -> {
                    System.out.print("Enter Student ID to update: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter new SR Code: ");
                    String srCode = scanner.nextLine();
                    System.out.print("Is Regular (true/false): ");
                    boolean isRegular = scanner.nextBoolean();
                    scanner.nextLine();
                    System.out.print("Enter new section: ");
                    String section = scanner.nextLine();
                    admin.updateUser(studentId, username, password, srCode, isRegular, section);
                }
                case 4 -> {
                    System.out.print("Enter Student ID to delete: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    admin.deleteUser(studentId);
                }
                case 5 -> admin.listAllUsers();
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void studentLogin(Scanner scanner, Enrollment enrollment) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        Student student = enrollment.authenticateStudent(username, password, null);
        if (student != null) {
            if (student.isRegular()) {
                System.out.println("Student login successful!");
                studentOptions(scanner, enrollment, student.getStudentId(), student.isRegular());
            } else {
                System.out.println("Student login successful! IRREGULAR DETECTED");
                irregstudentOptions(scanner, enrollment, student.getStudentId(), student.isRegular());
            }
        } else {
            System.out.println("Login failed.");
        }
    }

    private static void studentOptions(Scanner scanner, Enrollment enrollment, int studentId, Boolean isRegular) {
        while (true) {
            System.out.println("\n==== Spartan Menu ====");
            System.out.println("1. Register for Section");
            System.out.println("2. View Your Details");
            System.out.println("3. Switch Section");
            System.out.println("4. View Subjects");  // New option
            System.out.println("5. View Professors");  // New option
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");

            int choice1 = scanner.nextInt();

            switch (choice1) {
                case 1 -> enrollment.registerStudentInSection(scanner, studentId);
                case 2 -> enrollment.viewStudentDetails(studentId);
                case 3 -> enrollment.switchSection(scanner, studentId);
                case 4 -> enrollment.displaySubjects();
                case 5 -> enrollment.displayProfessors();
                case 6 -> {
                    System.out.println("Logging out...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

        //FOR IRREGULARS ETO2
        private static void irregstudentOptions(Scanner scanner, Enrollment enrollment, int studentId, Boolean isRegular) {
            while (true) {
                System.out.println("\n==== Spartan for Irregulars Menu ====");
                System.out.println("1. Register for Section");
                System.out.println("2. View Your Details");
                System.out.println("3. Switch Section");
                System.out.println("4. Register for a Subjects");  // New option
                System.out.println("5. Delete a Registered Subject");  // New option
                System.out.println("6. View Professors");  // New option
                System.out.println("7. Logout");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                switch (choice) {
                    case 1 -> enrollment.registerStudentInSection(scanner, studentId);
                    case 2 -> enrollment.viewIrregDetails(studentId);
                    case 3 -> enrollment.switchSection(scanner, studentId);
                    case 4 -> enrollment.registerSubjects(scanner, studentId);
                    case 5 -> enrollment.deleteRegisteredSubject(scanner, studentId);
                    case 6 -> enrollment.displayProfessors();
                    case 7 -> {
                        System.out.println("Logging out...");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        }
}

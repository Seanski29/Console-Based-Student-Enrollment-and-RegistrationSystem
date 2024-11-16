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

    private void initializeData() {

        students.add(new Student("Sean", "password", "23-36324", true));
        students.add(new Student("Axle", "password", "23-17423", false));
        students.add(new Student("Erll", "password", "23-30129", true));
        students.add(new Student("Jarell", "password", "23-31234", false));

        // Hardcoded subject data
        subjects.add(new Subjects("CS111", "Computer Programming", 40));
        subjects.add(new Subjects("CS131", "Data Structures and Algorithms", 40));
        subjects.add(new Subjects("MATH111", "Linear Algebra", 30));
        subjects.add(new Subjects("FILI102", "Filipino sa Iba't Ibang Disiplina", 30));
        subjects.add(new Subjects("GeD105", "Readings in Philippine History", 30));
        subjects.add(new Subjects("GeD109", "Science, Technology and Society", 30));
        subjects.add(new Subjects("PE102", "Rhythmic Activities", 30));
        subjects.add(new Subjects("NSTP121", "National Service Training Program 2", 50));

        // Hardcoded section data
        sections.add(new Sections("BSIT-1201"));
        sections.add(new Sections("BSIT-1202"));
        sections.add(new Sections("BSIT-1203"));
        sections.add(new Sections("BSIT-1204"));
        sections.add(new Sections("BSIT-1205"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println("Welcome to the BSU-TNEU Lipa Campus");
            System.out.println("First Year | SECOND SEMESTER Batch 2024-2025");
            System.out.println("Please Input the number below to Login");
            System.out.println("1. Student Login\n2. Admin Login\n3. Exit");
            try {
                choice = scanner.nextInt();
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
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // to clear the buffer
            }
        }
    }

    private void studentLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        for (Student student : students) {
            if (student.login(username, password)) {
                System.out.println("Login successful. Welcome!");
                handleStudentOperations(scanner, student);
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
            handleAdminOperations(scanner);
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    // Student-related actions
    private void handleStudentOperations(Scanner scanner, Student student) {
        int choice = 0;
        while (choice != 4) {
            System.out.println("1. Register for a Subject\n2. Pick a Section\n3. View Your Details\n4. Logout");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        registerForSubject(scanner, student);
                        break;
                    case 2:
                        pickSection(scanner, student);
                        break;
                    case 3:
                        viewStudentDetails(student);
                        break;
                    case 4:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine(); // clear buffer
            }
        }
    }

    private void registerForSubject(Scanner scanner, Student student) {
        System.out.println("Available subjects: ");
        for (int i = 0; i < subjects.size(); i++) {
            Subjects subject = subjects.get(i);
            System.out.println((i + 1) + ". " + subject.getSubjectName() + " (Slots available: " + subject.hasAvailableSeats() + ")");
        }

        System.out.print("Enter the number of the subject to enroll in: ");
        int subjectChoice = scanner.nextInt();
        if (subjectChoice > 0 && subjectChoice <= subjects.size()) {
            Subjects chosenSubject = subjects.get(subjectChoice - 1);
            if (chosenSubject.hasAvailableSeats()) {
                chosenSubject.enrollStudent();
                System.out.println("Successfully enrolled in " + chosenSubject.getSubjectName());
            } else {
                System.out.println("Sorry, no Slots available for " + chosenSubject.getSubjectName());
            }
        } else {
            System.out.println("Invalid subject choice.");
        }
    }

    private void pickSection(Scanner scanner, Student student) {
        System.out.println("Available sections: ");
        for (int i = 0; i < sections.size(); i++) {
            Sections section = sections.get(i);
            System.out.println((i + 1) + ". Section " + section.getSectionCode());
        }

        System.out.print("Enter the number of the section to join: ");
        int sectionChoice = scanner.nextInt();
        if (sectionChoice > 0 && sectionChoice <= sections.size()) {
            Sections chosenSection = sections.get(sectionChoice - 1);
            chosenSection.addStudent(student);
            System.out.println("Successfully enrolled in Section " + chosenSection.getSectionCode());
        } else {
            System.out.println("Invalid section choice.");
        }
    }

    private void viewStudentDetails(Student student) {
        System.out.println("Student ID: " + student.getStudentID());
        System.out.println("Regular: " + (student.isRegular() ? "Yes" : "No"));
    }

    // Admin-related actions
    private void handleAdminOperations(Scanner scanner) {
        int choice = 0;
        while (choice != 5) {
            System.out.println("1. View All Students\n2. View All Subjects\n3. View All Sections\n4. Add Subject\n5. Logout");
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        viewAllStudents();
                        break;
                    case 2:
                        viewAllSubjects();
                        break;
                    case 3:
                        viewAllSections();
                        break;
                    case 4:
                        addSubject(scanner);
                        break;
                    case 5:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Try again.");
                scanner.nextLine(); // clear buffer
            }
        }
    }

    private void viewAllStudents() {
        System.out.println("List of all students:");
        for (Student student : students) {
            System.out.println("Username: " + student.getUsername());
        }
    }

    private void viewAllSubjects() {
        System.out.println("List of all subjects:");
        for (Subjects subject : subjects) {
            System.out.println("Subject Code: " + subject.getSubjectCode() + ", Name: " + subject.getSubjectName());
        }
    }

    private void viewAllSections() {
        System.out.println("List of all sections:");
        for (Sections section : sections) {
            System.out.println("Section Code: " + section.getSectionCode());
        }
    }

    private void addSubject(Scanner scanner) {
        System.out.print("Enter subject code: ");
        String code = scanner.next();
        System.out.print("Enter subject name: ");
        String name = scanner.next();
        System.out.print("Enter available seats: ");
        int seats = scanner.nextInt();

        Subjects newSubject = new Subjects(code, name, seats);
        subjects.add(newSubject);
        System.out.println("New subject added.");
    }
}

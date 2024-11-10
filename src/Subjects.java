public class Subjects {
    private String subjectCode;
    private String subjectName;
    private int availableSeats;

    public Subjects(String subjectCode, String subjectName, int availableSeats) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.availableSeats = availableSeats;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }

    // Method to enroll a student, decreases seat count
    public void enrollStudent() {
        if (hasAvailableSeats()) {
            availableSeats--;
        } else {
            System.out.println("No seats available for " + subjectName);
        }
    }
}

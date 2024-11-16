public class Subjects {
    private String subjectCode;
    private String subjectName;
    private int availableSlots;

    public Subjects(String subjectCode, String subjectName, int availableSlots) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.availableSlots = availableSlots;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public boolean hasAvailableSeats() {
        return availableSlots > 0;
    }

    // Method to enroll a student, decreases seat count
    public void enrollStudent() {
        if (hasAvailableSeats()) {
            availableSlots--;
        } else {
            System.out.println("No seats available for " + subjectName);
        }
    }
}

public class Subjects {
    private String subjectCode;
    private String subjectName;
    private int professorId; // Referring to professor_id in the database

    public Subjects(String subjectCode, String subjectName, int professorId) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.professorId = professorId;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getProfessorId() {
        return professorId;
    }
}

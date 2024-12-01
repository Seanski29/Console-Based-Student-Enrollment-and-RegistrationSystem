public class Professors {
    private int professorId;
    private String professorName;
    private String professorUsername;

    public Professors(int professorId, String professorName, String professorUsername) {
        this.professorId = professorId;
        this.professorName = professorName;
        this.professorUsername = professorUsername;
    }

    public int getProfessorId() {
        return professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public String getProfessorUsername() {
        return professorUsername;
    }
}

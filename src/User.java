// Abstract class User that defines common properties and methods
public abstract class User {
    protected String username;
    protected String password;

    // Constructor to initialize common properties
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Login validation method
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() {
        return username;
    }

    // Abstract method for viewing user details, to be implemented in subclasses
    public abstract void viewDetails();

    // Abstract method for registering subjects, to be implemented in subclasses
    public abstract void registerForSubject(String subjectCode);
}

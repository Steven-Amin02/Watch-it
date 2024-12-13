package Model;
import java.io.Serializable;
import java.util.regex.Pattern;

public abstract class Person implements Serializable {
    private String username;
    private String password;
    protected String email;
    private String firstName;
    private String lastName;

    public Person(String username, String password, String email, String firstName, String lastName) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
    }
    public Person(){};
    // Username Validation
    public void setUsername(String username) {
        if (username == null || username.contains(" ")) {
            throw new IllegalArgumentException("Username cannot be null or contain spaces.");
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    // Password Validation
    public void setPassword(String password) {
        if (password == null || password.length() < 8 ||
                !password.matches(".*[A-Z].*") || !password.matches(".*[@#$*].*")) {
            throw new IllegalArgumentException("Password must be at least 8 characters, contain one uppercase letter, and one special character.");
        }
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // Abstract Email Validation
    public abstract void setEmail(String email);

    public String getEmail() {
        return email;
    }

    protected void validateEmail(String email, String regex) {
        if (email == null || !Pattern.matches(regex, email)) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    // First Name Validation
    public void setFirstName(String firstName) {
        if (firstName == null || !firstName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("First name must only contain letters.");
        }
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    // Last Name Validation
    public void setLastName(String lastName) {
        if (lastName == null || !lastName.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Last name must only contain letters.");
        }
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }
}
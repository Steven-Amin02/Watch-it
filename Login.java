import java.util.HashMap;
import java.util.Scanner;

public class Login {
    private HashMap<String, User> users;  // Separate HashMap for Users
    private HashMap<String, Admin> admins; // Separate HashMap for Admins
    private Scanner scanner;

    public Login() {
        users = new HashMap<>();
        admins = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    // Sign up method (handles both User and Admin)
    public void signUp() throws Exception {
        // Prompt for first name and validate
        System.out.println("Enter your first name:");
        String firstName = enterValidName("First name must only contain letters.");

        // Prompt for last name and validate
        System.out.println("Enter your last name:");
        String lastName = enterValidName("Last name must only contain letters.");

        // Prompt for username and validate
        System.out.println("Enter your username:");
        String username = enterValidUsername();

        // Prompt for password and validate
        System.out.println("Enter your password:");
        String password = enterValidPassword();

        // Prompt for email and validate
        System.out.println("Enter your email:");
        String email = enterValidEmail();

        // Automatically assign user type based on email format
        char type = assignUserTypeBasedOnEmail(email);

        // Register the user/admin based on the type
        if (type == 'U') {
            User user = new User(username, password, email, firstName, lastName);
            registerUser(user);
            System.out.println("User signed up successfully! Your ID: " + user.getId());
        } else if (type == 'A') {
            Admin admin = new Admin(username, password, email, firstName, lastName);
            registerAdmin(admin);
            System.out.println("Admin signed up successfully! Your ID: " + admin.getId());
        } else {
            throw new Exception("Invalid email format. Unable to assign user type.");
        }
    }

    // Helper method to validate name (first name or last name)
    private String enterValidName(String errorMessage) {
        while (true) {
            String name = scanner.nextLine();
            if (name.matches("[a-zA-Z]+")) {
                return name;
            }
            System.out.println("Error: " + errorMessage);
        }
    }

    // Helper method to validate username
    private String enterValidUsername() {
        while (true) {
            String username = scanner.nextLine();
            try {
                validateUsername(username);
                return username;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Helper method to validate password
    private String enterValidPassword() {
        while (true) {
            String password = scanner.nextLine();
            try {
                validatePassword(password);
                return password;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Helper method to validate email
    private String enterValidEmail() {
        while (true) {
            String email = scanner.nextLine();
            try {
                validateEmail(email, "^[\\w.-]+@watchit\\.(com|admin\\.com)$");
                return email;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Automatically assign user type based on email domain
    private char assignUserTypeBasedOnEmail(String email) throws Exception {
        if (email.endsWith("@watchit.com")) {
            return 'U';  // User
        } else if (email.endsWith("@watchit.admin.com")) {
            return 'A';  // Admin
        } else {
            throw new Exception("Email format is invalid. Must end with '@watchit.com' for users or '@watchit.admin.com' for admins.");
        }
    }

    // Field validation methods
    private void validateUsername(String username) throws Exception {
        if (username.isEmpty() || username.contains(" ")) {
            throw new Exception("Username cannot be empty or contain spaces.");
        }
        if (isUsernameTaken(username)) {
            throw new Exception("Username already exists. Please choose another.");
        }
    }

    private void validatePassword(String password) throws Exception {
        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[@#$*].*")) {
            throw new Exception("Password must be at least 8 characters long, contain one uppercase letter, and one special character (@, #, $, *).");
        }
    }

    private void validateEmail(String email, String regex) throws Exception {
        if (!email.matches(regex)) {
            throw new Exception("Invalid email format.");
        }
    }

    // Helper methods for registration
    private void registerUser(User user) throws Exception {
        if (users.containsKey(user.getUsername())) {
            throw new Exception("Username already exists for User.");
        }
        users.put(user.getUsername(), user);
    }

    private void registerAdmin(Admin admin) throws Exception {
        if (admins.containsKey(admin.getUsername())) {
            throw new Exception("Username already exists for Admin.");
        }
        admins.put(admin.getUsername(), admin);
    }

    // Login method for both User and Admin
    public Person loginUser(String username, String password) throws Exception {
        // Check in Users
        if (users.containsKey(username)) {
            User user = users.get(username);
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                throw new Exception("Invalid password for User.");
            }
        }

        // Check in Admins
        if (admins.containsKey(username)) {
            Admin admin = admins.get(username);
            if (admin.getPassword().equals(password)) {
                return admin;
            } else {
                throw new Exception("Invalid password for Admin.");
            }
        }

        throw new Exception("Username not found.");
    }

    // Check if username is taken
    public boolean isUsernameTaken(String username) {
        return users.containsKey(username) || admins.containsKey(username);
    }

    public void deleteUser(String username) throws Exception {
        if (users.containsKey(username)) {
            users.remove(username);
            System.out.println("User " + username + " has been deleted.");
        } else {
            throw new Exception("User with username " + username + " not found.");
        }
    }

    // Method for Admin to remove a User (called from Admin menu)
    public void adminDeleteUser(String username) {
        try {
            deleteUser(username);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

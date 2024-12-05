public class Admin extends Person {
    private String id;

    public Admin(String username, String password, String email, String fname, String lname) {
        super(username, password, email, fname, lname);
        this.id = IDGenerator.generateID('A'); // Generate unique admin ID
    }

    @Override
    public void setEmail(String email) {
        // Use a specific regex for Admin email validation
        validateEmail(email, "^[\\w.-]+@watchit\\.admin\\.com$");
    }

    public String getId() {
        return id;
    }

    public void deleteUser(Login loginSystem, String username) {
        try {
            loginSystem.deleteUser(username); // Call deleteUser method in the Login class
            System.out.println("User " + username + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

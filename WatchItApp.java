import java.util.Scanner;

public class WatchItApp {
    private static Login loginSystem = new Login();  // Login system to manage users/admins
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to WatchIt App!");
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            try {
                switch (choice) {
                    case 1:
                        loginSystem.signUp();  // Sign up process
                        break;
                    case 2:
                        logIn();  // Log in process
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        return;  // Exit the application
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Sign up logic for users


    // Log in logic for users
    private static void logIn() {
        System.out.println("\nLog In");

        String username = enterField("Enter Username: ");
        String password = enterField("Enter Password: ");

        try {
            Person loggedIn = loginSystem.loginUser(username, password);
            System.out.println("Login successful! Welcome " + loggedIn.getUsername());

            if (loggedIn instanceof User) {
                userMenu((User) loggedIn);  // User-specific menu
            } else if (loggedIn instanceof Admin) {
                adminMenu((Admin) loggedIn);  // Admin-specific menu
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper method to simplify field input
    private static String enterField(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // User-specific interactive menu
    private static void userMenu(User user) {
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. View Watched Movies");
            System.out.println("2. Add Watched Movie");
            System.out.println("3. View To-Watch List");
            System.out.println("4. Add Movie to To-Watch List");
            System.out.println("5. Settings");
            System.out.println("6. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // View watched movies
                    System.out.println("Watched Movies: ");
                    if (user.getWatchedMovies().isEmpty()) {
                        System.out.println("No watched movies.");
                    } else {
                        for (Movie movie : user.getWatchedMovies()) {
                            System.out.println("- " + movie);
                        }
                    }
                    break;

                case 2:
                    // Add a watched movie
                    System.out.print("Enter Movie Name to Add to Watched List: ");
                    String watchedMovieName = scanner.nextLine();
                    System.out.print("Enter Movie Genre: ");
                    String watchedMovieGenre = scanner.nextLine();
                    System.out.print("Enter Movie Director: ");
                    String watchedMovieDirector = scanner.nextLine();
                    Movie watchedMovie = new Movie(watchedMovieName, watchedMovieGenre, watchedMovieDirector);
                    user.addWatchedMovie(watchedMovie);
                    System.out.println("Movie added to Watched Movies.");
                    break;

                case 3:
                    // View to-watch movies
                    System.out.println("To-Watch Movies: ");
                    if (user.getToWatchMovies().isEmpty()) {
                        System.out.println("No movies in To-Watch List.");
                    } else {
                        for (Movie movie : user.getToWatchMovies()) {
                            System.out.println("- " + movie);
                        }
                    }
                    break;

                case 4:
                    // Add a movie to the To-Watch List
                    System.out.print("Enter Movie Name to Add to To-Watch List: ");
                    String toWatchMovieName = scanner.nextLine();
                    System.out.print("Enter Movie Genre: ");
                    String toWatchMovieGenre = scanner.nextLine();
                    System.out.print("Enter Movie Director: ");
                    String toWatchMovieDirector = scanner.nextLine();
                    Movie toWatchMovie = new Movie(toWatchMovieName, toWatchMovieGenre, toWatchMovieDirector);
                    user.addToWatchMovie(toWatchMovie);
                    System.out.println("Movie added to To-Watch List.");
                    break;
                case 5:
                    // Settings
                    settingsMenu(user);
                    break;

                case 6:
                    // Log out
                    System.out.println("Logged out.");
                    return;  // Return to the main menu

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Settings menu for changing password, username, or email
    private static void settingsMenu(User user) {
        while (true) {
            System.out.println("\nSettings Menu:");
            System.out.println("1. Change Password");
            System.out.println("2. Change Username");
            System.out.println("3. Change Email");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Change password
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    user.setPassword(newPassword);
                    System.out.println("Password changed successfully.");
                    break;

                case 2:
                    // Change username
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    user.setUsername(newUsername);
                    System.out.println("Username changed successfully.");
                    break;

                case 3:
                    // Change email
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    user.setEmail(newEmail);
                    System.out.println("Email changed successfully.");
                    break;

                case 4:
                    // Go back to the previous menu
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin-specific menu (for completeness)
    private static void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Remove User");
            System.out.println("2. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Admin deletes a user
                    System.out.print("Enter Username to Remove: ");
                    String usernameToRemove = scanner.nextLine();
                    admin.deleteUser(loginSystem, usernameToRemove);  // Admin calls deleteUser from Login
                    break;
                case 2:
                    // Log out
                    System.out.println("Logged out.");
                    return;  // Return to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

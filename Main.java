package Model;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Login loginSystem = new Login();  // Login system to manage users/admins
    static ArrayList <Movie> movies = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    static Recommendation recommendation = new Recommendation();

    public static void main(String[] args) throws IOException {
        loadDataFromFile();
/*        Movie movie = new Movie( "title", LocalDate.of(2010, 7, 16), 2.4f,  4.6f,  45f,  "poster", "country",  3f ,  "genre");
        movies.add(movie);
        movie.writeToFile(movie);*/
        while (true) {
            System.out.println("-----------Welcome to WatchIt App!-----------");
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
                        System.out.println(loginSystem.users);
                        break;
                    case 2:
                        Person loggedIn = loginSystem.loginUser();  // Log in process
                        if (loggedIn instanceof User) {
                            userMenu((User) loggedIn);  // User-specific menu
                        } else if (loggedIn instanceof Admin) {
                            adminMenu((Admin) loggedIn);  // Admin-specific menu
                        }
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        loginSystem.admins.get(0).writeToFile(loginSystem.admins);
                        loginSystem.users.get(0).writeToFile(loginSystem.users);
                        System.exit(0);  // Exit the application
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    // User-specific interactive menu
    private static void userMenu(User user) {
        user.subscription.checkSubscriptionStatus();
        while (true) {
            System.out.println("\nUser Menu:");
            System.out.println("1. view all avilable Movies");
            System.out.println("2. View Watched Movies");
            System.out.println("3. Add Watched Movie");
            System.out.println("4. View To-Watch List");
            System.out.println("5. Add Movie to To-Watch List");
            System.out.println("6. Manage Subsicriptions");
            System.out.println("7. Settings");
            System.out.println("8. Log Out");
            System.out.println("9. show last 10 movie");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    Movie.displayAll(movies);
                    recommendation.Search(movies);
                    break;
                case 2:
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

                case 3:
                    // Add a watched movie
                    System.out.print("Enter Movie Name to Add to Watched List: ");
                    String watchedMovieName = scanner.nextLine();
                    Movie watchedMovie = new Movie();
                    user.addWatchedMovie(watchedMovie);
                    System.out.println("Movie added to Watched Movies.");
                    break;

                case 4:
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

                case 5:
                    // Add a movie to the To-Watch List
                    System.out.print("Enter Movie Name to Add to To-Watch List: ");
                    String toWatchMovieName = scanner.nextLine();
                    System.out.print("Enter Movie Genre: ");
                    String toWatchMovieGenre = scanner.nextLine();
                    System.out.print("Enter Movie Director: ");
                    String toWatchMovieDirector = scanner.nextLine();
                    Movie toWatchMovie = new Movie();
                    user.addToWatchMovie(toWatchMovie);
                    System.out.println("Movie added to To-Watch List.");
                    break;
                case 6:
                    // Settings
                    user.subscription.test();
                    break;

                case 7:
                    // Settings
                    settingsMenu(user);
                    break;

                case 8:
                    // Log out
                    System.out.println("Logged out.");
                    return;  // Return to the main menu
                case 9:


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
    private static void loadDataFromFile() {
        System.out.println("Loading user data from file...");
        User tempUser = new User(); // Temporary user object to call FileReader
        Admin tempAdmin = new Admin();
        Movie tempMovie = new Movie();
        try {
            tempUser.FileReader(loginSystem.users); // Load data into the users list
            System.out.println("User data loaded successfully!");
            tempAdmin.FileReader(loginSystem.admins);
            System.out.println("Admin data loaded successfully!");
            tempMovie.FileReader(movies);
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    // Admin-specific menu (for completeness)
    private static void adminMenu(Admin admin) throws Exception {
        while (true) {
            System.out.println("-----------Admin Menu-----------");
            System.out.println("[1] Manage Users");
            System.out.println("[2] Manage Movies");
            System.out.println("[3] Manage User View Stats");
            System.out.println("[4] Log Out");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.println("------------Manage Users Menu------------");
                        System.out.println("[1] Add User");
                        System.out.println("[2] Modify User");
                        System.out.println("[3] Delete User");
                        System.out.println("[4] Return to Main Menu");
                        System.out.print("Enter your choice: ");

                        int userChoice = scanner.nextInt();

                        switch (userChoice) {
                            case 1:
                                admin.addUser(loginSystem);
                                break;
                            case 2:
                                admin.modifyUser(loginSystem);
                                admin.listAllUsers(loginSystem.users);
                                break;
                            case 3:
                                admin.deleteUser(loginSystem);
                                break;
                            case 4:
                                System.out.println("Returning to Main Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }

                        if (userChoice == 4) break; // Exit Manage Users Menu
                    }
                    break;

                case 2: // Manage Movies
                    admin.MovieList();
                    break;

                case 3:
                    while (true) {
                        System.out.println("------------Manage User View Stats------------");
                        System.out.println("[1] List All Users");
                        System.out.println("[2] View User Statistics");
                        System.out.println("[3] Return to Main Menu");
                        System.out.print("Enter your choice: ");

                        int statsChoice = scanner.nextInt();

                        switch (statsChoice) {
                            case 1:
                                admin.listAllUsers(loginSystem.users);
                                break;
                            case 2:
                                for (User user : loginSystem.users) {
                                    admin.viewUserStats(user);
                                }
                                break;
                            case 3:
                                System.out.println("Returning to Main Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }

                        if (statsChoice == 3) break;
                    }
                    break;

                case 4: // Log Out
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
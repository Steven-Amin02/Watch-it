package Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin extends Person {
    public static final Scanner in=new Scanner(System.in);
    private ArrayList<Movie> movielist=new ArrayList<Movie>();
    private String id;
    Director director;
    File file = new File("Admin.txt");
    public Admin(String username, String password, String email, String fname, String lname) {
        super(username, password, email, fname, lname);
        this.id = IDGenerator.generateID('A'); // Generate unique admin ID
    }

    public void setId(String id) {
        this.id = id;
    }

    public Admin() {

    }

    @Override
    public void setEmail(String email) {
        // Use a specific regex for Admin email validation
        validateEmail(email, "^[\\w.-]+@watchit\\.admin\\.com$");
    }

    public String getId() {
        return id;
    }
    public void MovieList() throws IOException {
        System.out.println("------------Movie------------");
        System.out.println("[1]Add Movie \n [2]Update Movie Details \n [3]Delete Movie");
        int choice=0;
        choice=in.nextInt();
        switch (choice){
            case 1:
                addMovie();
                break;
            case 2:
                updateMovieDetails();
                break;
            case 3:
                removeMovie();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option from the list.");

        }
    }
    public void addMovie() throws IOException {
        Movie movie = new Movie();
        System.out.println("Enter Movie Title: ");
        movie.setTitle(in.next());
        System.out.println("Enter Movie Date");
        movie.setMovieDate(LocalDate.parse(in.next()));
        System.out.println("Enter Movie Duration");
        movie.setDuration(in.nextFloat());
        System.out.println("Enter Movie imdb Score");
        movie.setImdbScore(in.nextFloat());
        System.out.println("Enter Movie Genre");
        movie.setGenre(in.next());
        System.out.println("Enter Movie Language");
        movie.addLanguage(in.next());
        movie.setMovieId();
        // Ensure Director is initialized
        if (movie.Director == null) {
            movie.Director = new Director();
        }

        // System.out.println("Rate");
        // movie.setRate(in.nextFloat());
        System.out.println("Enter Director First Name");
        movie.Director.setFirstName(in.next());
        System.out.println("Enter Director Last Name");
        movie.Director.setLastName(in.next());
        System.out.println("Enter Director Nationality");
        movie.Director.setNationality(in.next());
        System.out.println("Enter Director Age ");
        movie.Director.setAge(in.nextInt());
        System.out.println("Enter Director Date Of Birth");
        movie.Director.setDateOfBirth(in.next());
        System.out.println("Enter Director Gender");
        movie.Director.setGender(in.next());
        System.out.println("Enter Director Social Media Links");
        movie.Director.setSocialMedialinks(in.next());
        //  movie.addCast();
        System.out.println("Enter Movie Budget");
        movie.setBudget(in.nextFloat());
        System.out.println("Enter Movie Revenue");
        movie.setRevenue(in.nextFloat());
        System.out.println("Enter Movie Poster");
        movie.setPoster(in.next());
        System.out.println("Enter Movie Country");
        movie.setCountry(in.next());

        if (movie == null) {
            System.out.println("Cannot add an empty movie. Please enter a movie.");
            return;
        }

        if (!movielist.contains(movie)) {
            movielist.add(movie);
            System.out.println("Movie added successfully.");
            Movie.countMovie++;
        } else {
            System.out.println("Movie already exists.");
        }
    }
    public void removeMovie() {
        String moviename;
        System.out.println("Enter Movie Name:");
        moviename=in.next();
        if(moviename==null||moviename.isEmpty()){
            System.out.println("Invalid movie Name ");
            return;
        }
        boolean removed=movielist.removeIf(movie -> movie.getMovieId().equals(moviename));
        if(removed){
            System.out.println("Movie removed successfully.");
        }
        else{
            System.out.println("Movie not found.");
        }
    }
    public void updateMovieDetails() {
        String moviename;
        System.out.print("Enter Movie Name :");
        moviename=in.next();
        for (Movie movie : movielist) {
            if (movie.getMovieId().equals(moviename)) {
                boolean updating = true;
                while (updating) {
                    System.out.println("Choose what to update:");
                    System.out.println("[1] Title");
                    System.out.println("[2] Release Date");
                    System.out.println("[3] Duration");
                    System.out.println("[4] IMDB Score");
                    System.out.println("[5] Genres");
                    System.out.println("[6] Languages");
                    System.out.println("[7] Budget");
                    System.out.println("[8] Revenue");
                    System.out.println("[9] Poster");
                    System.out.println("[10] Movie Rate");
                    System.out.println("[11] Country");
                    System.out.println("[0] Exit");

                    int choice = in.nextInt();
                    in.nextLine();  // consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter new title: ");
                            String title = in.nextLine();
                            movie.setTitle(title);
                            break;
                        case 2:
                            System.out.print("Enter new release date (yyyy-MM-dd): ");
                            String dateStr = in.nextLine();
                            try {
                                LocalDate date = LocalDate.parse(dateStr);
                                movie.setMovieDate(date);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter new duration in minutes: ");
                            float duration = in.nextFloat();
                            movie.setDuration(duration);
                            break;
                        case 4:
                            System.out.print("Enter new IMDB score: ");
                            float score = in.nextFloat();
                            movie.setImdbScore(score);
                            break;
                        case 5:
                            // modifiy genre
                            break;
                        case 6:
                            System.out.print("Enter new language: ");
                            String language = in.nextLine();
                            movie.addLanguage(language);
                            break;
                        case 7:
                            System.out.print("Enter new budget: ");
                            float budget = in.nextFloat();
                            movie.setBudget(budget);
                            break;
                        case 8:
                            System.out.print("Enter new revenue: ");
                            float revenue = in.nextFloat();
                            movie.setRevenue(revenue);
                            break;
                        case 9:
                            System.out.print("Enter new poster URL: ");
                            String poster = in.nextLine();
                            movie.setPoster(poster);
                            break;
                        case 10:
                            System.out.print("Enter new movie rate: ");
                            float rate = in.nextFloat();
                            movie.setImdbScore(rate);
                            break;
                        case 11:
                            System.out.print("Enter new country: ");
                            String country = in.nextLine();
                            movie.setCountry(country);
                            break;
                        case 0:
                            updating = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
                System.out.println("Movie details updated successfully!");
                return;
            }
        }
        System.out.println("Movie with ID " + moviename + " not found.");
    }


    public void viewUserStats(User user) {

    }
    public void listAllUsers(ArrayList<User> users) {
        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return; // Exit the method if the list is empty or null
        }

        System.out.println("Listing all users:");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-10s %-15s %-15s %-15s %-20s\n", "ID", "First Name", "Last Name", "Username", "Email");
        System.out.println("------------------------------------------------------------");

        for (User user : users) {
            System.out.printf(
                    "%-10s %-15s %-15s %-15s %-20s\n",
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getEmail()
            );
        }

        System.out.println("------------------------------------------------------------");
        System.out.println("Total users: " + users.size());
    }

    public void addUser(Login loginSystem) throws Exception {
        loginSystem.signUp();
    }
    public void modifyUser(Login loginSystem) {
        String id,username;
        System.out.println("----------Searching For User----------");
        System.out.println("[1]User ID \n [2]Username");
        System.out.println("Enter Your Choice: ");
        int choice1 = 0;
        try{
            choice1 = in.nextInt();
        }catch (Exception e){
            System.out.println("Invalid choice please enter a number between 1 and 2");
        } User user = null;
        switch (choice1){
            case 1:
                System.out.print("Please enter the user ID that you want to modify: ");
                id = in.next();
                user = loginSystem.getUserById(id);
                if (user == null) {
                    System.out.println("User with ID " + id + " not found.");
                    return;
                }
                break;
            case 2:
                System.out.print("Please enter the user username that you want to modify: ");
                username = in.next();
                user = loginSystem.getUserByUsername(username);
                if (user == null) {
                    System.out.println("User with Username: " + username + " not found.");
                    return;}
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option from the list.");
                break;
        }
        if(user !=null){
            System.out.println("--------User Details--------");
            System.out.println("First Name :"+user.getFirstName()+"\n Last Name :"+user.getLastName()+"\n Username :"+user.getUsername()+"\n Email :"+user.getEmail()+"\n Password :"+user.getPassword()+"\n");
            System.out.println("--------User Details--------");
            System.out.println("[1]Modify First Name \n [2]Modify Last Name\n[3]Modify Username \n [4]Modify Email \n [5]Modify Password \n ");
            System.out.print("Enter Your Choice: ");
            int choice2 = 0;
            try {
                choice2 = in.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice please enter a number between 1 and 5");
            }
            switch (choice2) {
                case 1:
                    System.out.print("Enter New First Name:");
                    user.setFirstName(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 2:
                    System.out.println("Enter New Last Name:");
                    user.setLastName(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 3:
                    System.out.println("Enter New Username:");
                    user.setUsername(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 4:
                    System.out.println("Enter New Email:");
                    user.setEmail(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 5:
                    System.out.println("Enter New Password:");
                    user.setPassword(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option from the list.");

            }
        }
    }
    public void deleteUser(Login loginSystem) {

        try {
            System.out.println("----------Delete User----------");
            String username;
            username=in.next();
            loginSystem.deleteUser(username);
            System.out.println("User " + username + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    // manage files
    public void writeToFile(ArrayList <Admin> admins) throws IOException {
        // Use PrintWriter to write to the file in append mode
        try (PrintWriter writer = new PrintWriter(new FileWriter(file , false))) {
            String delimiter = ",";
            // Construct a CSV-like line for this User object
            for(Admin admin :admins) {
                StringBuilder adminLine = new StringBuilder();
                adminLine.append(admin.getId()).append(delimiter);
                adminLine.append(admin.getFirstName()).append(delimiter);
                adminLine.append(admin.getLastName()).append(delimiter);
                adminLine.append(admin.getUsername()).append(delimiter);
                adminLine.append(admin.getPassword()).append(delimiter);
                adminLine.append(admin.getEmail());
                writer.println(adminLine.toString());
            }
        }
    }
    public void FileReader(ArrayList<Admin> admins) throws IOException {
        File file = new File("admin.txt"); // Adjust the path to your file
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        Scanner scan = new Scanner(file);
        scan.useDelimiter("\n"); // Use newline as delimiter to read each line
        while (scan.hasNext()) {
            String line = scan.next().trim(); // Read a full line
            String[] data = line.split(","); // Split the line by commas

            if (data.length == 6) { // Ensure the line has all required fields
                Admin admin = new Admin();
                admin.setId(data[0].trim());
                admin.setFirstName(data[1].trim());
                admin.setLastName(data[2].trim());
                admin.setUsername(data[3].trim());
                admin.setPassword(data[4].trim());
                admin.setEmail(data[5].trim());
                admins.add(admin); // Add the user to the list
            } else {
                System.out.println("Skipping invalid line: " + line);
            }
        }
        scan.close(); // Close the scanner
        System.out.println("File loaded successfully! " + admins.size() + " admin found.");
    }
}

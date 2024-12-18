package Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class User extends Person {
    protected static User instance;
    protected ArrayList <Movie> toWatchMovies = new ArrayList<>(); // Micky
    protected Subscription subscription = new Subscription("",new Date());
    private String id;
    File file = new File("User.txt");

    public User(String username, String password, String email, String fname, String lname) throws Exception { // remove throws Exeption
        super(username, password, email,fname,lname);
        this.id = IDGenerator.generateID('U'); // Generate unique user ID
        this.toWatchMovies = new ArrayList<>();
        subscription.getPlans().setPlanName("Non-plan");

    }


    public User(){
        subscription.getPlans().setPlanName("Non-plan");
    };
    @Override
    public void setEmail(String email) {
        if (!email.matches("^[\\w.-]+@watchit\\.com$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    // Getters and setters for watchedMovies and toWatchMovies
    public ArrayList<Movie> getMoviesByUser(String userId, ArrayList<UserWatchRecord> watchRecords, ArrayList<Movie> allMovies) {
        // Create a list to store the movies watched by the user
        ArrayList<Movie> moviesWatchedByUser = new ArrayList<>();

        // Iterate through the watch records to find records for the specified user
        for (UserWatchRecord record : watchRecords) {
            if (record.getUserId().equals(userId)) {
                // Find the movie in the list of all movies
                for (Movie movie : allMovies) {
                    if (movie.getTitle().equals(record.getMovie())) {
                        moviesWatchedByUser.add(movie);
                        break;
                    }
                }
            }
        }
        return moviesWatchedByUser; // Return the list of movies watched by the user
    }

/*    public ArrayList<Movie> getWatchedMovies() {
        if (watchedMovies == null) {
            return new ArrayList<>(); // Return an empty list as the default value
        }
        return watchedMovies;
    }*/

    public ArrayList<Movie> getToWatchMovies() {
        if (toWatchMovies == null) {
            return new ArrayList<>(); // Return an empty list as the default value
        }
        return toWatchMovies;
    }

    public void addToWatchMovie(Movie movie) { //wish list
        toWatchMovies.add(movie);
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void writeToFile(ArrayList <User> users) throws IOException {
        // Use PrintWriter to write to the file in append mode
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, false))) {
            String delimiter = ",";
            // Construct a CSV-like line for this User object

            for(User user : users) {
                StringBuilder userLine = new StringBuilder();
                userLine.append(user.getId()).append(delimiter);
                userLine.append(user.getFirstName()).append(delimiter);
                userLine.append(user.getLastName()).append(delimiter);
                userLine.append(user.getUsername()).append(delimiter);
                userLine.append(user.getPassword()).append(delimiter);
                userLine.append(user.getEmail()).append(delimiter);
                userLine.append(user.subscription.getPlans().getPlanName()).append(delimiter);
                userLine.append(user.subscription.getMoviesWatched());
                writer.println(userLine.toString());
            }
        }
    }
    public void FileReader(ArrayList<User> users) throws IOException {
        File file = new File("User.txt"); // Adjust the path to your file
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        Scanner scan = new Scanner(file);
        scan.useDelimiter("\n"); // Use newline as delimiter to read each line
        while (scan.hasNext()) {
            String line = scan.next().trim(); // Read a full line
            String[] data = line.split(","); // Split the line by commas

            if (data.length == 8) { // Ensure the line has all required fields
                User user = new User();
                user.setId(data[0].trim());
                user.setFirstName(data[1].trim());
                user.setLastName(data[2].trim());
                user.setUsername(data[3].trim());
                user.setPassword(data[4].trim());
                user.setEmail(data[5].trim());
                user.getSubscription().getPlans().setPlanName(data[6].trim());
                user.getSubscription().setMoviesWatched(Integer.parseInt(data[7].trim()));
                users.add(user); // Add the user to the list
            } else {
                System.out.println("Skipping invalid line: " + line);
            }
        }
        scan.close(); // Close the scanner
        System.out.println("File loaded successfully! " + users.size() + " users found.");
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", id='" + id + '\''+
                super.getUsername() +
                super.getPassword() +
                super.getFirstName()+
                super.getLastName() +
                ", toWatchMovies=" + toWatchMovies +
                ", subscription=" + subscription +
                '}';
    }



}

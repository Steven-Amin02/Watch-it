import java.util.ArrayList;

public class User extends Person {
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> toWatchMovies;
    private Subscription subscription;
    private String id;

    public User(String username, String password, String email, String fname, String lname) throws Exception {
        super(username, password, email,fname,lname);
        this.id = IDGenerator.generateID('U'); // Generate unique user ID
        this.watchedMovies = new ArrayList<>();
        this.toWatchMovies = new ArrayList<>();

    }

    @Override
    public void setEmail(String email) {
        if (!email.matches("^[\\w.-]+@watchit\\.com$")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
      this.email = email;
    }

    // Getters and setters for watchedMovies and toWatchMovies
    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public ArrayList<Movie> getToWatchMovies() {
        return toWatchMovies;
    }

    public void addWatchedMovie(Movie movie) {
        watchedMovies.add(movie);
    }

    public void addToWatchMovie(Movie movie) {
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
}

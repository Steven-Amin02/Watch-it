package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Movie {
    private static final Scanner input = new Scanner(System.in);
    private String movieId;
    private String title;
    private LocalDate movieDate;  // LocalDate.of(2010, 7, 16) --> will be used when creating object
    private float duration;
    private double imdbScore;
    protected ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> languages = new ArrayList<>();
    //The Director
    private ArrayList<Cast> castList; // List of cast from class cast
    private float budget;
    private float revenue;
    private String poster;
    private String country;
    static int countMovie = 0;

    // Constructor with parameters
    public Movie(String title, LocalDate movieDate, float duration, float Revenue, float budget, String poster, String country, double imdbScore, String genre) {
        countMovie++;
        if (genre == null || genre.trim().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }
        setTitle(title);
        setMovieDate(movieDate);
        setDuration(duration);
        setRevenue(revenue);
        setBudget(budget);
        setPoster(poster);
        setCountry(country);
        setImdbScore(imdbScore);
        genres.add(genre);
        this.castList = new ArrayList<>();
        setMovieId();
    }


    // Default constructor
    public Movie(){}

    // Method to calculate profitability
    public float calcProfitability() {
        return this.revenue - this.budget;
    }

    // Method to modify genres (add, delete)
    void modifyGenre() {
        String name;
        while (true) {
            System.out.println("1. Add Genre");
            System.out.println("2. Delete Genre");
            System.out.println("3. Exit");

            int choice = 0;
            try {
                choice = input.nextInt();
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                input.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter genre name:");
                    name = input.nextLine();
                    genres.add(name);  // Add new genre
                    break;
                case 2:
                    System.out.println("Enter genre name to delete:");
                    String searchName = input.next();
                    genres.removeIf(genreItem -> genreItem.equals(searchName));  // Delete genre
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;  // Exit the method
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    void addLanguage(String name){
        languages.add(name);
    }
    // ----> casts
    // Add a Cast member to the movie
    public void addCast(Cast cast) {
        castList.add(cast);
    }
    // Display all Cast members
    public void displayCast() {
        System.out.println("Cast Members:");
        for (Cast cast : castList) {
            cast.displayDetails();
            System.out.println("--------------------");
        }
    }


    // All Setter and Getter
    public String getMovieId() {
        return movieId;
    }

    private void setMovieId() {
        this.movieId = genres.get(0).charAt(0) + Integer.toString(countMovie);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        this.title = title;
    }

    public LocalDate getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(LocalDate movieDate) {
        if (movieDate == null) {
            throw new IllegalArgumentException("Movie date cannot be null.");
        }
        if (movieDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Movie date cannot be in the future.");
        }
        this.movieDate = movieDate;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0.");
        }
        this.duration = duration;
    }

    // Set imdbScore
    public void setImdbScore(double imdbScore){
        if (imdbScore < 0 || imdbScore > 10) {
            throw new IllegalArgumentException("Movie rate must be between 0 and 10.");
        }
         this.imdbScore = imdbScore;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        if (budget <= 0) {
            throw new IllegalArgumentException("Budget must be greater than 0.");
        }
        this.budget = budget;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        if (revenue < 0) {
            throw new IllegalArgumentException("Revenue cannot be negative.");
        }
        this.revenue = revenue;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        if (poster == null || poster.trim().isEmpty()) {
            throw new IllegalArgumentException("Poster cannot be null or empty.");
        }
        this.poster = poster;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country == null || country.trim().isEmpty()) {
            throw new IllegalArgumentException("Country cannot be null or empty.");
        }
        this.country = country;
    }

    // Print the last movies from 10 days ago
    public static ArrayList<Movie> getMoviesReleasedLast10Days(ArrayList<Movie> movies) {
        LocalDate currentDate = LocalDate.now();
        ArrayList<Movie> recentMovies = new ArrayList<>();

        for (Movie movie : movies) {
            if (movie.getMovieDate().isAfter(currentDate.minusDays(10))) {
                recentMovies.add(movie);
            }
        }
        return recentMovies;
    }
    // Method to display movie details
    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", title='" + title + '\'' +
                ", movieDate=" + movieDate +
                ", duration=" + duration +
                ", genres=" + genres +
                ", budget=" + budget +
                ", Revenue=" + revenue +
                ", poster='" + poster + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

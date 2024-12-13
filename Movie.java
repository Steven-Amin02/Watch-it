package Model;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
    private float imdbScore;
    private float rate;
    private int noOfviews;
    private int noOfikes;
    protected Director Director ;
    private String genre;
    private ArrayList<String> languages = new ArrayList<>();
    //The Director
    protected ArrayList<Cast> castList; // List of cast from class cast
    private float budget;
    private float revenue;
    private String poster;
    private String country;
    static int countMovie = 0;
    File file = new File("Movie.txt");


    // Constructor with parameters
    public Movie(String title, LocalDate movieDate, float duration, float Revenue, float budget, String poster, String country, float imdbScore, String genre) {
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
        this.genre = genre;
        this.castList = new ArrayList<>();
        this.Director = new Director(); // Initialize Director
        setMovieId();
    }


    // Default constructor
    public Movie(){
        this.genre = "Unknown";  // Assign default value
        this.Director = new Director(); // Initialize Director

    }

    // Method to calculate profitability
    public float calcProfitability() {
        return this.budget - this.revenue ;
    }

    // Method to modify genres (add, delete)
//    void modifyGenre() {
//        String name;
//        while (true) {
//            System.out.println("1. Add Genre");
//            System.out.println("2. Delete Genre");
//            System.out.println("3. Exit");
//
//            int choice = 0;
//            try {
//                choice = input.nextInt();
//                input.nextLine();
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input! Please enter a valid number.");
//                input.nextLine();
//                continue;
//            }
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Enter genre name:");
//                    name = input.nextLine();
//                    genres.add(name);  // Add new genre
//                    break;
//                case 2:
//                    System.out.println("Enter genre name to delete:");
//                    String searchName = input.next();
//                    genres.removeIf(genreItem -> genreItem.equals(searchName));  // Delete genre
//                    break;
//                case 3:
//                    System.out.println("Exiting...");
//                    return;  // Exit the method
//                default:
//                    System.out.println("Invalid choice. Try again.");
//            }
//        }
//    }

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

    public ArrayList<Cast> getCastList() {
        return castList;
    }

    public void setCastList(ArrayList<Cast> castList) {
        this.castList = castList;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId() {
        if (genre == null || genre.isEmpty()) {
            throw new IllegalStateException("Genre must be set before setting the movie ID.");
        }
        this.movieId = genre.charAt(0) + Integer.toString(countMovie);
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
    public void setImdbScore(float imdbScore){
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
    public int getNoOfviews() {
        return noOfviews;
    }
    public void setNoOfviews(int noOfviews) {
        this.noOfviews = noOfviews;
    }
    public int getNoOfikes() {
        return noOfikes;
    }
    public void setNoOfikes(int noOfikes) {
        this.noOfikes = noOfikes;
    }
    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Director getDirector() {
        if (this.Director == null) {
            this.Director = new Director();
        }
        return this.Director;
    }

    public void setDirector(Director director) {
        this.Director = director;
    }
    // Method to display movie details
    public static void displayAll(ArrayList<Movie> movies) {
        for (Movie movie : movies) {
            System.out.println("-----------------------------------------------");
            System.out.println("Movie Details:");
            System.out.printf("%-18s: %s%n", "Movie ID", movie.getMovieId());
            System.out.printf("%-18s: %s%n", "Title", movie.getTitle());
            System.out.printf("%-18s: %s%n", "Release Date", movie.getMovieDate());
            System.out.printf("%-18s: %s min%n", "Duration", movie.getDuration());
            System.out.printf("%-18s: %s%n", "Genre", movie.getGenre());
            System.out.printf("%-18s: %.1f%n", "IMDB Score", movie.getImdbScore());
            System.out.printf("%-18s: %.1f%n", "Rate", movie.getRate());
            System.out.printf("%-18s: %d%n", "Number of Views", movie.getNoOfviews());
            System.out.printf("%-18s: %d%n", "Number of Likes", movie.getNoOfikes());
            System.out.printf("%-18s: $%.2f%n", "Budget", movie.getBudget());
            System.out.printf("%-18s: $%.2f%n", "Revenue", movie.getRevenue());
            System.out.printf("%-18s: $%.2f%n", "Profitability", movie.calcProfitability());
            System.out.printf("%-18s: %s%n", "Country", movie.getCountry());
            System.out.printf("%-18s: %s%n", "Poster", movie.getPoster());

            // Display Director details
            Director director = movie.getDirector();
            System.out.println("Director Details:");
            if (director != null) {
                System.out.printf("   %-15s: %s%n", "First Name", director.getFirstName());
                System.out.printf("   %-15s: %s%n", "Last Name", director.getLastName());
                System.out.printf("   %-15s: %s%n", "Nationality", director.getNationality());
                System.out.printf("   %-15s: %d%n", "Age", director.getAge());
                System.out.printf("   %-15s: %s%n", "Date of Birth", director.getDateOfBirth());
                System.out.printf("   %-15s: %s%n", "Gender", director.getGender());
                System.out.printf("   %-15s: %s%n", "Social Links", director.getSocialMedialinks());
            } else {
                System.out.println("   Not available");
            }

            // Display languages
            System.out.printf("%-18s: %s%n", "Languages", String.join(", ", movie.languages));

            // Display cast details
            System.out.println("Cast List:");
            if (movie.getCastList() != null && !movie.getCastList().isEmpty()) {
                for (Cast cast : movie.getCastList()) {
                    cast.displayDetails();
                }
            } else {
                System.out.println("   No cast members available.");
            }
            System.out.println("-----------------------------------------------");
        }
    }
    public void writeToFile(Movie movie) throws IOException {
        // Use PrintWriter to write to the file in append mode
        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            String delimiter = ",";
            StringBuilder movieLine = new StringBuilder();

            movieLine.append(movie.getMovieId()).append(delimiter);
            movieLine.append(movie.getTitle()).append(delimiter);
            movieLine.append(movie.getDuration()).append(delimiter);
            movieLine.append(movie.getMovieDate()).append(delimiter);
            movieLine.append(movie.getBudget()).append(delimiter);
            movieLine.append(movie.getCountry()).append(delimiter);
            movieLine.append(movie.getGenre()).append(delimiter);
            movieLine.append(movie.getImdbScore()).append(delimiter);
            movieLine.append(movie.getRate()).append(delimiter);

            // Add Director details (if not null)
            if (movie.getDirector() != null) {
                movieLine.append(movie.getDirector().getFirstName()).append(delimiter);
                movieLine.append(movie.getDirector().getLastName()).append(delimiter);
                movieLine.append(movie.getDirector().getNationality()).append(delimiter);
            } else {
                // If no director, add placeholders
                movieLine.append("Unknown").append(delimiter);  // First Name
                movieLine.append("Unknown").append(delimiter);  // Last Name
                movieLine.append("Unknown").append(delimiter);  // Nationality
            }
            writer.println(movieLine.toString());
        }
    }
    public void FileReader(ArrayList<Movie> movies) throws IOException {
        File file = new File("movie.txt"); // Adjust the path to your file
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        Scanner scan = new Scanner(file);
        scan.useDelimiter("\n"); // Use newline as delimiter to read each line
        while (scan.hasNext()) {
            String line = scan.next().trim(); // Read a full line
            String[] data = line.split(","); // Split the line by commas

            if (data.length >= 12) { // Ensure the line has all required fields
                Movie movie = new Movie();
                movie.movieId = data[0].trim();
                movie.setTitle(data[1].trim());
                movie.setDuration(Float.parseFloat(data[2].trim()));
                movie.setMovieDate(LocalDate.parse(data[3].trim()));
                movie.setBudget(Float.parseFloat(data[4].trim()));
                movie.setCountry(data[5].trim());
                movie.setGenre(data[6].trim());
                movie.setImdbScore(Float.parseFloat(data[7].trim()));
                movie.setRate(Float.parseFloat(data[8].trim()));

                // Read Director details
                Director director = new Director();
                director.setFirstName(data[9].trim()); // First Name
                director.setLastName(data[10].trim()); // Last Name
                director.setNationality(data[11].trim()); // Nationality
                movie.setDirector(director);

                movies.add(movie); // Add the movie to the list
            } else {
                System.out.println("Skipping invalid line: " + line);
            }
        }
        scan.close(); // Close the scanner
        System.out.println("File loaded successfully! " + movies.size() + " movies found.");
    }
    static public void movieInfo(Movie movie){
            System.out.println("-----------------------------------------------");
            System.out.println("Movie{" +
                    "movieId='" + movie.movieId + '\'' +
                    ", title='" + movie.title + '\'' +
                    ", movieDate=" + movie.movieDate +
                    ", duration=" + movie.duration +
                    ", imdbScore=" + movie.imdbScore +
                    ", rate=" + movie.rate +
                    ", noOfviews=" + movie.noOfviews +
                    ", noOfikes=" + movie.noOfikes +
                    ", Director=" + movie.Director +
                    ", genre='" + movie.genre + '\'' +
                    ", languages=" + movie.languages +
                    ", castList=" + movie.castList +
                    ", budget=" + movie.budget +
                    ", revenue=" + movie.revenue +
                    ", poster='" + movie.poster + '\'' +
                    ", country='" + movie.country + '\'' +
                    '}');
            System.out.println("-----------------------------------------------");

    }
}
package Model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserWatchRecord {

    private String userId;
    private String movie;
    private LocalDate dateOfWatching;
    private Integer rating; // Optional rating (can be null)
    protected ArrayList <Movie> watchedMovies = new ArrayList<>(); // Micky
    File file=new File("UserWatchRecord.txt");
    // Constructor
    public UserWatchRecord(String userId, String movie, LocalDate dateOfWatching, Integer rating) {
        this.userId = userId;
        this.movie = movie;
        this.dateOfWatching = dateOfWatching;
        this.rating = rating;
        this.watchedMovies = new ArrayList<>();
    }

    // Overloaded constructor for when rating is not provided
    public UserWatchRecord(String userId, String movie, LocalDate dateOfWatching) {
        this(userId, movie, dateOfWatching, null);
    }

    // Default constructor
    public UserWatchRecord() {}

    // Getters and Setters
    public void setWatchedMovies(ArrayList<Movie> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    public void addWatchedMovie(Movie movie) {  // history
        watchedMovies.add(movie);
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public LocalDate getDateOfWatching() {
        return dateOfWatching;
    }

    public void setDateOfWatching(LocalDate dateOfWatching) {
        this.dateOfWatching = dateOfWatching;
    }

    public void setDateOfWatching(String date) {
        try {
            this.dateOfWatching = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        if (rating == null) {
            this.rating = null;
            return;
        }
        if (!(rating instanceof Integer)) {
            throw new IllegalArgumentException("Rating must be a valid number between 1 and 5.");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.rating = rating;
    }



    @Override
    public String toString() {
        return "UserWatchRecord{" +
                "userId='" + userId + '\'' +
                ", movie='" + movie + '\'' +
                ", dateOfWatching=" + dateOfWatching +
                ", rating=" + (rating != null ? rating : "Not Rated") +
                '}';
    }
    public void writeToFile(ArrayList <UserWatchRecord> watch) throws IOException {
        // Use PrintWriter to write to the file in append mode
        try (PrintWriter writer = new PrintWriter(new FileWriter(file , false))) {
            String delimiter = ",";
            // Construct a CSV-like line for this User object
            for(UserWatchRecord watched :watch) {
                StringBuilder watchLine = new StringBuilder();
                watchLine.append(watched.getUserId()).append(delimiter);
                watchLine.append(watched.getMovie()).append(delimiter);
                watchLine.append(watched.getDateOfWatching()).append(delimiter);
                watchLine.append(watched.getRating());
                writer.println(watchLine.toString());
            }
        }
    }
    public void FileReader(ArrayList<UserWatchRecord> watch) throws IOException {
        File file = new File("UserWatchRecord.txt"); // Adjust the path to your file
        if (!file.exists()) {
            throw new IOException("File not found: " + file.getAbsolutePath());
        }

        Scanner scan = new Scanner(file);
        scan.useDelimiter("\n"); // Use newline as delimiter to read each line
        while (scan.hasNext()) {
            String line = scan.next().trim(); // Read a full line
            String[] data = line.split(","); // Split the line by commas

            if (data.length == 4) { // Ensure the line has all required fields
                UserWatchRecord watched = new UserWatchRecord();
                watched.setUserId(data[0].trim());
                watched.setMovie(data[1].trim());
                watched.setDateOfWatching(data[2].trim());
                watched.setRating(Integer.parseInt(data[3].trim()));

                watch.add(watched);
            } else {
                System.out.println("Skipping invalid line: " + line);
            }
        }
        scan.close(); // Close the scanner
        System.out.println("File loaded successfully! " + watch.size() + " records found.");
    }
}

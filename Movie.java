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
    private ArrayList<String> genres = new ArrayList<>();
    private ArrayList<String> languages = new ArrayList<>();
    // List of Director from class Director
    private ArrayList<Cast> castList = new ArrayList<>(); // List of cast from class cast
    private float budget;
    private float Revenue;
    private String poster;
    private float movieRate;
    private String country;

    // Constructor with parameters
    public Movie(String movieId, String title, LocalDate movieDate, float duration, float Revenue, float budget, String poster, String country, float movieRate) {
        this.movieId = movieId;
        this.title = title;
        this.movieDate = movieDate;
        this.duration = duration;
        this.Revenue = Revenue;
        this.budget = budget;
        this.poster = poster;
        this.country = country;
        this.movieRate = movieRate;
        modifyGenre();
    }

    // Default constructor
    public Movie(){}
    // Method to calculate profitability
    public float calcProfitability() {
        return this.Revenue - this.budget;
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

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(LocalDate movieDate) {
        this.movieDate = movieDate;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    // Set imdbScore
    public void setImdbScore(double imdbScore){
        if(imdbScore >= 0 && imdbScore <= 10)
            this.imdbScore = imdbScore;
        else
            System.out.println("Invalid Score");
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public float getRevenue() {
        return Revenue;
    }

    public void setRevenue(float revenue) {
        Revenue = revenue;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getMovieRate() {
        return movieRate;
    }

    public void setMovieRate(float movieRate) {
        this.movieRate = movieRate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
                ", Revenue=" + Revenue +
                ", poster='" + poster + '\'' +
                ", movieRate=" + movieRate +
                ", country='" + country + '\'' +
                '}';
    }
}

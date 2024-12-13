package Model;
import java.time.LocalDate;
import java.util.ArrayList;


public class Series {
    private String seriesId;
    private int numOfMovies = 0;
    private String seriesName;
    ArrayList<Movie> movies  =  new ArrayList<Movie>() ; // Create an ArrayList object
    private String Description;
    private LocalDate SeriesDate;

    public Series(String seriesId, int numOfMovies, String seriesName, String Description, LocalDate SeriesDate) {
        this.seriesId = seriesId;
        this.numOfMovies = numOfMovies;
        this.seriesName = seriesName;
        this.Description = Description;
        this.SeriesDate = SeriesDate;
    }

    public Series() {
    }



}

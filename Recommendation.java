package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
//import java.util.Exception;
import java.util.InputMismatchException;

public class Recommendation extends Movie{

    Scanner input = new Scanner(System.in);

//////////////SORT/////////////////SORT/////////SORT///////////////SORT/////////////

    public void sortRatingAsc(ArrayList<Movie> allMovies)
    {
        Collections.sort(allMovies , (m1,m2) -> Float.compare(m1.getRate(), m2.getRate()));
        for(int i=0 ; i<allMovies.size() ; i++)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() +
                    "Movie rate: " + allMovies.get(i).getRate());
        }
    }

    public void sortViewsAsc(ArrayList<Movie> allMovies)
    {
        Collections.sort(allMovies , (m1,m2) -> Integer.compare(m1.getNoOfviews(), m2.getNoOfviews()));
        for(int i=0 ; i<allMovies.size() ; i++)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() +
                    "Movie rate: " + allMovies.get(i).getNoOfviews());
        }
    }

/*    public void sortLikesAsc(ArrayList<Movie> allMovies)
    {
        Collections.sort(allMovies , (m1,m2) -> Integer.compare(m1.getNoOfikes(), m2.getNoOfikes()));
        for(int i=0 ; i<allMovies.size() ; i++)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() +
                    "Movie rate: " + allMovies.get(i).getNoOfikes());
        }
    }*/

    public void sortRatingDesc(ArrayList<Movie> allMovies)
    {
        //Collections.sort(allMovies , Collections.reverseOrder(Comparator.comparingDouble(Movie::getRate)));
        Collections.sort(allMovies , (m1,m2) -> Float.compare(m1.getRate(), m2.getRate()));
        for(int i=allMovies.size() ; i<=0 ; i--)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() +
                    "Movie rate: " + allMovies.get(i).getRate());
        }
    }

    public void sortViewsDesc(ArrayList<Movie> allMovies)
    {
        //Collections.sort(allMovies , Collections.reverseOrder(Comparator.comparingInt(Movie::getNoOfviews)));
        Collections.sort(allMovies , (m1,m2) -> Integer.compare(m1.getNoOfviews(), m2.getNoOfviews()));
        for(int i=allMovies.size() ; i<=0 ; i--)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() +
                    "Movie rate: " + allMovies.get(i).getNoOfviews());
        }
    }


/*    public void sortLikesDesc(ArrayList<Movie> allMovies)
    {
        //Collections.sort(allMovies , Collections.reverseOrder(Comparator.comparingInt(Movie::getNoOfikes)));
        Collections.sort(allMovies , (m1,m2) -> Integer.compare(m1.getNoOfikes(), m2.getNoOfikes()));
        for(int i=allMovies.size() ; i<=0 ; i--)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() +
                    "Movie rate: " + allMovies.get(i).getNoOfikes());
        }
    }*/


    public void Sort(ArrayList<Movie> allMovies)
    {
        int choice1 , choice2;


        System.out.println("Sort movies");
        System.out.println("(1) Sort by Rating");
        System.out.println("(2) Sort by Views");
        System.out.println("(3) Sort by Likes");
        System.out.println("Enter your choice");
        try{
            choice1 = input.nextInt();

            switch (choice1){
                case 1:
                    System.out.println("(1) High to low");
                    System.out.println("(2) Low to high");
                    System.out.println("Enter your choice");
                    choice2 = input.nextInt();
                    try{
                        switch(choice2){
                            case 1:
                                sortRatingDesc(allMovies);
                                break;
                            case 2:
                                sortRatingAsc(allMovies);
                                break;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Please enter invalid number");
                    }

                    break;
                case 2:
                    System.out.println("(1) High to low");
                    System.out.println("(2) Low to high");
                    System.out.println("Enter your choice");
                    choice2 = input.nextInt();
                    try{
                        switch(choice2){
                            case 1:
                                sortViewsDesc(allMovies);
                                break;
                            case 2:
                                sortViewsAsc(allMovies);
                                break;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Please enter invalid number");

                    }
                    break;
/*                case 3:
                    System.out.println("(1) High to low");
                    System.out.println("(2) Low to high");
                    System.out.println("Enter your choice");
                    try{
                        choice2 = input.nextInt();
                        switch(choice2){
                            case 1:
                                sortLikesDesc(allMovies);
                                break;
                            case 2:
                                sortLikesAsc(allMovies);
                                break;
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Please enter valid number");
                    }
                    break;*/
            }
        }catch(InputMismatchException e){
            System.out.println("Please enter valid number");
        }
    }

//////////////SEARCH////////////////////////////SEARCH//////////////////////////////////SEARCH/////////////////////


    public static final void checkSearchResult(ArrayList<Movie> searchedResult)
    {
        if(searchedResult.isEmpty())
            System.out.println("No matches found");
        else{
            for(int i=0 ; i<searchedResult.size() ; i++) {
                System.out.println(searchedResult.get(i).getTitle());
                Movie.movieInfo(searchedResult.get(i));
            }
        }
    }

static public Movie getmoviebyname(String name,ArrayList<Movie> allMovies) {
    if (name.isEmpty())
        throw new IllegalArgumentException("Name cannot be empty");
    ArrayList<Movie> searchMovie = new ArrayList<>();
    Movie movies = new Movie();
    for(Movie movie :allMovies){
    if(movie.getTitle().toLowerCase().contains(name.trim().toLowerCase())){
        return movie;
    }
    }
    return null;
}
    public void searchMovieByName(ArrayList<Movie> allMovies)     //search movie by title
    {
        String searchMovie;
        System.out.println("Search : ");
        try{
            searchMovie = input.next();

            if(searchMovie.isBlank()){
                throw new IllegalArgumentException();
            }

            ArrayList<Movie> searchedMovies = new ArrayList<>();

            for(int i=0 ; i<allMovies.size() ; i++)
            {
                if(allMovies.get(i).getTitle().toLowerCase().contains(searchMovie.trim().toLowerCase()))
                {
                    searchedMovies.add(allMovies.get(i));
                }
                Recommendation.checkSearchResult(searchedMovies);
            }
        }catch (IllegalArgumentException e){
            System.out.println("Empty search");
        }catch(Exception e){
            System.out.println("Unexpected error " + e.getMessage());
        }
    }


    public void searchMovieByDirector(ArrayList<Movie> allMovies)         //search by director
    {
        String searchDirector;
        System.out.println("Search : ");

        try{
            searchDirector = input.next();

            if(searchDirector.isBlank())
            {
                throw new IllegalArgumentException();
            }

            ArrayList<Movie> searchedMovies = new ArrayList<>();

            for(int i=0 ; i<allMovies.size() ; i++)
            {
                if(allMovies.get(i).getDirector().getName().toLowerCase().contains(searchDirector.trim().toLowerCase()))
                {
                    searchedMovies.add(allMovies.get(i));
                }
                Recommendation.checkSearchResult(searchedMovies);
            }
        }catch(IllegalArgumentException e){
            System.out.println("Empty search");
        }
        catch(Exception e){
            e.getMessage();
        }
    }


    public void searchMovieByGenre(ArrayList<Movie> allMovies)       //search by genres
    {
        String searchGenre;
        System.out.println("Search : ");

        try{

            searchGenre = input.next();

            if(searchGenre.isBlank()){
                throw new IllegalArgumentException();
            }

            ArrayList<Movie> searchedMovies = new ArrayList<>();

            for(int i=0 ; i<allMovies.size() ; i++)
            {
                if(allMovies.get(i).getGenre().toLowerCase().contains(searchGenre.trim().toLowerCase()))
                {
                    searchedMovies.add(allMovies.get(i));
                }
                Recommendation.checkSearchResult(searchedMovies);
            }
        }catch(IllegalArgumentException e){
            System.out.println("Empty search");
        }
        catch(Exception e){
            e.getMessage();
        }
    }


    public void searchMovieByActor(ArrayList<Movie> allMovies)
    {
        String searchActor;
        System.out.println("Search : ");
        try{
            searchActor = input.next();

            if(searchActor.isBlank()){
                throw new IllegalArgumentException();
            }

            ArrayList<Movie> searchedMovies = new ArrayList<>();

            for(int i=0 ; i<allMovies.size() ; i++)
            {
                for(int j=0 ; j<allMovies.get(i).getCastList().size() ; j++)
                {
                    if(allMovies.get(i).getCastList().get(j).getFullName().contains(searchActor.trim().toLowerCase()))
                    {
                        searchedMovies.add(allMovies.get(i));
                    }
                }
            }Recommendation.checkSearchResult(searchedMovies);
        }catch(IllegalArgumentException e){
            System.out.println("Empty search");
        }catch(Exception e){
            System.out.println("Unexpected error "+ e.getMessage());
        }
    }

    public void searchMovieByLanguage(ArrayList<Movie> allMovies)
    {
        String searchLanguage;
        System.out.println("Search : ");
        try{
            searchLanguage = input.next();

            if(searchLanguage.isBlank()){
                throw new IllegalArgumentException();
            }

            ArrayList<Movie> searchedMovies = new ArrayList<>();

            for(int i=0 ; i<allMovies.size() ; i++)
            {
                if(allMovies.get(i).getGenre().toLowerCase().contains(searchLanguage.trim().toLowerCase()))
                {
                    searchedMovies.add(allMovies.get(i));
                }
            }Recommendation.checkSearchResult(searchedMovies);
        }catch(IllegalArgumentException e){
            System.out.println("Empty search");
        }catch(Exception e){
            System.out.println("Unexpected error "+ e.getMessage());
        }
    }
   /* public Movie getMovieById(ArrayList<Movie> allMovies, String movieId) {
        if (movieId == null || movieId.isBlank()) {
            throw new IllegalArgumentException("Movie ID cannot be null or empty.");
        }

        for (Movie movie : allMovies) {
            if (movieId.equalsIgnoreCase(movie.getMovieId())) {
                return movie;
            }
        }

        System.out.println("No movie found with ID: " + movieId);
        return null;
    }*/

    public void Search(ArrayList<Movie> allMovies)
    {
        int choice1;

        System.out.println("Search movies");
        System.out.println("(1) By movie name");
        System.out.println("(2) By movie genre");
        System.out.println("(3) By movie director");
        System.out.println("(4) By movie Actor");
        System.out.println("(5) By Language");
        System.out.println("Enter your choice");
        try{
            choice1 = input.nextInt();

            switch (choice1){
                case 1:
                    searchMovieByName(allMovies);
                    break;
                case 2:
                    searchMovieByGenre(allMovies);
                    break;
                case 3:
                    searchMovieByDirector(allMovies);
                    break;
                case 4:
                    searchMovieByActor(allMovies);
                case 5:
                    searchMovieByLanguage(allMovies);
            }
        }catch(InputMismatchException e){
            System.out.println("Please enter valid number");
        }
    }

    // we have removed recommendation by Cast
/*    public void recommendedMovies(ArrayList<Movie> allMovies ,User Users, ArrayList<UserWatchRecord> userwatch){
        boolean isRecommended = false ;
        for(int i=0 ; i<allMovies.size() ; i++){
            for(int j=0 ; j<Users.getMoviesByUser(Users.getId(), userwatch, allMovies).size() ; j++)
            {
                if(allMovies.get(i).getDirector().getName().equals(Users.getMoviesByUser(Users.getId(), userwatch, allMovies).get(j).getDirector().getName()) ||
                        (allMovies.get(i).getGenre().equals(Users.getMoviesByUser(Users.getId(), userwatch, allMovies).get(j).getGenre()))) {
                    Movie.movieInfo(allMovies.get(i));
                    isRecommended = true ;
                }
            }
        }
        if(!isRecommended){
            System.out.println("You don't have any previous moives");
            System.out.println("all Movies");
            Movie.displayAll(allMovies);
        }
    }*/

    public void recommendedMovies(ArrayList<Movie> allMovies, User user, ArrayList<UserWatchRecord> userWatchRecords) {
        // List to store recommended movie names without duplicates
        ArrayList<String> movieRecommendedName = new ArrayList<>();
        boolean isRecommended = false;

        // Get the list of movies the user has already watched
        ArrayList<Movie> watchedMovies = user.getMoviesByUser(user.getId(), userWatchRecords, allMovies);

        // Loop through all movies
        for (Movie movie : allMovies) {
            // Loop through the user's watched movies
            for (Movie watchedMovie : watchedMovies) {
                // Check if the movie matches by director or genre
                if (movie.getDirector().getName().equals(watchedMovie.getDirector().getName()) ||
                        movie.getGenre().equals(watchedMovie.getGenre())) {

                    // Check if the movie is already in the recommended list
                    if (!movieRecommendedName.contains(movie.getTitle())) {
                        movieRecommendedName.add(movie.getTitle()); // Add the movie if not already present
                    }

                    isRecommended = true;
                }
            }
        }

        // Display recommended movies
        if (isRecommended) {
            System.out.println("Recommended Movies:");
            for (String movieName : movieRecommendedName) {
                System.out.println(movieName);
            }
        } else {
            System.out.println("You don't have any previously watched movies.");
            System.out.println("All Movies:");
            Movie.displayAll(allMovies);
        }
    }

}

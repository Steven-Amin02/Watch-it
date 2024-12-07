package watchit.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Recommendation extends Movie{

    
   Scanner input = new Scanner(System.in);

    
    
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
    
      public void sortLikesAsc(ArrayList<Movie> allMovies)
    {
        Collections.sort(allMovies , (m1,m2) -> Integer.compare(m1.getNoOfikes(), m2.getNoOfikes()));
        for(int i=0 ; i<allMovies.size() ; i++)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() + 
                    "Movie rate: " + allMovies.get(i).getNoOfikes());
        }
    }
  
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
    
      public void sortLikesDesc(ArrayList<Movie> allMovies)
      {
        //Collections.sort(allMovies , Collections.reverseOrder(Comparator.comparingInt(Movie::getNoOfikes)));
        Collections.sort(allMovies , (m1,m2) -> Integer.compare(m1.getNoOfikes(), m2.getNoOfikes()));
        for(int i=allMovies.size() ; i<=0 ; i--)
        {
            System.out.println("(" + i+1 + ") Movie name: " + allMovies.get(i).getTitle() + 
                    "Movie rate: " + allMovies.get(i).getNoOfikes());            
        }
      }
    
    public void Sort(ArrayList<Movie> allMovies)
    {
        int choice1 , choice2;
        
        System.out.println("Sort movies");
        System.out.println("(1) Sort by Rating");
        System.out.println("(2) Sort by Views");
        System.out.println("(3) Sort by Likes");
        System.out.println("Enter your choice");
        
        choice1 = input.nextInt();
        
        switch (choice1){
            case 1:
                System.out.println("(1) High to low");
                System.out.println("(2) Low to high");
                System.out.println("Enter your choice");
                choice2 = input.nextInt();
                switch(choice2){
                    case 1:
                        sortRatingDesc(allMovies);
                        break;
                    case 2:
                        sortRatingAsc(allMovies);
                        break;
                }
                break;
                case 2:
                System.out.println("(1) High to low");
                System.out.println("(2) Low to high");
                System.out.println("Enter your choice");
                choice2 = input.nextInt();
                switch(choice2){
                    case 1:
                        sortViewsDesc(allMovies);
                        break;
                    case 2:
                        sortViewsAsc(allMovies);
                        break;
                }
                break;
                case 3:
                System.out.println("(1) High to low");
                System.out.println("(2) Low to high");
                System.out.println("Enter your choice");
                choice2 = input.nextInt();
                switch(choice2){
                    case 1:
                        sortLikesDesc(allMovies);
                        break;
                    case 2:
                        sortLikesAsc(allMovies);
                        break;
                }
                break;       
        }
    }
     
//////////////SEARCH////////////////////////////SEARCH//////////////////////////////////SEARCH/////////////////////    
 
    
    public static final void checkSearchResult(int answer ,ArrayList<Movie> searchedResult)
        {
            if(answer == 0)
                System.out.println("No matches found");
            else{
                for(int i=0 ; i<searchedResult.size() ; i++)
                    System.out.println(searchedResult.get(i).getTitle());
            }
        }
    
    
    
    public void searchMovieName(ArrayList<Movie> allMovies)     //search movie by title
    {
        String searchMovie;
        System.out.println("Search : ");
        searchMovie = input.next();
        
        ArrayList<Movie> searchedMovies = new ArrayList<>();
        
        for(int i=0 ; i<allMovies.size() ; i++)
        {
            if( (allMovies.get(i).getTitle().equalsIgnoreCase(searchMovie)) || 
                    (allMovies.get(i).getTitle().toLowerCase().contains(searchMovie.toLowerCase())))
            {
                searchedMovies.add(allMovies.get(i));
            }
            Recommendation.checkSearchResult(searchedMovies.size(), searchedMovies);
        }   
    }
    
    //search by director
    //search by genres
    
    
    
   /*
    to be added in Movie class
    
    private float rate;
    private int noOfviews;
    private int noOfikes;

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
    }*/

    
    
}

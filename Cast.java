import java.util.*;

public class Cast {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private List<String> movies;
    private List<String> awards;
    private List<String> socialMediaLinks;

   
    public Cast(String firstName, String lastName, String dateOfBirth, String gender, String nationality,
                List<String> movies, List<String> awards, List<String> socialMediaLinks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.movies = movies != null ? movies : new ArrayList<>();
        this.awards = awards != null ? awards : new ArrayList<>();
        this.socialMediaLinks = socialMediaLinks != null ? socialMediaLinks : new ArrayList<>();
    }

    
    public void displayDetails() {
        System.out.println("--- Cast Details ---");
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Nationality: " + nationality);
        System.out.println("Movies: " + movies);
        System.out.println("Awards: " + awards);
        System.out.println("Social Media Links: " + socialMediaLinks);
    }

   
    public int calculatePopularityScore() {
        int score = 0;
        score += movies.size() * 10;  
        score += awards.size() * 20; s
        score += socialMediaLinks.size() * 5;
        return score;
    }

   
    public static void main(String[] args) {

        Cast castMember = new Cast(
                "Mohamed", "Yasser",
                "22/07/2005", "Male",
                "Egyptian",
                Arrays.asList("Inception", "Interstellar"),
                Arrays.asList("Best Actor 2021", "Lifetime Achievement 2023"),
                Arrays.asList("Facebook: fb.com/mohamed", "Twitter: twitter.com/mohamed")
        );

        
        castMember.displayDetails();

        
        int popularityScore = castMember.calculatePopularityScore();
        System.out.println("\nPopularity Score: " + popularityScore);
    }
}

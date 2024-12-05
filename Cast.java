import java.util.ArrayList;

public class Cast {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String nationality;
    private ArrayList<String> movies;
    private ArrayList<String> awards;
    private ArrayList<String> socialMediaLinks;

    
    public Cast(String firstName, String lastName, String dateOfBirth, String gender, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.nationality = nationality;
        this.movies = new ArrayList<>();
        this.awards = new ArrayList<>();
        this.socialMediaLinks = new ArrayList<>();
    }

   
    public void addMovie(String movie) {
        movies.add(movie);
    }

   
    public void displayMovies() {
        System.out.println("Movies:");
        for (String movie : movies) {
            System.out.println("- " + movie);
        }
    }

    
    public void addAward(String award) {
        awards.add(award);
    }

   
    public void displayAwards() {
        System.out.println("Awards:");
        for (String award : awards) {
            System.out.println("- " + award);
        }
    }

    
    public void addSocialMediaLink(String link) {
        socialMediaLinks.add(link);
    }

    
    public void displaySocialMediaLinks() {
        System.out.println("Social Media Links:");
        for (String link : socialMediaLinks) {
            System.out.println("- " + link);
        }
    }

    
    public void displayDetails() {
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Nationality: " + nationality);
        displayMovies();
        displayAwards();
        displaySocialMediaLinks();
    }

    
    public static void main(String[] args) {
        Cast castMember = new Cast("Mohamed", "Yasser", "22/7/2005", "Male", "Egyptian");
        castMember.addMovie("Inspirational Journey");
        castMember.addMovie("Code of Honor");
        castMember.addAward("Best Rising Star Award");
        castMember.addSocialMediaLink("https://twitter.com/mohamedyasser");
        castMember.displayDetails();
    }
}

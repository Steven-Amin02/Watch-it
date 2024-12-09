import java.util.List;
import java.util.ArrayList;

public class Director {
    final String firstName;
    final String lastName;
    final String nationality;
    final int age;
    final String dateOfBirth;
    final String gender;
    final String socialMedialinks;
    private List<String> movies;
    private String name;
    public Director(String firstName, String lastName, String nationality, int age, String dateOfBirth, String gender, String socialMedialinks){
        name = firstName.concat(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.age = age;
        this.socialMedialinks = socialMedialinks;
        this.movies = new ArrayList<>();
    }

    // movie setter
    public void addMovie(String movie){
        movies.add(movie);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNationality() {
        return nationality;
    }

    public int getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getSocialMedialinks() {
        return socialMedialinks;
    }

    public List<String> getMovies() {
        return movies;
    }

    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //display directors info
    public void displayInfo(){
        System.out.println("Director: " + firstName + " " + lastName);
        System.out.println("Date Of Birth: " + dateOfBirth);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Nationality: " + nationality);
        System.out.println("Social Media Links: " + socialMedialinks);
        System.out.println("Movies: ");
        for (String movie : movies){
            System.out.println(" - " + movie);
        }
        System.out.println();
    }
}

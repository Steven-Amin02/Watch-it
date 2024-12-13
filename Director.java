package Model;
import java.util.List;
import java.util.ArrayList;

public class Director {
    protected String firstName;
    protected String lastName;
    protected String nationality;
    protected int age;
    protected String dateOfBirth;
    protected String gender;
    protected String socialMedialinks;
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

    public Director() {

    }

    // movie setter
    public void addMovie(String movie){
        movies.add(movie);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSocialMedialinks(String socialMedialinks) {
        this.socialMedialinks = socialMedialinks;
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
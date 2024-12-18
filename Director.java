package Model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;
import java.net.MalformedURLException;
import java.net.URL;

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

    public Director(String firstName, String lastName,
                    String nationality, int age, String dateOfBirth,
                    String gender, String socialMedialinks) {
        name = firstName.concat(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
        this.gender = gender;
        this.age = age;
        this.socialMedialinks = socialMedialinks;
        this.movies = new ArrayList<>();
        setDateOfBirth(dateOfBirth);
    }

    public Director() {
        this.movies = new ArrayList<>();
    }

    // Movie setter
    public void addMovie(String movie) {
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

    //to validate age
    public void setAge(String ageInput) {
        try {
            int parsedAge = Integer.parseInt(ageInput);
            if (parsedAge > 0) {
                this.age = parsedAge;
            } else {
                System.out.println("Age must be a positive number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input for age. Please enter a numeric value.");
        }
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(String dateOfBirth) {
        if (isValidDate(dateOfBirth)) {
            this.dateOfBirth = dateOfBirth;
        } else {
            this.dateOfBirth = "Invalid dateOfBirth";
        }
    }

    //to validate date of birth
    private boolean isValidDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            return !parsedDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setSocialMedialinks(String socialMedialinks) {
        if (isValidURL(socialMedialinks)) {
            this.socialMedialinks = socialMedialinks;
        } else {
            System.out.println("Invalid social media link. Please provide a valid URL.");
        }
    }

    //to validate SM links
    private boolean isValidURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
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

    // Display director's info
    public String displayInfo() {
        return "Director{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", age=" + age +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", socialMedialinks='" + socialMedialinks + '\'' +
                ", movies=" + movies +
                ", name='" + name + '\'' +
                '}';
    }
}

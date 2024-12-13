package Model;

import java.util.ArrayList;

public class Cast {
    private String firstName;
    private String lastName;
    private String fullName = firstName.concat(lastName);
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

    public String getFullName() {
        return fullName;
    }

    public ArrayList<String> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<String> movies) {
        this.movies = movies;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public ArrayList<String> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<String> awards) {
        this.awards = awards;
    }

    public ArrayList<String> getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public void setSocialMediaLinks(ArrayList<String> socialMediaLinks) {
        this.socialMediaLinks = socialMediaLinks;
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
}
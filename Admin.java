import java.util.Scanner;

public class Admin {
    final private String Adminid="010";
    private String UserName,Password;

    public Admin() {}

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }


//this method check that the entered password has: 1-uppercase char 2-lowercase char 3-digit 4-special char
public static boolean validatePasswordComplexity(String password) {
    boolean hasUppercase = false, hasLowercase = false, hasDigit = false, hasSpecialChar = false;
    for (char c : password.toCharArray()) {
        if (Character.isUpperCase(c)) {
            hasUppercase = true;
        } else if (Character.isLowerCase(c)) {
            hasLowercase = true;
        } else if (Character.isDigit(c)) {
            hasDigit = true;
        } else if (!Character.isLetterOrDigit(c)) {
            hasSpecialChar = true;
        }
    }
    return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;}

    public void setPassword(String password) {

boolean isValid=false;
do{
    System.out.println("Enter password :");
    Scanner in = new Scanner(System.in);
    password = in.next();
    isValid = validatePasswordComplexity(password);
    if(!isValid){
        System.out.println("Invalid password. Please try again.");
    }
}while(!isValid);
            this.Password = password;
            System.out.println("the password successfully entered");

    }
//    public void addMovie(Movie movie) {
//    }
//
//    public void removeMovie(Movie movie) {
//
//    }
//
//    public void updateMovieDetails(Movie movie) {
//    }
//
//    public void viewUserStats(User user) {
//    }
//
//    public void listAllUsers() {
//    }
//
//    public void viewSystemStats() {
//    }
//
//

}

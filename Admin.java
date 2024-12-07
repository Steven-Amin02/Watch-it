import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
public class Admin extends Person {
    public static final Scanner in=new Scanner(System.in);
    private ArrayList<Movie> movielist=new ArrayList<Movie>();
    private String id;

    public Admin(String username, String password, String email, String fname, String lname) {
        super(username, password, email, fname, lname);
        this.id = IDGenerator.generateID('A'); // Generate unique admin ID
    }

    @Override
    public void setEmail(String email) {
        // Use a specific regex for Admin email validation
        validateEmail(email, "^[\\w.-]+@watchit\\.admin\\.com$");
    }

    public String getId() {
        return id;
    }
    public void MovieList(Movie movie){
        System.out.println("------------Movie------------");
        System.out.println("[1]Add Movie \n [2]Update Movie Details \n [3]Delete Movie");
        int choice=0;
        choice=in.nextInt();
        switch (choice){
            case 1:
                addMovie(movie);
                break;
            case 2:
              //  updateMovieDetails(movie);
                break;
            case 3:
                System.out.println("Enter Movie ID that you want to delete");
                String movieid=in.next();
                removeMovie(movieid);
                break;
            default:
                    System.out.println("Invalid choice. Please select a valid option from the list.");

        }
    }
public void addMovie(Movie movie) {
    if(movie==null){
        System.out.println("Cannot an empty movie ,please enter a movie");
        return;
    }
    if(!movielist.contains(movie)){
        movielist.add(movie);
        System.out.println("Movie added successfully.");
    }
    else{
        System.out.println("Movie already exists.");
    }

}
public void removeMovie(String movieid) {
        if(movieid==null||movieid.isEmpty()){
            System.out.println("Invalid movie ID ");
            return;
        }
        boolean removed=movielist.removeIf(movie -> movie.getMovieId().equals(movieid));
if(removed){
    System.out.println("Movie removed successfully.");
}
else{
    System.out.println("Movie not found.");
}
}
public void updateMovieDetails(String movieId) {
        for (Movie movie : movielist) {
            if (movie.getMovieId().equals(movieId)) {
                boolean updating = true;
                while (updating) {
                    System.out.println("Choose what to update:");
                    System.out.println("[1] Title");
                    System.out.println("[2] Release Date");
                    System.out.println("[3] Duration");
                    System.out.println("[4] IMDB Score");
                    System.out.println("[5] Genres");
                    System.out.println("[6] Languages");
                    System.out.println("[7] Budget");
                    System.out.println("[8] Revenue");
                    System.out.println("[9] Poster");
                    System.out.println("[10] Movie Rate");
                    System.out.println("[11] Country");
                    System.out.println("[0] Exit");

                    int choice = in.nextInt();
                    in.nextLine();  // consume newline

                    switch (choice) {
                        case 1:
                            System.out.print("Enter new title: ");
                            String title = in.nextLine();
                            movie.setTitle(title);
                            break;
                        case 2:
                            System.out.print("Enter new release date (yyyy-MM-dd): ");
                            String dateStr = in.nextLine();
                            try {
                                LocalDate date = LocalDate.parse(dateStr);
                                movie.setMovieDate(date);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format.");
                            }
                            break;
                        case 3:
                            System.out.print("Enter new duration in minutes: ");
                            float duration = in.nextFloat();
                            movie.setDuration(duration);
                            break;
                        case 4:
                            System.out.print("Enter new IMDB score: ");
                            double score = in.nextDouble();
                            movie.setImdbScore(score);
                            break;
                        case 5:
                            movie.modifyGenre();  // Assume modifyGenre handles adding/removing genres
                            break;
                        case 6:
                            System.out.print("Enter new language: ");
                            String language = in.nextLine();
                            movie.addLanguage(language);
                            break;
                        case 7:
                            System.out.print("Enter new budget: ");
                            float budget = in.nextFloat();
                            movie.setBudget(budget);
                            break;
                        case 8:
                            System.out.print("Enter new revenue: ");
                            float revenue = in.nextFloat();
                            movie.setRevenue(revenue);
                            break;
                        case 9:
                            System.out.print("Enter new poster URL: ");
                            String poster = in.nextLine();
                            movie.setPoster(poster);
                            break;
                        case 10:
                            System.out.print("Enter new movie rate: ");
                            float rate = in.nextFloat();
                            movie.setMovieRate(rate);
                            break;
                        case 11:
                            System.out.print("Enter new country: ");
                            String country = in.nextLine();
                            movie.setCountry(country);
                            break;
                        case 0:
                            updating = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }
                System.out.println("Movie details updated successfully!");
                return;
            }
        }
        System.out.println("Movie with ID " + movieId + " not found.");
    }


public void viewUserStats(User user) {

}
public void listAllUsers() {
}
    public void addUser(login loginSystem) throws Exception {
       loginSystem.signUp();
    }
    public void modifyUser(login loginSystem) {
        String id,username;
        System.out.println("----------Searching For User----------");
        System.out.println("[1]User ID \n [2]Username");
        System.out.println("Enter Your Choice: ");
        int choice1 = 0;
        try{
            choice1 = in.nextInt();
        }catch (Exception e){
            System.out.println("Invalid choice please enter a number between 1 and 2");
        } User user = null;
        switch (choice1){
            case 1:
                System.out.print("Please enter the user ID that you want to modify: ");
                id = in.next();
                user = loginSystem.getUserById(id);
                if (user == null) {
                    System.out.println("User with ID " + id + " not found.");
                    return;
                }
                break;
            case 2:
                System.out.print("Please enter the user username that you want to modify: ");
                username = in.next();
                user = loginSystem.getUserByUsername(username);
                if (user == null) {
                    System.out.println("User with Username: " + username + " not found.");
                    return;}
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option from the list.");
                break;
        }
        if(user !=null){
            System.out.println("--------User Details--------");
            System.out.println("First Name :"+user.getFirstName()+"\n Last Name :"+user.getLastName()+"\n Username :"+user.getUsername()+"\n Email :"+user.getEmail()+"\n Password :"+user.getPassword()+"\n");
            System.out.println("--------User Details--------");
            System.out.println("[1]Modify First Name \n [2]Modify Last Name\n[3]Modify Username \n [4]Modify Email \n [5]Modify Password \n ");
            System.out.print("Enter Your Choice: ");
            int choice2 = 0;
            try {
                choice2 = in.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice please enter a number between 1 and 5");
            }
            switch (choice2) {
                case 1:
                    System.out.print("Enter New First Name:");
                    user.setFirstName(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 2:
                    System.out.println("Enter New Last Name:");
                    user.setLastName(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 3:
                    System.out.println("Enter New Username:");
                    user.setUsername(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 4:
                    System.out.println("Enter New Email:");
                    user.setEmail(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                case 5:
                    System.out.println("Enter New Password:");
                    user.setPassword(in.next());
                    System.out.println("User details updated successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option from the list.");

            }
        }


    }
    public void deleteUser(login loginSystem, String username) {
        try {
            loginSystem.deleteUser(username); // Call deleteUser method in the Login class
            System.out.println("User " + username + " deleted successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

import java.time.LocalDate;

    public class UserWatchRecord {
        private User user;
        private Movie movie;
        private LocalDate dateOfWatching;
        private float rating;

        public UserWatchRecord(User user, Movie movie, LocalDate dateOfWatching, float rating) {
            this.user = user;
            this.movie = movie;
            this.dateOfWatching = dateOfWatching;
            this.rating = rating;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Movie getMovie() {
            return movie;
        }

        public void setMovie(Movie movie) {
            this.movie = movie;
        }

        public LocalDate getDateOfWatching() {
            return dateOfWatching;
        }

        public void setDateOfWatching(LocalDate dateOfWatching) {
            this.dateOfWatching = dateOfWatching;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            if( rating >= 1 && rating <= 5){
                System.out.println("Your Rating has been added");
                this.rating = rating;
            }
            else
                System.out.println("Invalid Rating Please enter a rate from 1 to 5");
        }
    }


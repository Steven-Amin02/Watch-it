import java.util.Calendar;
import java.util.Date;


public class Subscription {

    private String UserID;
    private Plan Plans ;
    private double Price;
    private Date StartDate;

    private static int MoviesWatched;
    private Date ExpirationDate ;
    private boolean RenewalStatus;

    public Subscription(String userID, String planType, Date startDate) {
        this.UserID = userID;
        this.StartDate = startDate;
        this.setPlan(planType); // Dynamically set the plan
        this.ExpirationDate = calculateExpirationDate(startDate);
        this.RenewalStatus = false; // Default is manual renewal
    }
    public Subscription()
    {
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public Plan getPlans() {
        return Plans;
    }

    public void setPlans(Plan plans) {
        Plans = plans;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public boolean isRenewalStatus() {
        return RenewalStatus;
    }

    public void setRenewalStatus(boolean renewalStatus) {
        RenewalStatus = renewalStatus;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }

    public static int getMoviesWatched() {
        return MoviesWatched;
    }

    public static void setMoviesWatched(int moviesWatched) {
        MoviesWatched = moviesWatched;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setPlan(String planType) {
        try {
            switch (planType.toLowerCase()) {
                case "basic":
                    this.Plans = new Basic();
                    break;
                case "standard":
                    this.Plans = new Standard();
                    break;
                case "premium":
                    this.Plans = new Premium();
                    break;
                default:
                    System.out.println("Invalid plan type");
            }
        }
           catch(NullPointerException e){
                // Plan type is not set.
                System.out.println("You don't have a plan.");
            }

        }



    private Date calculateExpirationDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }
    public void checkSubscriptionStatus() {
       try{
           Date currentDate = new Date();
           if (currentDate.after(this.ExpirationDate)) {
               System.out.println("Your subscription has expired. Please renew to continue watching movies.");
               // Logic to restrict access (e.g., set a flag or throw an exception)
           } else {
               System.out.println("Your subscription is active.");
           }
       }
       catch(NullPointerException e) {
           //Expiration date is not set.
           System.out.println("You don't have a plan.");
       }
       catch(IllegalStateException e) {

           //Subscription has expired.
           System.out.println("Your subscription has expired. Please renew to continue watching movies.");


       }
    }
    public boolean canWatchMovie() {
       try{
           Date currentDate = new Date();
           if (currentDate.after(this.ExpirationDate)) {
               System.out.println("Cannot watch movies: Subscription has expired.");
               return false;
           }
           if (MoviesWatched >= this.Plans.MaxMovies) {
               System.out.println(STR."Cannot watch movies: You have reached your monthly limit of \{this.Plans.MaxMovies} movies.");
               return false;
           }
           return true;
       }
       catch (NullPointerException e){
           //Plan or maximum movie limit is not set.
           System.out.println("You don't have a plan.");
           return false;
       }
       catch(IllegalStateException e) {
           //Subscription has expired.
           System.out.println("Your subscription has expired. Please renew to continue watching movies.");
           return false;
       }
    }
    public void watchMovie() {
       try {
           if (canWatchMovie()) {
               MoviesWatched++;
               System.out.println(STR."You watched a movie! Total movies watched this month: \{MoviesWatched}");
           } else {
               System.out.println("Unable to watch movie. Check your subscription status or movie limit.");
           }
       }
           catch(NullPointerException E ){
           //Plan is not set or maximum movie limit.
               System.out.println("You don't have a plan.");
           }
       catch(IllegalStateException e) {
           //Subscription has expired.
           System.out.println("Your subscription has expired. Please renew to continue watching movies.");
       }
       }



    ////////////// Upgrade Plan

    public void upgradePlan(String newPlan) {
        // Ensure new plan is higher-tier than the current one
        if ((newPlan.equals("premium"))){
            System.out.println("You are already on the highest plan. Upgrade not possible.");
            return;
        }
        try{

            switch (newPlan.toLowerCase()) {
                case "basic":
                    if (this.Plans instanceof Basic) {
                        System.out.println("You are already on the Basic plan.");
                        return;
                    } else {
                        this.Plans = new Basic();
                    }
                    break;
                case "standard":
                    if (this.Plans instanceof Basic) {
                        this.Plans = new Standard();
                    } else {
                        System.out.println("You are already on the Standard plan.");
                        return;
                    }
                    break;
                case "premium":
                    this.Plans = new Premium();
                    break;
                default:
                    System.out.println("Invalid plan selected for upgrade.");
                    return;
            }

            // Adjust price, reset movie count, and extend expiration date
            this.Price = this.Plans.Price;
            MoviesWatched = 0; // Reset movie count for new plan
            this.ExpirationDate = calculateExpirationDate(new Date()); // Recalculate expiration date from today
            System.out.println(STR."Plan upgraded to: \{newPlan}. New monthly price: $\{this.Price}");
        }
        catch(NullPointerException e) {
            //Plan is not set or maximum movie limit.
            System.out.println("You don't have a plan.");
        }
    }

    public void DeletePlan() {
        this.Plans = null;
        System.out.println("Subscription plan deleted.");

    }



    public void sendExpirationNotification() {
       try{
           Calendar calendar = Calendar.getInstance();
           calendar.setTime(this.ExpirationDate);
           calendar.add(Calendar.DATE, -3);
           Date notificationDate = calendar.getTime();

           Date currentDate = new Date();
           if (currentDate.equals(notificationDate)) {
               System.out.println("Your subscription will expire in 3 days.");
           } else if (currentDate.equals(this.ExpirationDate)) {
               System.out.println("Your subscription expires today.");
           }
       }
       catch(NullPointerException e) {
           //Expiration date is not set.
           System.out.println("You don't have a plan.");
       }
    }

    public void usageReminder() {
        if (this.Plans.MaxMovies - MoviesWatched == 1) {
            System.out.println("You have only 1 movie left this month.");
        }
    }

    public void recommendUpgrade() {
        if (MoviesWatched >= this.Plans.MaxMovies - 1) {
            System.out.println("Consider upgrading to a higher plan for more movies.");
        }
    }


    public void renewSubscription() {
        this.ExpirationDate = calculateExpirationDate(new Date());
        System.out.println(STR."Subscription renewed. Next expiration date: \{this.ExpirationDate}");
    }

    public void sendPlanComparison() {


        try{
            System.out.println(STR."Current plan: \{this.Plans.getPlanName()}");
            System.out.println("Compare with other plans to find the best fit.");
        }
        catch (NullPointerException E){
            System.out.println("No plan set for this subscription.");
        }

    }





    //end
    }








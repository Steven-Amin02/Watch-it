import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.lang.StringTemplate;



public class Subscription {

    private String UserID;
    private Plan Plans;
    private double Price;
    private Date StartDate;
    private static int MoviesWatched;
    private Date ExpirationDate;
    private boolean RenewalStatus;
    private static int BasicCount;
    private static int PremiumCount;
    private static int StandardCount;


    Scanner scanner = new Scanner(System.in);

    public Subscription(String userID, String planType, Date startDate) {
        this.UserID = userID;
        this.StartDate = startDate;
        this.setPlan(planType); // Dynamically set the plan
        this.ExpirationDate = calculateExpirationDate(startDate);
        this.RenewalStatus = false; // Default is manual renewal
    }

    public Subscription() {
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
                    BasicCount++;
                    break;
                case "standard":
                    this.Plans = new Standard();
                    StandardCount++;
                    break;
                case "premium":
                    this.Plans = new Premium();
                    PremiumCount++;
                    break;
                default:
                    System.out.println("Invalid plan type");
            }
        } catch (NullPointerException e) {
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
        if (this.Plans != null) {
            Date currentDate = new Date();
            if (currentDate.after(this.ExpirationDate)) {
                System.out.println("Your subscription has expired. Please renew to continue watching movies.");
                // Logic to restrict access (e.g., set a flag or throw an exception)
            } else {
                System.out.println("Your subscription is active.");
            }
        } else {
            System.out.println("You don't have a plan.");

        }

    }

    public boolean canWatchMovie() {
        try {
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
        } catch (NullPointerException e) {
            //Plan or maximum movie limit is not set.
            System.out.println("You don't have a plan.");
            return false;
        } catch (IllegalStateException e) {
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
        } catch (NullPointerException E) {
            //Plan is not set or maximum movie limit.
            System.out.println("You don't have a plan.");
        } catch (IllegalStateException e) {
            //Subscription has expired.
            System.out.println("Your subscription has expired. Please renew to continue watching movies.");
        }
    }


    /// /////////// Upgrade Plan

    public void upgradePlan(String newPlan) {

        try {

            switch (newPlan.toLowerCase()) {
                case "basic":
                    if (this.Plans instanceof Basic) {
                        System.out.println("You are already on the Basic plan.");
                        return;
                    } else {
                        if (this.Plans instanceof Premium) {
                            PremiumCount--;
                        } else if (this.Plans instanceof Standard) {
                            StandardCount--;
                        }
                        this.Plans = new Basic();
                        BasicCount++;
                    }
                    break;
                case "standard":
                    if (this.Plans instanceof Standard) {
                        System.out.println("You are already on the Standard plan.");
                        return;
                    } else {
                        if (this.Plans instanceof Premium) {
                            PremiumCount--;
                        } else if (this.Plans instanceof Basic) {
                            BasicCount--;
                        }
                        this.Plans = new Standard();
                        StandardCount++;

                    }
                    break;
                case "premium":
                    if (this.Plans instanceof Premium) {
                        System.out.println("You are already on the Premium plan.");
                        return;
                    } else {
                        if (this.Plans instanceof Basic) {
                            BasicCount--;
                        } else if (this.Plans instanceof Standard) {
                            StandardCount--;
                        }
                        this.Plans = new Premium();
                        PremiumCount++;
                    }
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
        } catch (NullPointerException e) {
            //Plan is not set or maximum movie limit.
            System.out.println("You don't have a plan.");
        }
    }

    public void DeletePlan() {
        try {
            switch (this.Plans.getPlanName()) {
                case "basic" -> BasicCount--;
                case "standard" -> StandardCount--;
                case "premium" -> PremiumCount--;
            }

            this.Plans = null;
            System.out.println("Subscription plan deleted.");

        } catch (NullPointerException e) {
            //Plan is not set.
            System.out.println("You already don't have a plan.");
        }
    }


    public void sendExpirationNotification() {
        try {
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
        } catch (NullPointerException e) {
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
        if (Plans == null) {
            System.out.println("You don't have a subscription to renew.");
        } else {
            this.ExpirationDate = calculateExpirationDate(new Date());
            System.out.println(STR."Subscription renewed. Next expiration date: \{this.ExpirationDate}");
        }
    }

    public void sendPlanComparison() {


        try {
            System.out.println(STR."Current plan: \{this.Plans.getPlanName()}");
            System.out.println(STR."Resolution: \{this.Plans.getResolution()}");
            System.out.println(STR."Number of Movies: \{MoviesWatched}");
            System.out.println(STR."Device Access: \{this.Plans.getDeviceAccess()}");
            System.out.println(STR."Family Sharing: \{this.Plans.getFamilySharing()}");
            System.out.println(STR."Additional Benefits: \{this.Plans.getAdditionalBenefit()}");
            System.out.println("-----------------------------------------------------------");
        } catch (NullPointerException E) {
            System.out.println("No plan set for this subscription.");
        }

    }

    public void DisplayNumOfSubscriptions() {
        System.out.println(STR."Basic plan subscriptions: \{BasicCount}");
        System.out.println(STR."Standard plan subscriptions:  \{StandardCount}");
        System.out.println(STR."Premium plan subscriptions:  \{PremiumCount}");
    }

    public void DisplaySubscriptions() {
        //Display Plans Details
        System.out.println("Available Plans:");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Plan", "Movies", "Price", "Resolution", "Device Access", "Family Sharing");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Basic", "5", "$10", "SD", "0", "0");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Standard", "10", "$20", "HD", "2", "2");
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s\n", "Premium", "30", "$50", "4K", "Unlimited", "4");
        System.out.println("-----------------------------------------------------------");
    }


    public void test() {

        while (true) {

            System.out.println("Subscriptions Menu");
            System.out.println("1. Upgrade plan");
            System.out.println("2. Cancel Subscriptions");
            System.out.println("3. Check subscription status");
            System.out.println("4. Watch a movie");
            System.out.println("5. Send plan comparison");
            System.out.println("6. Check Number of subscriptions");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    DisplaySubscriptions();
                    System.out.println("\n");
                    System.out.print("Enter new plan type (basic, standard, premium): ");
                    String newPlan = scanner.next();
                    upgradePlan(newPlan);
                    break;
                case 2:
                    DeletePlan();
                    break;
                case 3:
                    checkSubscriptionStatus();
                    break;
                case 4:
                    watchMovie();
                    break;
                case 5:
                    sendPlanComparison();
                    break;
                case 6:
                    DisplayNumOfSubscriptions();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

    }



public void TestNonSubscription() {
    while (true) {
        System.out.println("Not Subscribed Menu");
        System.out.println("1. Choose your subscription.");
        System.out.println("2. Display available subscriptions");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter new plan type (basic, standard, premium): ");
                String newPlan = scanner.next();
                setPlan(newPlan);
                break;
            case 2:
                DisplaySubscriptions();
                break;
            case 3:
                System.out.println("Exiting...");
                scanner.close();
                return;
                default:
                System.out.println("Invalid choice. Please try again.");

                break;
        }

    }
}

public void Testmenu() {
    if (Plans== null) {
        TestNonSubscription();
    }
    else{
        test();
    }
}



    //end
   }








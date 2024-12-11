package Model;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;




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
        if (planType == null || planType.trim().isEmpty()) {
            this.Plans = Plan.getPlanByName("Non-plan");
        } else {
            this.setPlan(planType); // Dynamically set the chosen plan
        }
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
            Plan selectedPlan = Plan.getPlanByName(planType); // Fetch plan from ArrayList
            if (selectedPlan != null) {
                this.Plans = selectedPlan;
                switch (selectedPlan.PlanName.toLowerCase()) {
                    case "basic" -> BasicCount++;
                    case "standard" -> StandardCount++;
                    case "premium" -> PremiumCount++;
                }
            } else {
                System.out.println("Invalid plan type");
            }
        } catch (NullPointerException e) {
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
            if (this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                System.out.println("You are currently on the Non-plan. No subscription benefits are available.");
            } else {
                Date currentDate = new Date();
                if (currentDate.after(this.ExpirationDate)) {
                    System.out.println("Your subscription has expired. Please renew to continue watching movies.");
                    // Logic to restrict access (e.g., set a flag or throw an exception)
                } else {
                    System.out.println("Your subscription is active.");
                }
            }
        } else {
            System.out.println("You don't have a plan.");
        }
    }

    public boolean canWatchMovie() {
        try {
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                System.out.println("You cannot watch movies under the Non-plan.");
                return false;
            }
            Date currentDate = new Date();
            if (currentDate.after(this.ExpirationDate)) {
                System.out.println("Cannot watch movies: Subscription has expired.");
                return false;
            }
            if (MoviesWatched >= this.Plans.MaxMovies) {
                System.out.println("Cannot watch movies: You have reached your monthly limit of " + this.Plans.MaxMovies + " movies.");
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            System.out.println("You are currently on the Non-plan.");
            return false;
        }
    }


    public void watchMovie() {
        try {
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                System.out.println("You cannot watch movies under the Non-plan. Please upgrade your plan.");
            } else if (canWatchMovie()) {
                MoviesWatched++;
                System.out.println("You watched a movie! Total movies watched this month: " + MoviesWatched);
            } else {
                System.out.println("Unable to watch movie. Check your subscription status or movie limit.");
            }
        } catch (NullPointerException E) {
            // Plan is not set or maximum movie limit.
            System.out.println("You don't have a plan.");
        } catch (IllegalStateException e) {
            // Subscription has expired.
            System.out.println("Your subscription has expired. Please renew to continue watching movies.");
        }
    }


    /// /////////// Upgrade Plan
    public void upgradePlan(String newPlanType) {
        try {
            Plan newPlan = Plan.getPlanByName(newPlanType); // Fetch new plan
            if (newPlan == null) {
                System.out.println("Invalid plan selected for upgrade.");
                return;
            }

            if (this.Plans != null && this.Plans.PlanName.equalsIgnoreCase(newPlanType)) {
                System.out.println("You are already on the " + newPlanType + " plan.");
                return;
            }

            // Adjust subscription counts
            if (this.Plans != null) {
                switch (this.Plans.getPlanName().toLowerCase()) {
                    case "basic" -> BasicCount--;
                    case "standard" -> StandardCount--;
                    case "premium" -> PremiumCount--;
                }
            }
            switch (newPlan.getPlanName().toLowerCase()) {
                case "basic" -> BasicCount++;
                case "standard" -> StandardCount++;
                case "premium" -> PremiumCount++;
            }

            // Update plan
            this.Plans = newPlan;
            this.Price = newPlan.getPrice();
            MoviesWatched = 0; // Reset movie count
            this.ExpirationDate = calculateExpirationDate(new Date()); // Reset expiration date
            System.out.println("Plan upgraded to: " + newPlan.getPlanName() + ". New monthly price: $" + this.Price);
        } catch (NullPointerException e) {
            System.out.println("You don't have a plan.");
        }
    }

    public void DeletePlan() {
        try {
            if (this.Plans != null && !this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                switch (this.Plans.getPlanName().toLowerCase()) {
                    case "basic" -> BasicCount--;
                    case "standard" -> StandardCount--;
                    case "premium" -> PremiumCount--;
                }
                // Replace with Non-plan
                this.Plans = Plan.getPlanByName("Non-plan");
                System.out.println("Your subscription plan has been reset to the default Non-plan.");
            } else {
                System.out.println("You are already on the Non-plan and cannot delete it.");
            }
        } catch (NullPointerException e) {
            System.out.println("No plan is currently associated with this subscription.");
        }
    }


    public void sendExpirationNotification() {
        try {
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                // No notification for Non-plan users
                System.out.println("You are on the Non-plan. No expiration notifications apply.");
                return;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.ExpirationDate);
            calendar.add(Calendar.DATE, -3); // Notification 3 days before expiration
            Date notificationDate = calendar.getTime();

            Date currentDate = new Date();
            if (currentDate.equals(notificationDate)) {
                System.out.println("Your subscription will expire in 3 days.");
            } else if (currentDate.equals(this.ExpirationDate)) {
                System.out.println("Your subscription expires today.");
            }
        } catch (NullPointerException e) {
            // Expiration date is not set or the plan is null
            System.out.println("You don't have a valid subscription.");
        }
    }

    public void usageReminder() {
        if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            System.out.println("You are on the Non-plan. No usage limits apply.");
        } else if (this.Plans.MaxMovies - MoviesWatched == 1) {
            System.out.println("You have only 1 movie left this month.");
        }
    }

    public void recommendUpgrade() {
        if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            System.out.println("You are on the Non-plan. Consider upgrading your plan to enjoy subscription benefits.");
        } else if (MoviesWatched >= this.Plans.MaxMovies - 1) {
            System.out.println("Consider upgrading to a higher plan for more movies.");
        }
    }


    public void renewSubscription() {
        if (this.Plans == null || this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            System.out.println("You are on the Non-plan. Renewal is not applicable.");
        } else {
            this.ExpirationDate = calculateExpirationDate(new Date());
            System.out.println("Subscription renewed. Next expiration date: " + this.ExpirationDate);
        }
    }

    public void sendPlanComparison() {


        try {
            System.out.println("Current plan: "+this.Plans.getPlanName());
            System.out.println("Resolution: "+this.Plans.getResolution());
            System.out.println("Number of Movies: "+MoviesWatched);
            System.out.println("Device Access: "+this.Plans.getDeviceAccess());
            System.out.println("Family Sharing:"+ this.Plans.getFamilySharing());
            System.out.println("Additional Benefits:"+ this.Plans.getAdditionalBenefit());
            System.out.println("-----------------------------------------------------------");
        } catch (NullPointerException E) {
            System.out.println("No plan set for this subscription.");
        }

    }

    public void DisplayNumOfSubscriptions() {
        System.out.println("Basic plan subscriptions: "+BasicCount);
        System.out.println("Standard plan subscriptions:  "+StandardCount);
        System.out.println("Premium plan subscriptions:  "+PremiumCount);
    }

    public void DisplaySubscriptions() {
        System.out.println("Available Plans:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-8s %-10s %-8s %-10s %-15s %-10s %-15s\n",
                "Plan", "Movies", "Price", "Resolution", "Device Access", "Family Sharing" , "Additional Benefits" );
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Plan plan : Plan.planList) {
            PrintStream printf = System.out.printf("%-10s %-10d %-10.2f %-10s %-10d %-15d %-15s\n",
                    plan.getPlanName(),
                    plan.getMaxMovies(),
                    plan.getPrice(),
                    plan.getResolution(),
                    plan.getDeviceAccess(),
                    plan.getFamilySharing(),
                    plan.getAdditionalBenefit());
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }






    public void test() {
        if (this.Plans == null || this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            TestNonSubscription();
        } else {

            while (true) {

                System.out.println("Subscriptions Menu");
                System.out.println("1. Upgrade plan");
                System.out.println("2. Cancel Subscriptions");
                System.out.println("3. Check subscription status");
                System.out.println("4. Watch a movie");
                System.out.println("5. Send plan comparison");
                System.out.println("6. Check Number of subscriptions");
                System.out.println("7. Modify plan details");
                System.out.println("8. Exit");
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
                        Plan.modifyPlan();
                        break;
                    case 8:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;


                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
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







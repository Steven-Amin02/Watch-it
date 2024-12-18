package Model;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Subscription {
    private Plan Plans;
    private double Price;
    private Date StartDate;
    protected int MoviesWatched = 0;
    private Date ExpirationDate;
    private boolean RenewalStatus;
    private static int BasicCount;
    private static int PremiumCount;
    private static int StandardCount;

    Scanner scanner = new Scanner(System.in);

    public Subscription(String planType, Date startDate) {

        this.StartDate = startDate;
        if (planType == null || planType.trim().isEmpty()) {
            this.Plans = Plan.getPlanByName("Non-plan");
        } else {
            this.setPlans(planType); // Dynamically set the chosen plan
        }
        this.ExpirationDate = calculateExpirationDate(startDate);
        this.RenewalStatus = false; // Default is manual renewal
    }

    public Subscription() {

        this.Plans = Plan.getPlanByName("Non-plan");
    }


    public Plan getPlans() {
        if (this.Plans == null) {
            return new Plan("Non-plan");
        }
        return this.Plans;
    }

//
//    public void setPlans(Plan plans) {
//        Plans = plans;
//    }

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

    public int getMoviesWatched() {
        return MoviesWatched;
    }

    public void setMoviesWatched(int moviesWatched) {
        MoviesWatched = moviesWatched;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public void setPlans(String planType) {
        Scanner scanner = new Scanner(System.in);
        Plan selectedPlan;

        while (true) {
            try {
                selectedPlan = Plan.getPlanByName(planType); // Fetch plan from ArrayList

                // Check if the selected plan is valid and not "Non-plan"
                if (selectedPlan != null && !selectedPlan.getPlanName().equalsIgnoreCase("Non-plan")) {
                    this.Plans = selectedPlan;

                    // Update subscription count based on the selected plan
                    switch (selectedPlan.PlanName.toLowerCase()) {
                        case "basic":
                            BasicCount++;
                            System.out.println("-".repeat(50));
                            System.out.println("Successfully subscribed to the Basic plan.");
                            System.out.println("-".repeat(50));
                            break;
                        case "standard":
                            StandardCount++;
                            System.out.println("-".repeat(50));
                            System.out.println("Successfully subscribed to the Standard plan.");
                            System.out.println("-".repeat(50));
                            break;
                        case "premium":
                            PremiumCount++;
                            System.out.println("-".repeat(50));
                            System.out.println("Successfully subscribed to the Premium plan.");
                            System.out.println("-".repeat(50));
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid plan type.");
                    }
                    break; // Exit the loop after a valid plan is set
                } else {
                    System.out.println("-".repeat(50));
                    System.out.println("Invalid plan type. Please select a valid subscription plan:");
                    System.out.println("-".repeat(50));
                }
            } catch (NullPointerException | IllegalArgumentException e) {
                System.out.println("-".repeat(50));
                System.out.println("Error: Plan not found or invalid. Please enter a valid plan (Basic, Standard, Premium):");
                System.out.println("-".repeat(50));
            }

            // Prompt user again for input
            System.out.print("Enter plan type (Basic, Standard, Premium): ");
            planType = scanner.nextLine();
        }
    }

    public Date calculateExpirationDate(Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.DATE, 30);
        return calendar.getTime();
    }

    public void checkSubscriptionStatus() {
        try {
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                // إذا كان المستخدم على "Non-plan"
                System.out.println("\n*************************************************");
                System.out.println("    You are currently on the Non-plan.");
                System.out.println("    No subscription benefits are available.");
                System.out.println("*************************************************");
                TestNonSubscription();
            } else {
                Date currentDate = new Date();
                if (currentDate.after(this.ExpirationDate)) {
                    System.out.println("\n*************************************************");
                    System.out.println("    Your subscription has expired.");
                    System.out.println("    Please renew to continue watching movies.");
                    System.out.println("*************************************************");
                } else {
                    System.out.println("\n*************************************************");
                    System.out.println("    Your subscription is active.");
                    System.out.println("*************************************************");
                }
            }
        } catch (NullPointerException e) {
            System.out.println("\n*************************************************");
            System.out.println("    You don't have a plan.");
            System.out.println("*************************************************");
        }
    }

    public boolean canWatchMovie() {
        try {
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                System.out.println("\n*************************************************");
                System.out.println("    You cannot watch movies under the Non-plan.");
                System.out.println("*************************************************");
                return false;
            }

            Date currentDate = new Date();
            if (currentDate.after(this.ExpirationDate)) {
                System.out.println("\n*************************************************");
                System.out.println("    Cannot watch movies: Subscription has expired.");
                System.out.println("*************************************************");
                return false;
            }

            if (MoviesWatched >= this.Plans.getMaxMovies()) {
                System.out.println("\n*************************************************");
                System.out.println("    Cannot watch movies: You have reached your monthly limit of " + this.Plans.getMaxMovies() + " movies.");
                System.out.println("*************************************************");
                return false;
            }

            return true;

        } catch (NullPointerException e) {
            System.out.println("\n*************************************************");
            System.out.println("    You are currently on the Non-plan.");
            System.out.println("*************************************************");
            return false;
        }
    }

    public void watchMovie() {
        try {
            // Check if the user is on the "Non-plan"
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                System.out.println("\n*************************************************");
                System.out.println("    You cannot watch movies under the Non-plan.");
                System.out.println("    Please upgrade your plan.");
                System.out.println("*************************************************");
            } else if (canWatchMovie()) {
                // If the user can watch a movie, increment the count
                MoviesWatched++;
                System.out.println("\n*************************************************");
                System.out.println("    You watched a movie!");
                System.out.println("    Total movies watched this month: " + MoviesWatched);
                System.out.println("*************************************************");
            } else {
                // If the user cannot watch a movie (e.g., movie limit exceeded)
                System.out.println("\n*************************************************");
                System.out.println("    Unable to watch movie.");
                System.out.println("    Check your subscription status or movie limit.");
                System.out.println("*************************************************");
            }
        } catch (NullPointerException e) {
            // Plan is not set or maximum movie limit.
            System.out.println("\n*************************************************");
            System.out.println("    You don't have a plan.");
            System.out.println("*************************************************");
        } catch (IllegalStateException e) {
            // Subscription has expired
            System.out.println("\n*************************************************");
            System.out.println("    Your subscription has expired.");
            System.out.println("    Please renew to continue watching movies.");
            System.out.println("*************************************************");
        }
    }

    /// /////////// Upgrade Plan
    public void upgradePlan(String newPlanType) {
        try {
            // Fetch the new plan based on the provided type
            Plan newPlan = Plan.getPlanByName(newPlanType);

            // Check if the plan is valid
            if (newPlan == null) {
                System.out.println("\n*************************************************");
                System.out.println("    Invalid plan selected for upgrade.");
                System.out.println("*************************************************");
                return;
            }

            // Check if the user is already on the requested plan
            if (this.Plans != null && this.Plans.getPlanName().equalsIgnoreCase(newPlanType)) {
                System.out.println("\n*************************************************");
                System.out.println("    You are already on the " + newPlanType + " plan.");
                System.out.println("*************************************************");
                return;
            }

            // Adjust subscription counts for the old plan if it's not "Non-plan"
            if (this.Plans != null && !this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                switch (this.Plans.getPlanName().toLowerCase()) {
                    case "basic":
                        BasicCount--;
                        break;
                    case "standard":
                        StandardCount--;
                        break;
                    case "premium":
                        PremiumCount--;
                        break;
                }
            }

            // Update subscription count for the new plan
            switch (newPlan.getPlanName().toLowerCase()) {
                case "basic":
                    BasicCount++;
                    break;
                case "standard":
                    StandardCount++;
                    break;
                case "premium":
                    PremiumCount++;
                    break;
            }
            // Payment loop
            CardPayment cardPayment = new CardPayment();
            boolean isSuccessful = cardPayment.processPayment();

            while (!isSuccessful && !newPlan.getPlanName().equalsIgnoreCase("Non-plan")) {
                System.out.println("\n💳 Welcome to WatchIt Payment System");

                System.out.println("\nProcessing Payment...");

/*                        if (isSuccessful) {
                            System.out.println("✅ Thank you! Your payment was successful.");
                            return; // Exit after successful subscription
                        } else {
                            System.out.println("❌ Payment failed. Please try again.");
                        }*/
            }


            // Update user's plan and subscription details
            this.Plans = newPlan;
            this.Price = newPlan.getPrice();
            renewSubscription(); // Renew the subscription with new plan details
            MoviesWatched = 0; // Reset movie count
            this.ExpirationDate = calculateExpirationDate(new Date()); // Reset expiration date

            System.out.println("\n*************************************************");
            System.out.println("    Plan upgraded to: " + newPlan.getPlanName());
            System.out.println("    New monthly price: $" + this.Price);
            System.out.println("*************************************************");

        } catch (NullPointerException e) {
            System.out.println("\n*************************************************");
            System.out.println("    You don't have a plan.");
            System.out.println("*************************************************");
        }
    }


    public void DeletePlan() {
        try {
            if ( !this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                // Decrease the count based on the current plan
                switch (this.Plans.getPlanName().toLowerCase()) {
                    case "basic":
                        BasicCount--;
                        System.out.println("\n*************************************************");
                        System.out.println("    Your Basic plan subscription has been cancelled.");
                        System.out.println("*************************************************");
                        break;
                    case "standard":
                        StandardCount--;
                        System.out.println("\n*************************************************");
                        System.out.println("    Your Standard plan subscription has been cancelled.");
                        System.out.println("*************************************************");
                        break;
                    case "premium":
                        PremiumCount--;
                        System.out.println("\n*************************************************");
                        System.out.println("    Your Premium plan subscription has been cancelled.");
                        System.out.println("*************************************************");
                        break;
                    default:
                        break;
                }

                // Reset to Non-plan
                this.Plans = Plan.getPlanByName("Non-plan");
                System.out.println("\n*************************************************");
                System.out.println("    Your subscription plan has been reset to the default Non-plan.");
                System.out.println("*************************************************");

            } else {
                System.out.println("\n*************************************************");
                System.out.println("    You are already on the Non-plan and cannot delete it.");
                System.out.println("*************************************************");
            }
        } catch (NullPointerException e) {
            System.out.println("\n*************************************************");
            System.out.println("    No plan is currently associated with this subscription.");
            System.out.println("*************************************************");
        }
    }

    public void sendExpirationNotification() {
        try {
            if (this.Plans == null || this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
                // No notification for Non-plan users
                System.out.println("\n-------------------------------------------------");
                System.out.println("    You are on the Non-plan. No expiration notifications apply.");
                System.out.println("---------------------------------------------------");
                return;
            }

            if (this.ExpirationDate == null) {
                System.out.println("\n-------------------------------------------------");
                System.out.println("    Expiration date is not set for your plan.");
                System.out.println("---------------------------------------------------");
                return;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(this.ExpirationDate);
            calendar.add(Calendar.DATE, -3); // Notification 3 days before expiration
            Date notificationDate = calendar.getTime();

            Date currentDate = new Date();

            // Compare based on day, month, and year only
            if (isSameDay(currentDate, notificationDate)) {
                System.out.println("---------------------------------------------------");
                System.out.println("    Your subscription will expire in 3 days.");
                System.out.println("---------------------------------------------------");
            } else if (isSameDay(currentDate, this.ExpirationDate)) {
                System.out.println("---------------------------------------------------");
                System.out.println("    Your subscription expires today!");
                System.out.println("---------------------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("---------------------------------------------------");
            System.out.println("    An error occurred while sending notification.");
            System.out.println("---------------------------------------------------");
            System.out.println("    " + e.getMessage());
        }
    }

    public void usageReminder() {
        if ( this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            System.out.println("You are on the Non-plan. No usage limits apply.");
        } else if (this.Plans.MaxMovies - MoviesWatched == 1) {
            System.out.println("You have only 1 movie left this month.");
        }
    }

//    public void recommendUpgrade() {
//        if ( this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
//            System.out.println("You are on the Non-plan. Consider upgrading your plan to enjoy subscription benefits.");
//        } else if (MoviesWatched >= this.Plans.MaxMovies - 1) {
//            System.out.println("Consider upgrading to a higher plan for more movies.");
//        }
//    }


    public void renewSubscription() {
        if (this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            System.out.println("-----------------------------------------");
            System.out.println("⚠️ Renewal Not Applicable");
            System.out.println("You are currently on the Non-plan. Renewal is not available for this plan.");
            System.out.println("-----------------------------------------");
        } else {
            this.ExpirationDate = calculateExpirationDate(new Date());
            System.out.println("-----------------------------------------");
            System.out.println("✅ Subscription Renewed Successfully!");
            System.out.println("Your subscription has been renewed.");
            System.out.println("Next expiration date: " + this.ExpirationDate);
            System.out.println("-----------------------------------------");
        }
    }


    public void sendPlanComparison() {
        try {
            System.out.println("-----------------------------------------------------------");
            System.out.println("📋 Current Subscription Plan Details");
            System.out.println("-----------------------------------------------------------");
            System.out.printf("%-20s: %s\n", "Plan Name", this.Plans.getPlanName());
            System.out.printf("%-20s: %s\n", "Resolution", this.Plans.getResolution());
            System.out.printf("%-20s: %d\n", "Movies Watched", MoviesWatched);
            System.out.printf("%-20s: %d Devices\n", "Device Access", this.Plans.getDeviceAccess());
            System.out.printf("%-20s: %d Members\n", "Family Sharing", this.Plans.getFamilySharing());
            System.out.printf("%-20s: %s\n", "Additional Benefits", this.Plans.getAdditionalBenefit());
            System.out.println("-----------------------------------------------------------");
        } catch (NullPointerException e) {
            System.out.println("-----------------------------------------------------------");
            System.out.println("⚠️ No plan is set for this subscription.");
            System.out.println("Please choose a subscription plan to access its details.");
            System.out.println("-----------------------------------------------------------");
        }
    }


    public void DisplayNumOfSubscriptions() {
        System.out.println("-----------------------------------------------------------");
        System.out.println("📊 Subscription Summary");
        System.out.println("-----------------------------------------------------------");
        System.out.printf("%-20s: %d\n", "Basic Plan Subscriptions", BasicCount);
        System.out.printf("%-20s: %d\n", "Standard Plan Subscriptions", StandardCount);
        System.out.printf("%-20s: %d\n", "Premium Plan Subscriptions", PremiumCount);
        System.out.println("-----------------------------------------------------------");
    }

    public void DisplaySubscriptions() {
        System.out.println("📋 Available Subscription Plans:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-10s %-15s %-15s %-15s %-30s\n",
                "Plan", "Movies", "Price ($)", "Resolution", "Device Access", "Family Sharing", "Additional Benefits");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Plan plan : Plan.planList) {
            System.out.printf("%-10s %-10d %-10.2f %-15s %-15d %-15d %-30s\n",
                    plan.getPlanName(),
                    plan.getMaxMovies(),
                    plan.getPrice(),
                    plan.getResolution(),
                    plan.getDeviceAccess(),
                    plan.getFamilySharing(),
                    plan.getAdditionalBenefit());
        }
    }

    public void test() {
        if (this.Plans == null || this.Plans.getPlanName().equalsIgnoreCase("Non-plan")) {
            TestNonSubscription();
            return;
        }

        while (true) {
            System.out.println("\n📋 Subscriptions Menu");
            System.out.println("------------------------------------------------");
            System.out.println("1. Upgrade Plan");
            System.out.println("2. Cancel Subscription");
            System.out.println("3. Check Subscription Status");
            System.out.println("4. Watch a Movie");
            System.out.println("5. Send Plan Comparison");
            System.out.println("6. Check Number of Subscriptions");
            System.out.println("7. Modify Plan Details");
            System.out.println("8. Exit");
            System.out.println("------------------------------------------------");
            System.out.print("Enter your choice (1-8): ");

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    DisplaySubscriptions();
                    System.out.print("\nEnter new plan type (Non-plan, basic, standard, premium): ");
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
                    System.out.println("Thank you for using our service. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void TestNonSubscription() {
        System.out.println("Not Subscribed Menu");
        System.out.println("1. Choose your subscription.");
        System.out.println("2. Display available subscriptions");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                DisplaySubscriptions();
                System.out.print("Enter The Plan type :");
                String newPlan = scanner.next();
                setPlans(newPlan);
                    // Payment loop
                    CardPayment cardPayment = new CardPayment();
                    boolean isSuccessful = cardPayment.processPayment();

                    while (!isSuccessful && !newPlan.equalsIgnoreCase("Non-plan")) {
                        System.out.println("\n💳 Welcome to WatchIt Payment System");

                        System.out.println("\nProcessing Payment...");

/*                        if (isSuccessful) {
                            System.out.println("✅ Thank you! Your payment was successful.");
                            return; // Exit after successful subscription
                        } else {
                            System.out.println("❌ Payment failed. Please try again.");
                        }*/
                    }

                if (newPlan.equalsIgnoreCase("Non-plan")) {
                    TestNonSubscription();
                }
                break;
            case 2:
                DisplaySubscriptions();
                break;
            case 3:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);

        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

}

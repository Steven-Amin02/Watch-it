import java.util.ArrayList;
import java.util.Scanner;


public class Plan {
    protected String PlanName;
    protected int MaxMovies;
    protected double Price;
    protected String Resolution;
    protected int DeviceAccess;
    protected int FamilySharing;
    protected String AdditionalBenefit;

    // Create an ArrayList to store plan objects
    static ArrayList<Plan> planList = new ArrayList<>();

    // Constructor
    public Plan(String PlanName, int MaxMovies, double Price, String Resolution, int DeviceAccess, int FamilySharing, String AdditionalBenefits) {
        this.PlanName = PlanName;
        this.MaxMovies = MaxMovies;
        this.Price = Price;
        this.Resolution = Resolution;
        this.DeviceAccess = DeviceAccess;
        this.FamilySharing = FamilySharing;
        this.AdditionalBenefit = AdditionalBenefits;
    }


    public String getPlanName() {
        return PlanName;
    }

    public void setPlanName(String planName) {
        PlanName = planName;
    }

    public int getMaxMovies() {
        return MaxMovies;
    }

    public void setMaxMovies(int maxMovies) {
        MaxMovies = maxMovies;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getResolution() {
        return Resolution;
    }

    public void setResolution(String resolution) {
        Resolution = resolution;
    }

    public int getDeviceAccess() {
        return DeviceAccess;
    }

    public void setDeviceAccess(int deviceAccess) {
        DeviceAccess = deviceAccess;
    }

    public int getFamilySharing() {
        return FamilySharing;
    }

    public void setFamilySharing(int familySharing) {
        FamilySharing = familySharing;
    }

    public String getAdditionalBenefit() {
        return AdditionalBenefit;
    }

    public void setAdditionalBenefit(String additionalBenefit) {
        AdditionalBenefit = additionalBenefit;
    }

    // Static block to populate planList during class loading
    static {
        populatePlanList();
    }

    public static void populatePlanList() {
        if (planList.isEmpty()) { // Avoid duplicate entries
            planList.add(new Plan("basic", 5, 10.0, "SD", 5, 0, "Limited access to content"));
            planList.add(new Plan("standard", 10, 20.0, "HD", 10, 2, "Access to a larger library, Get 10% off every third movie"));
            planList.add(new Plan("premium", 30, 50.0, "4k", 50, 4, "Full library access, early content access, priority support"));
        }
    }

    public static Plan getPlanByName(String planType) {
        if (planList == null || planList.isEmpty()) {
            System.err.println("Debug: planList is null or empty. Initializing...");
            populatePlanList();
        }
        if (planType == null || planType.trim().isEmpty()) {
            System.err.println("Error: Invalid plan type provided.");
            return null;
        }
        for (Plan plan : planList) {
            if (plan.PlanName.equalsIgnoreCase(planType.trim())) {
                return plan;
            }
        }
        return null;
    }



    public static void modifyPlan() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Plan Name you want to modify:");
        String planName = scanner.nextLine();

        // Fetch the plan by name
        Plan existingPlan = Plan.getPlanByName(planName);

        if (existingPlan == null) {
            System.out.println("Plan not found. Please check the Plan Name and try again.");
            return;
        }

        System.out.println("Plan found: " + planName);
        System.out.println("Current details:");
        System.out.printf("Max Movies: %d, Price: %.2f, Resolution: %s, Device Access: %d, Family Sharing: %d, Additional Benefit: %s\n",
                existingPlan.getMaxMovies(),
                existingPlan.getPrice(),
                existingPlan.getResolution(),
                existingPlan.getDeviceAccess(),
                existingPlan.getFamilySharing(),
                existingPlan.getAdditionalBenefit());

        // Update details
        System.out.println("Enter new Max Movies (or press Enter to skip):");
        String maxMovies = scanner.nextLine();
        if (!maxMovies.isEmpty()) {
            existingPlan.setMaxMovies(Integer.parseInt(maxMovies));
        }

        System.out.println("Enter new Price (or press Enter to skip):");
        String price = scanner.nextLine();
        if (!price.isEmpty()) {
            existingPlan.setPrice(Double.parseDouble(price));
        }

        System.out.println("Enter new Resolution (or press Enter to skip):");
        String resolution = scanner.nextLine();
        if (!resolution.isEmpty()) {
            existingPlan.setResolution(resolution);
        }

        System.out.println("Enter new Device Access Count (or press Enter to skip):");
        String deviceAccess = scanner.nextLine();
        if (!deviceAccess.isEmpty()) {
            existingPlan.setDeviceAccess(Integer.parseInt(deviceAccess));
        }

        System.out.println("Enter new Family Sharing Count (or press Enter to skip):");
        String familySharing = scanner.nextLine();
        if (!familySharing.isEmpty()) {
            existingPlan.setFamilySharing(Integer.parseInt(familySharing));
        }

        System.out.println("Enter new Additional Benefit (or press Enter to skip):");
        String additionalBenefit = scanner.nextLine();
        if (!additionalBenefit.isEmpty()) {
            existingPlan.setAdditionalBenefit(additionalBenefit);
        }

        System.out.println("Plan updated successfully. Updated details:");
        System.out.printf("Max Movies: %d, Price: %.2f, Resolution: %s, Device Access: %d, Family Sharing: %d, Additional Benefit: %s\n",
                existingPlan.getMaxMovies(),
                existingPlan.getPrice(),
                existingPlan.getResolution(),
                existingPlan.getDeviceAccess(),
                existingPlan.getFamilySharing(),
                existingPlan.getAdditionalBenefit());
    }


}

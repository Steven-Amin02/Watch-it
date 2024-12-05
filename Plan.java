public abstract class Plan
{
protected String PlanName;
protected int MaxMovies;
protected double Price;
protected String Resolution;
protected int DeviceAccess;
protected int FamilySharing;
protected String AdditionalBenefit;


 public Plan(String PlanName,  int MaxMovies, double Price, String Resolution, int DeviceAccess, int FamilySharing ,String AdditionalBenefits)
 {
     this.PlanName = PlanName;
    this.MaxMovies = MaxMovies;
    this.Price = Price;
    this.Resolution = Resolution;
    this.DeviceAccess = DeviceAccess;
    this.FamilySharing = FamilySharing;
    this.AdditionalBenefit = AdditionalBenefits;
}


    public Plan() {

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



    public double getPrice() {
        return Price;
    }


    public String getResolution() {
        return Resolution;
    }



    public int getDeviceAccess() {
        return DeviceAccess;
    }



    public int getFamilySharing() {
        return FamilySharing;
    }



    public String getAdditionalBenefit() {
        return AdditionalBenefit;
    }












}
